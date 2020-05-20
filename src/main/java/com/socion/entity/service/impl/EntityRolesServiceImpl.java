package com.socion.entity.service.impl;

import com.socion.entity.config.AppContext;
import com.socion.entity.dao.EntityRoles;
import com.socion.entity.dao.IamDao;
import com.socion.entity.dto.MemberDTO;
import com.socion.entity.dto.UserRequestTDO;
import com.socion.entity.repository.EntityRoleRepository;
import com.socion.entity.service.EntityRolesService;
import com.socion.entity.utils.AesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class EntityRolesServiceImpl implements EntityRolesService {

    @Autowired
    EntityRoleRepository entityRoleRepository;
    @Autowired
    IamDao iamDao;
    @Autowired
    AppContext appContext;

    private static final Logger LOGGER = LoggerFactory.getLogger(EntityRolesServiceImpl.class);

    public List<MemberDTO> getEntityadmindetails(Long id) {
        List<EntityRoles> entityRoles = entityRoleRepository.getByEntityId(id);
        Call<List<LinkedHashMap>> userRequest;
        try {
            List<String> userList = getUserIds(entityRoles);
            userRequest = iamDao.getUsers(new UserRequestTDO(getUserIds(entityRoles)));
            Response<List<LinkedHashMap>> userResponse = userRequest.execute();
            if (userResponse.isSuccessful()) {
                List<LinkedHashMap> userDetails = (List<LinkedHashMap>) userResponse.body();
                List<String> userIdsfromDb = new ArrayList<>();
                userDetails.stream().forEach((LinkedHashMap userDetail) -> {
                    userIdsfromDb.add(userDetail.get("userId").toString());
                });
                if (!(userDetails == null)) {
                    userList.removeAll(userIdsfromDb);
                    entityRoles.removeIf(entityRoles1 -> userList.contains(entityRoles1.getEntityRolePk().getUserId()));
                    return convertMember(entityRoles, prepareUserMap(userDetails));
                } else {
                    return new ArrayList<>();
                }
            }
        } catch (IOException e) {
            LOGGER.error("Error logged as ", e);
        }
        return new ArrayList<>();
    }

    private Map<String, LinkedHashMap> prepareUserMap(List<LinkedHashMap> registryUsers) {
        Map<String, LinkedHashMap> userMap = new LinkedHashMap<>();
        if (null != registryUsers && !registryUsers.isEmpty()) {
            registryUsers.stream().forEach(user -> {
                userMap.put((String) user.get("userId"), user);
            });
        }
        return userMap;

    }

    private List<MemberDTO> convertMember(List<EntityRoles> entityRoles, Map<String, LinkedHashMap> userMap) {
        List<MemberDTO> memberDTOs = new ArrayList<>();

        if (null != entityRoles && !entityRoles.isEmpty() && null != userMap && !userMap.isEmpty()) {
            entityRoles.stream().forEach(entityrolee -> {
                memberDTOs.add(new MemberDTO(entityrolee.getEntityRolePk().getUserId(), envcryption(entityrolee.getEntityRolePk().getUserId()), entityrolee.getEntityRolePk().getRole(), (String) userMap.get(entityrolee.getEntityRolePk().getUserId()).get("name"), (String) userMap.get(entityrolee.getEntityRolePk().getUserId()).get("photo"), (String) userMap.get(entityrolee.getEntityRolePk().getUserId()).get("profileCardUrl"), entityrolee.getDeleted(), (String) userMap.get(entityrolee.getEntityRolePk().getUserId()).get("emailId"),
                        userMap.get(entityrolee.getEntityRolePk().getUserId()).get("countryCode").toString().concat(userMap.get(entityrolee.getEntityRolePk().getUserId()).get("phoneNumber").toString())));
            });
        }

        return memberDTOs;
    }

    public String envcryption(String key) {
        AesUtil aesUtil = new AesUtil(appContext.getKeySize(), appContext.getIterationCount());
        return aesUtil.encrypt(appContext.getSaltValue(), appContext.getIvValue(),
                appContext.getSecretKey(), key);
    }

    private List<String> getUserIds(List<EntityRoles> entityRoles) {
        List<String> userIds = new ArrayList<>();

        if (null != entityRoles && !entityRoles.isEmpty()) {
            entityRoles.stream().forEach((EntityRoles entiroles) -> {
                userIds.add(entiroles.getEntityRolePk().getUserId());
            });
        }
        return userIds;
    }
}
