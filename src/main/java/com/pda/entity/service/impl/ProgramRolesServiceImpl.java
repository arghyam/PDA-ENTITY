package com.pda.entity.service.impl;

import com.pda.entity.dao.Program;
import com.pda.entity.dao.ProgramRolePk;
import com.pda.entity.dao.ProgramRoles;
import com.pda.entity.dto.ProgramRolesDTO;
import com.pda.entity.dto.ResponseDTO;
import com.pda.entity.exceptions.BadRequestException;
import com.pda.entity.repository.ProgramRolesRepository;
import com.pda.entity.service.ProgramRolesService;
import com.pda.entity.service.ProgramService;
import com.pda.entity.utils.Constant;
import com.pda.entity.utils.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProgramRolesServiceImpl implements ProgramRolesService {

    @Autowired
    ProgramRolesRepository programRolesRepository;

    @Autowired
    ProgramService programService;
    protected static final Logger LOGGER = LoggerFactory.getLogger(ProgramRolesServiceImpl.class);

    @Override
    public Iterable<ProgramRoles> convertAndSaveAll(List<String> userIds, String role, Program program) {
        List<ProgramRoles> programRoles = new ArrayList<>();
        userIds.forEach(admin -> {
            ProgramRoles roles = new ProgramRoles();
            roles.setProgram(program);
            roles.setId(new ProgramRolePk(admin, program.getId(), role));
            programRoles.add(roles);
        });

        return programRolesRepository.saveAll(programRoles);
    }

    @Override
    public ResponseDTO addRole(ProgramRolesDTO programRolesDTO, BindingResult bindingResult, Long programId) {
        Program program = programService.getOne(programId);
        if (program == null || programRolesRepository.isRoleAlreadyAddedtoProgram(programRolesDTO.getProgramId(), programRolesDTO.getRole(), programRolesDTO.getUserIds())) {
            return HttpUtils.onFailure(HttpStatus.BAD_REQUEST.value(), "program is null or already added ");
        }

        List<ProgramRoles> programRolesList = new ArrayList<>();
        programRolesDTO.getUserIds().forEach(userId -> {
            ProgramRoles programRoles = new ProgramRoles();
            programRoles.setProgram(program);
            if (programRolesDTO.getRole().equalsIgnoreCase(Constant.PROGRAM_ADMIN) || programRolesDTO.getRole().equalsIgnoreCase(Constant.CONTENT_ADMIN) || programRolesDTO.getRole().equalsIgnoreCase(Constant.TRAINER)) {
                programRoles.setId(new ProgramRolePk(userId, program.getId(), programRolesDTO.getRole()));
            } else {
                throw new BadRequestException("Invalid program role");
            }
            programRolesList.add(programRoles);
        });

        programRolesRepository.saveAll(programRolesList);

        return HttpUtils.onSuccess_("SUCCESS", "Successfully added the roles ");
    }

    @Override
    public boolean checkEligibleTrainer(long id, String userId) {
        List<ProgramRoles> programRoles = programRolesRepository.checkEligibeTrainer(id, userId);
        if (programRoles.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<ProgramRoles> getRoles(Long programId) {
        LOGGER.info("get by program id: {}", programRolesRepository.getByProgramId(programId));
        return programRolesRepository.getByProgramId(programId);
    }

}
