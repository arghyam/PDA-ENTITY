package com.pda.entity.service.impl;

import com.pda.entity.dao.Entity;
import com.pda.entity.dao.IamDao;
import com.pda.entity.dao.Program;
import com.pda.entity.dao.ProgramRoles;
import com.pda.entity.dto.MemberDTO;
import com.pda.entity.dto.UserRequestTDO;
import com.pda.entity.exceptions.NotFoundException;
import com.pda.entity.repository.ProgramRepository;
import com.pda.entity.config.AppContext;
import com.pda.entity.dto.ProgramDTO;
import com.pda.entity.service.EntityService;
import com.pda.entity.service.ProgramAdminService;
import com.pda.entity.service.ProgramRolesService;
import com.pda.entity.service.ProgramService;
import com.pda.entity.utils.AesUtil;
import com.pda.entity.utils.Constant;
import com.pda.entity.utils.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProgramServiceImpl implements ProgramService {

    @Autowired
    ProgramRepository programRepository;
    
    @Autowired
    ProgramAdminService programAdminService;
    
    @Autowired
    ProgramRolesService programRolesService;

    @Autowired
    EntityService entityService;
    
    @Autowired
    AppContext appContext;
    
    @Autowired
    IamDao iamDao;
    protected static final Logger LOGGER = LoggerFactory.getLogger(ProgramServiceImpl.class);
    @Override
    public Program convertAndSave(ProgramDTO programDTO, Entity entity) {
        Program program = new Program();
        program.setName(programDTO.getName());
        program.setDescription(programDTO.getDescription());
        program.setEmail(programDTO.getEmail());
        program.setEntity(entity);
        program.setActive(true);
        try {
            program.setStartDate(new SimpleDateFormat(Constant.DATE_FORMAT).parse(programDTO.getStartDate()));
            program.setEndDate(new SimpleDateFormat(Constant.DATE_FORMAT).parse(programDTO.getEndDate()));
        } catch (ParseException e) {
           LOGGER.info("EXCEPTION:{}",e.getMessage());
        }
        return programRepository.save(program);
    }

    @Override
    public Program create(ProgramDTO programDTO, BindingResult bindingResult) {
        ValidationUtil.valiadtePojo(bindingResult);
        Entity entity = entityService.getOne(programDTO.getEntityId());
        return convertAndSave(programDTO,entity);
    }

    @Override
    public Program update(ProgramDTO programDTO, BindingResult bindingResult, Long id) {
        ValidationUtil.valiadtePojo(bindingResult);
        Program program = getOne(id);
        if(program == null) {
            throw new NotFoundException("Program not found with id "+id);
        }
        program.setName(programDTO.getName());
        program.setDescription(programDTO.getDescription());
        program.setEmail(programDTO.getEmail());

        Entity entity = entityService.getOne(programDTO.getEntityId());

        program.setEntity(entity);
        program.setActive(true);
        try {
            program.setStartDate(new SimpleDateFormat(Constant.DATE_FORMAT).parse(programDTO.getStartDate()));
            program.setEndDate(new SimpleDateFormat(Constant.DATE_FORMAT).parse(programDTO.getEndDate()));
        } catch (ParseException e) {
        LOGGER.info("EXCEPTION:{}",e.getMessage());
        }
        return programRepository.save(program);
    }

    @Override
    public Program getOne(Long id) {
        Program program = programRepository.getOne(id);
        if(program == null) {
            throw new NotFoundException("Program not found with id "+id);
        }
        return program;
    }

    @Override
    public List<Program> getAll() {
        return programRepository.findAll();
    }

    @Override
    public List<Program> getProgramsByEntityId(Long entityId) {
        return programRepository.getProgramByEntityId(entityId);
    }

	@Override
	public List<MemberDTO> getMemberForProgramId(Long programId, Long entityId) {
		List<ProgramRoles> programRoles = programRolesService.getRoles(programId);
		LOGGER.info("program roles list:{}",programRoles);
		Call<List<LinkedHashMap>> userRequest;
		try {
		    List<String> userList = getUserIds(programRoles);
			userRequest = iamDao.getUsers(new UserRequestTDO(userList));
			Response<List<LinkedHashMap>> userResponse = userRequest.execute();
			if (userResponse.isSuccessful()) {
				List<LinkedHashMap> userDetails = (List<LinkedHashMap>) userResponse.body();
                List<String> userIDsFromDb =  new ArrayList<>();
				userDetails.stream().forEach(userDetail -> {
                    userIDsFromDb.add(userDetail.get("userId").toString());
                });
				userList.removeAll(userIDsFromDb);
				programRoles.removeIf(programRole -> userList.contains(programRole.getId().getUserId()));
				return convertMember(programRoles, prepareUserMap(userDetails));
		    }
		} catch (IOException e) {
            LOGGER.info("EXCEPTION:{}",e.getMessage());
		}
		return new ArrayList<>();
	}
	
	private List<MemberDTO> convertMember( List<ProgramRoles> programRoles, Map<String, LinkedHashMap> userMap) {
		List<MemberDTO> memberDTOs = new ArrayList<>();

		if (null != programRoles && !programRoles.isEmpty() && null!=userMap && !userMap.isEmpty()) {
			programRoles.stream().forEach(progRole -> {
			    LOGGER.info("program role is:{}",progRole);
			    memberDTOs.add(new MemberDTO(progRole.getId().getUserId(), envcryption(progRole.getId().getUserId()), progRole.getId().getRole(), (String) userMap.get(progRole.getId().getUserId()).get("name"), (String) userMap.get(progRole.getId().getUserId()).get("photo"), (String) userMap.get(progRole.getId().getUserId()).get("profileCardUrl"), progRole.isDeleted(),(String)userMap.get(progRole.getId().getUserId()).get("emailId"),
                        userMap.get(progRole.getId().getUserId()).get("countryCode").toString().concat(userMap.get(progRole.getId().getUserId()).get("phoneNumber").toString())));
			});
		}
		LOGGER.info("membetDTOS IS:{}",memberDTOs);
		return memberDTOs;
	}
	
	public String envcryption(String key) {
		 AesUtil aesUtil = new AesUtil(appContext.getKeySize(), appContext.getIterationCount());
	     return aesUtil.encrypt(appContext.getSaltValue(), appContext.getIvValue(),
                 appContext.getSecretKey(), key);
	}
	
	private List<String> getUserIds( List<ProgramRoles> programRoles) {
		List<String> userIds = new ArrayList<>();
		if (null != programRoles && !programRoles.isEmpty()) {
			programRoles.stream().forEach(progRole -> {
				userIds.add(progRole.getId().getUserId());
			});
		}
		return userIds;
	}
	
	private Map<String, LinkedHashMap> prepareUserMap(List<LinkedHashMap> registryUsers) {
		Map<String, LinkedHashMap> userMap = new LinkedHashMap<>();
		if (null != registryUsers && !registryUsers.isEmpty()) {
			registryUsers.stream().forEach(user -> {
				userMap.put((String)user.get("userId"), user);
			});
		}
		return userMap;
		
	}
}
