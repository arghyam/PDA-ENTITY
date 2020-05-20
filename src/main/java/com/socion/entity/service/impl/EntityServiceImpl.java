package com.socion.entity.service.impl;

import com.socion.entity.config.AppContext;
import com.socion.entity.dao.*;
import com.socion.entity.dao.*;
import com.socion.entity.dao.RegistryEntityRolesWithOsId;
import com.socion.entity.dto.*;
import com.socion.entity.dto.*;
import com.socion.entity.dto.v2.*;
import com.socion.entity.dto.EntityRole;
import com.socion.entity.dto.v2.*;
import com.socion.entity.exceptions.NotFoundException;
import com.socion.entity.repository.EntityRepository;
import com.socion.entity.service.EntityService;
import com.socion.entity.service.KeycloakService;
import com.socion.entity.service.ProgramAdminService;
import com.socion.entity.utils.AesUtil;
import com.socion.entity.utils.Constant;
import com.socion.entity.utils.HttpUtils;
import com.socion.entity.utils.ValidationUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.*;

@Service
public class EntityServiceImpl implements EntityService {

    @Autowired
    EntityRepository entityRepository;

    @Autowired
    KeycloakService keycloakService;

    @Autowired
    ProgramAdminService programAdminService;
    @Autowired
    AppContext appContext;
    @Autowired
    RegistryDao registryDao;

    @Autowired
    IamDao iamDao;
    protected static final Logger LOGGER = LoggerFactory.getLogger(EntityServiceImpl.class);

    @Override
    public Entity convertAndSave(String name, String description) {
        Entity entity = new Entity();
        entity.setName(name);
        return entityRepository.save(entity);
    }

    @Override
    public Entity create(EntityDTO entityDTO, BindingResult bindingResult) {
        ValidationUtil.valiadtePojo(bindingResult);

        Entity savedEntity = convertAndSave(entityDTO.getName(), entityDTO.getDescription());

        if (entityDTO.getProgramAdmin() != null) {
            programAdminService.convertAndSaveAll(entityDTO.getProgramAdmin(), savedEntity);
        }

        return savedEntity;
    }


    @Override
    public Entity getOne(Long id) {
        Entity entity = entityRepository.getOne(id);
        if (entity == null) {
            throw new NotFoundException("Entity not found with id " + id);
        }
        return entity;
    }

    @Override
    public Entity updateEntity(Long id, EntityDTO entityDTO, BindingResult bindingResult) {
        ValidationUtil.valiadtePojo(bindingResult);
        Entity entity = getOne(id);

        if (entity == null) {
            throw new NotFoundException("Entity not found with id " + id);
        }

        entity.setName(entityDTO.getName());
        entityRepository.save(entity);

        if (entityDTO.getProgramAdmin() != null) {
            programAdminService.convertAndSaveAll(entityDTO.getProgramAdmin(), entity);
        }

        return entity;
    }

    @Override
    public void deleteEntity(Long id) {
        Entity entity = getOne(id);
        if (entity == null) {
            throw new NotFoundException("Entity not found with Id " + id);
        }
        entity.setDeleted(true);
        entityRepository.save(entity);
        programAdminService.deleteProgramAdminsByEntityId(id);
    }

    @Override
    public List<Entity> getAllEntity() {
        return entityRepository.findAll();
    }

    @Override

    public ResponseDTO pushentitytoregistry(EntityRegistryDto entity) throws IOException {
        Request request = new Request();
        ResponseDTO responseDTO = new ResponseDTO();
        entity.setEntityidStr(String.valueOf(entity.getEntity_id()));
        request.setEntity(entity);
        entity.setAddress_line1(entity.getAddress_line1().replace(",", "#$%"));
        entity.setAddress_line2(entity.getAddress_line2().replace(",", "#$%"));
        RegistryRequest registryRequest = new RegistryRequest(null, request, RegistryResponse.API_ID.CREATE.getId());
        String adminAccessToken = keycloakService.generateAccessToken(appContext.getAdminUserName());
        Call<RegistryResponse> createRegistryEntryCall = registryDao.entities(adminAccessToken, registryRequest);
        retrofit2.Response registryUserCreationResponse = createRegistryEntryCall.execute();
        RegistryResponse registryResponse = (RegistryResponse) registryUserCreationResponse.body();

        if (!registryResponse.getResponseParams().getStatus().name().equals(Constant.SUCCESSFUL)) {

            LOGGER.error("Error Creating registry entry {} ", registryResponse.getResponseParams().getErrmsg());
            responseDTO.setMessage("Error creating registry entry");
            responseDTO.setResponseCode(500);
        } else {
            LOGGER.info("Entity successfully pushed to Registry");
            responseDTO.setMessage("Registry entry successfully created");
            responseDTO.setResponseCode(200);
        }
        return responseDTO;
    }

    public ResponseDTO pushEntityDocstoRegistry(EntityDocs entityDocs) throws IOException, JSONException {
        Request1 request = new Request1();
        ResponseDTO responseDTO = new ResponseDTO();
        entityDocs.setEntityidStr(String.valueOf(entityDocs.getEntity_id()));
        request.setEntityDocs(entityDocs);
        RegistryDocsRequest registryDocsRequest = new RegistryDocsRequest(null, request, RegistryResponse.API_ID.CREATE.getId());
        String adminAccessToken = keycloakService.generateAccessToken(appContext.getAdminUserName());
        Call<RegistryResponse> createRegistryEntryCall = registryDao.entitiesdocs(adminAccessToken, registryDocsRequest);
        retrofit2.Response registryUserCreationResponse = createRegistryEntryCall.execute();
        RegistryResponse registryResponse = (RegistryResponse) registryUserCreationResponse.body();

        if (!registryResponse.getResponseParams().getStatus().name().equals(Constant.SUCCESSFUL)) {

            LOGGER.error("Error Creating registry entry {} ", registryResponse.getResponseParams().getErrmsg());
            responseDTO.setMessage("Error creating registry entry");
            responseDTO.setResponseCode(500);
        } else {
            LOGGER.info("Entity successfully pushed to Registry");
            responseDTO.setMessage("Registry entry successfully created");
            responseDTO.setResponseCode(200);
        }
        return responseDTO;
    }

    public ResponseDTO pushEntityRoletoRegistry(EntityRole entityRole) throws IOException {
        RequestRoles request = new RequestRoles();
        ResponseDTO responseDTO = new ResponseDTO();
        entityRole.setEntityidStr(String.valueOf(entityRole.getEntity_id()));
        request.setEntityRole(entityRole);
        RegistryRolesRequest registryRolesRequest = new RegistryRolesRequest(null, request, RegistryResponse.API_ID.CREATE.getId());
        String adminAccessToken = keycloakService.generateAccessToken(appContext.getAdminUserName());
        Call<RegistryResponse> createRegistryEntryCall = registryDao.entitiesroles(adminAccessToken, registryRolesRequest);
        retrofit2.Response registryUserCreationResponse = createRegistryEntryCall.execute();
        RegistryResponse registryResponse = (RegistryResponse) registryUserCreationResponse.body();

        if (!registryResponse.getResponseParams().getStatus().name().equals(Constant.SUCCESSFUL)) {

            LOGGER.error("Error Creating registry entry {} ", registryResponse.getResponseParams().getErrmsg());
            responseDTO.setMessage("Error creating registry entry");
            responseDTO.setResponseCode(500);
        } else {
            LOGGER.info("Entity successfully pushed to Registry");
            responseDTO.setMessage("Registry entry successfully created");
            responseDTO.setResponseCode(200);
        }
        return responseDTO;

    }

    public RegisterEntityandDocsOsid searchEntityByEntityId(int entityId) throws IOException {
        ResponseDTO responseDTO = new ResponseDTO();
        RegistryEntityWithOsId registryEntityWithOsId = new RegistryEntityWithOsId();
        RegistryEntityDocsWithOsId registryEntityDocsWithOsId = new RegistryEntityDocsWithOsId();
        RegistryEntityRolesWithOsId registryEntityRolesWithOsId = new RegistryEntityRolesWithOsId();
        List<RegistryEntityDocsWithOsId> registerEntityandDocsOsids = new ArrayList<>();
        List<RegistryEntityRolesWithOsId> registerEntityandRoleOsids = new ArrayList<>();
        RegisterEntityWithEntityId entityIdd = new RegisterEntityWithEntityId();
        SlimRegistryEntityDto searchRegistryEntityDto = new SlimRegistryEntityDto();
        searchRegistryEntityDto.setId(RegistryResponse.API_ID.SEARCH.getId());
        searchRegistryEntityDto.setVer("1.0");
        entityIdd.setEntityidStr(String.valueOf(entityId));
        SlimRequestEntityId request = new SlimRequestEntityId();
        request.setEntity(entityIdd);
        searchRegistryEntityDto.setRequest(request);
        String adminAccessToken = keycloakService.generateAccessToken(appContext.getAdminUserName());
        RegistryResponse registryResponse = new RegistryResponse();
        registryResponse = registryDao.searchEntityByEntityId(adminAccessToken, searchRegistryEntityDto).execute().body();
        List<RegistryResponse> registryResponse1 = new ArrayList<>();

        registryResponse1.add(entitydocsretrocall(adminAccessToken, entityId));
        List<RegistryResponse> registryResponse3 = new ArrayList<>();

//        registryResponse3.add(entityrolesretrocall(adminAccessToken,entityId,));

        LOGGER.info("The result from the registry {}", registryResponse1.size());

        for (RegistryResponse registryResponse2 : registryResponse1) {

            if (registryResponse2.getResult() != null && registryResponse2.getResponseCode().equals("OK")) {
                LOGGER.info("The result for the entity docs is {}", registryResponse2.getResult());
                try {
                    int i = 0;
                    while ((((List<LinkedHashMap<String, Object>>) registryResponse2.getResult()).get(i) != null)) {
                        registryEntityDocsWithOsId = new RegistryEntityDocsWithOsId(((List<LinkedHashMap<String, Object>>) registryResponse2.getResult()).get(i));
                        if (!registryEntityDocsWithOsId.isDeleted()) {
                            registerEntityandDocsOsids.add(registryEntityDocsWithOsId);
                        }
                        i = i + 1;
                    }
                } catch (Exception e) {
                    LOGGER.info("Error logged as {}", e);
                }

                LOGGER.debug("Retrieved entity details successfully with entity_id : {} ", registryEntityDocsWithOsId.getEntity_id());
                responseDTO.setResponseCode(200);
                responseDTO.setMessage("Successfully retrieved entity details using entityId");

            }


        }

        if (registryResponse.getResult() != null && registryResponse.getResponseCode().equals("OK")) {

            registryEntityWithOsId = new RegistryEntityWithOsId(((List<LinkedHashMap<String, Object>>) registryResponse.getResult()).get(0));
            registryEntityWithOsId.setAddress_line1(registryEntityWithOsId.getAddress_line1().replace("#$%", ","));
            registryEntityWithOsId.setAddress_line2(registryEntityWithOsId.getAddress_line2().replace("#$%", ","));


            LOGGER.debug("Retrieved entity details successfully with entity_id : {} ", registryEntityWithOsId.getEntity_id());


            responseDTO.setResponseCode(200);
            responseDTO.setMessage("Successfully retrieved entity details using entityId");

        } else {
            LOGGER.error("Entity does not exist in registry with entityId : {} ", entityId);
            responseDTO.setResponseCode(500);
            responseDTO.setMessage("Failed to retrieve entity details using entity_id");


        }
        RegisterEntityandDocsOsid registerEntityandDocsOsid = new RegisterEntityandDocsOsid();
        registerEntityandDocsOsid.setRegistryEntityDocsWithOsId(registerEntityandDocsOsids);
        registerEntityandDocsOsid.setRegistryEntityWithOsId(registryEntityWithOsId);


        return registerEntityandDocsOsid;

    }

    public RegistryResponse entitydocsretrocall(String adminAccessToken, int entityId) throws IOException {
        SlimRegistryEntityDocsDto searchRegistryEntityDto = new SlimRegistryEntityDocsDto();
        RegisterEntityDocsWithEntityId entityIdd = new RegisterEntityDocsWithEntityId();
        SlimRequestEntityDocsId request = new SlimRequestEntityDocsId();
        searchRegistryEntityDto.setId(RegistryResponse.API_ID.SEARCH.getId());
        searchRegistryEntityDto.setVer("1.0");
        entityIdd.setEntityidStr(String.valueOf(entityId));
        request.setEntityDocs(entityIdd);
        searchRegistryEntityDto.setRequest(request);
        RegistryResponse registryResponse = (registryDao.searchEntityDocsByEntityId(adminAccessToken, searchRegistryEntityDto).execute().body());
        ObjectMapper obj = new ObjectMapper();
        LOGGER.info("askdbaknfa     {}", obj.writeValueAsString(searchRegistryEntityDto));
        return registryResponse;
    }

    public RegistryResponse entityrolesretrocall(String adminAccessToken, int entityId, String user_id) throws IOException {
        SlimRegistryEntityRolesDto searchRegistryEntityDto = new SlimRegistryEntityRolesDto();
        RegisterEntityRolesWithEntityId entityIdd = new RegisterEntityRolesWithEntityId();
        SlimRequestEntityRolesId request = new SlimRequestEntityRolesId();
        searchRegistryEntityDto.setId(RegistryResponse.API_ID.SEARCH.getId());
        searchRegistryEntityDto.setVer("1.0");
        entityIdd.setEntityidStr(String.valueOf(entityId));
        entityIdd.setUserid(user_id);
        request.setEntityRoles(entityIdd);
        searchRegistryEntityDto.setRequest(request);
        RegistryResponse registryResponse = (registryDao.searchEntityRolesByEntityIdAndUserId(adminAccessToken, searchRegistryEntityDto).execute().body());
        ObjectMapper obj = new ObjectMapper();
        LOGGER.info("askdbaknfa     {}", obj.writeValueAsString(searchRegistryEntityDto));
        return registryResponse;
    }

    public ResponseDTO updateRegistryEntity(RegistryEntityWithOsId updatedregistryEntityWithOsId) throws IOException {
        updatedregistryEntityWithOsId.setAddress_line1(updatedregistryEntityWithOsId.getAddress_line1().replace(",", "#$%"));
        updatedregistryEntityWithOsId.setAddress_line2(updatedregistryEntityWithOsId.getAddress_line2().replace(",", "#$%"));
        String adminAccessToken = keycloakService.generateAccessToken(appContext.getAdminUserName());
        ResponseDTO responseDTO = new ResponseDTO();

        RequestWithOsId request = new RequestWithOsId();
        RegistryEntityUpdateFormat registryEntityUpdateFormat = new RegistryEntityUpdateFormat();
        RegistryEntityWithOsId registryEntityWithOsId = updatedregistryEntityWithOsId;
        registryEntityUpdateFormat.setRegistryEntityWithOsId(registryEntityWithOsId);
        RegistryRequestWithOsId registryRequest = new RegistryRequestWithOsId(null, registryEntityUpdateFormat, RegistryResponse.API_ID.UPDATE.getId());
        ObjectMapper obj = new ObjectMapper();
        LOGGER.info("update=======================    {}", obj.writeValueAsString(registryEntityWithOsId));
        RegistryResponse registryResponse = registryDao.updateEntityByEntityId(adminAccessToken, registryRequest).execute().body();
        ObjectMapper obj1 = new ObjectMapper();
        LOGGER.info("after update=======================    {}", obj1.writeValueAsString(registryResponse));
        LOGGER.info("what happened {}", registryResponse.getResponseParams().getStatus());
        if (registryResponse.getResponseParams().getStatus().toString().equalsIgnoreCase("SUCCESSFUL")) {

            responseDTO.setMessage(registryResponse.getResponseParams().getStatus().toString());
            responseDTO.setResponseCode(200);
        } else {
            responseDTO.setMessage(registryResponse.getResponseParams().getStatus().toString());
            responseDTO.setResponseCode(500);
        }
        return responseDTO;


    }

    public ResponseDTO updateRegistryEntityRoles(RegistryEntityRolesWithOsId updatedregistryEntityRolesWithOsId) throws IOException {

        String adminAccessToken = keycloakService.generateAccessToken(appContext.getAdminUserName());
        ResponseDTO responseDTO = new ResponseDTO();

        RegistryEntityRolesUpdatedFormat registryEntityUpdateFormat = new RegistryEntityRolesUpdatedFormat();
        RegistryEntityRolesWithOsId registryEntityRolesWithOsId = updatedregistryEntityRolesWithOsId;
        registryEntityUpdateFormat.setRegistryEntityRolesWithOsId(registryEntityRolesWithOsId);
        RegistryRequestRolesWithOsId registryRequest = new RegistryRequestRolesWithOsId(null, registryEntityUpdateFormat, RegistryResponse.API_ID.UPDATE.getId());
        ObjectMapper obj = new ObjectMapper();
        LOGGER.info("update=======================    {}", obj.writeValueAsString(registryEntityRolesWithOsId));
        RegistryResponse registryResponse = registryDao.updateEntityRolesByEntityId(adminAccessToken, registryRequest).execute().body();
        ObjectMapper obj1 = new ObjectMapper();
        LOGGER.info("after update=======================    {}", obj1.writeValueAsString(registryResponse));
        LOGGER.info("what happened {}", registryResponse.getResponseParams().getStatus());
        if (registryResponse.getResponseParams().getStatus().toString().equalsIgnoreCase("SUCCESSFUL")) {

            responseDTO.setMessage(registryResponse.getResponseParams().getStatus().toString());
            responseDTO.setResponseCode(200);
        } else {
            responseDTO.setMessage(registryResponse.getResponseParams().getStatus().toString());
            responseDTO.setResponseCode(500);
        }
        return responseDTO;

    }


    public ResponseDTO updateRegistryEntityDocs(String osid, boolean deleted) throws IOException {

        String adminAccessToken = keycloakService.generateAccessToken(appContext.getAdminUserName());
        ResponseDTO responseDTO = new ResponseDTO();


        RegistryEntityDocsUpdateFormat registryEntityUpdateFormat = new RegistryEntityDocsUpdateFormat();
        RegistryEntityDocsWithOsId registryEntityDocsWithOsId = new RegistryEntityDocsWithOsId();

        SearchEntitywithOsId searchRegistryEntityDto = new SearchEntitywithOsId();
        RegisterEntityDocsWithOsId entityIdd = new RegisterEntityDocsWithOsId();
        entityIdd.setEntity_document_id(osid);
        SlimRequestEntityOsId entityDocsId = new SlimRequestEntityOsId();
        entityDocsId.setEntityDocs(entityIdd);

        searchRegistryEntityDto.setId(RegistryResponse.API_ID.SEARCH.getId());
        searchRegistryEntityDto.setVer("1.0");
        searchRegistryEntityDto.setRequest(entityDocsId);
        RegistryResponse registryResponse = (registryDao.searchEntityDocsByOsid(adminAccessToken, searchRegistryEntityDto).execute().body());
        ObjectMapper obj = new ObjectMapper();
        LOGGER.info("askdbaknfa     {}", obj.writeValueAsString(searchRegistryEntityDto));
        registryEntityDocsWithOsId = new RegistryEntityDocsWithOsId(((List<LinkedHashMap<String, Object>>) registryResponse.getResult()).get(0));
        RegistryEntityDocsWithOsId registryEntityWithOsId = registryEntityDocsWithOsId;
        registryEntityDocsWithOsId.setDeleted(deleted);
        registryEntityUpdateFormat.setRegistryEntityWithOsId(registryEntityWithOsId);
        RegistryDocsRequestWithOsId registryRequest = new RegistryDocsRequestWithOsId(null, registryEntityUpdateFormat, RegistryResponse.API_ID.UPDATE.getId());
        ObjectMapper obj1 = new ObjectMapper();
        LOGGER.info("update=======================    {}", obj1.writeValueAsString(registryEntityUpdateFormat));
        RegistryResponse registryResponse1 = registryDao.updateEntityDocsByEntityId(adminAccessToken, registryRequest).execute().body();
        ObjectMapper obj2 = new ObjectMapper();
        LOGGER.info("after update=======================    {}", obj2.writeValueAsString(registryResponse));
        LOGGER.info("what happened {}", registryResponse.getResponseParams().getStatus());
        if (registryResponse.getResponseParams().getStatus().toString().equalsIgnoreCase("SUCCESSFUL")) {

            responseDTO.setMessage(registryResponse.getResponseParams().getStatus().toString());
            responseDTO.setResponseCode(200);
        } else {
            responseDTO.setMessage(registryResponse.getResponseParams().getStatus().toString());
            responseDTO.setResponseCode(500);
        }
        return responseDTO;


    }

    public RegisterEntityandRoleOsids searchEntityByuserIdandentityId(int entityId, String user_id) throws IOException {
        RegisterEntityandRoleOsids registerEntityandRoleOsidss = new RegisterEntityandRoleOsids();
        List<RegistryResponse> registryResponse3 = new ArrayList<>();
        String adminAccessToken = keycloakService.generateAccessToken(appContext.getAdminUserName());
        registryResponse3.add(entityrolesretrocall(adminAccessToken, entityId, user_id));
        RegistryEntityRolesWithOsId registryEntityRolesWithOsId = new RegistryEntityRolesWithOsId();

        List<RegistryEntityRolesWithOsId> registerEntityandRoleOsids = new ArrayList<>();
        LOGGER.info("The result from the registry {}", registryResponse3.size());
        for (RegistryResponse registryResponse4 : registryResponse3) {

            if (registryResponse4.getResult() != null && registryResponse4.getResponseCode().equals("OK")) {
                LOGGER.info("The result for the entity roles is {}", registryResponse4.getResult());
                try {
                    int j = 0;
                    while ((((List<LinkedHashMap<String, Object>>) registryResponse4.getResult()).get(j) != null)) {
                        registryEntityRolesWithOsId = new RegistryEntityRolesWithOsId(((List<LinkedHashMap<String, Object>>) registryResponse4.getResult()).get(j));
                        registerEntityandRoleOsids.add(registryEntityRolesWithOsId);
                        j = j + 1;
                    }
                } catch (Exception e) {
                    LOGGER.info("Error logged as {}", e);
                }
                registerEntityandRoleOsidss.setRegistryEntityRolesWithOsId(registerEntityandRoleOsids);

                LOGGER.debug("Retrieved entity details successfully with entity_id : {} ", registryEntityRolesWithOsId.getEntity_id());


            }
        }
        return registerEntityandRoleOsidss;
    }

    public List<MemberDTO> getEntityadmindetails(Long id) throws IOException {

        List<RegistryEntityRolesWithOsId> entityRoles = fetchEntityRolesFromRegitry(id);
//        List<EntityRoles> entityRoles = entityRoleRepository.getByEntityId(id);
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
                    entityRoles.removeIf(entityRoles1 -> userList.contains(entityRoles1.getUserId()));
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

    public List<RegistryEntityRolesWithOsId> fetchEntityRolesFromRegitry(Long id) throws IOException {
        String adminAccessToken = keycloakService.generateAccessToken(appContext.getAdminUserName());
        RegistryRequestClass registryRequestClass = new RegistryRequestClass();
        SlimRequestEntityRoleObjects slimRequestEntityRoleObjects = new SlimRequestEntityRoleObjects();
        EntityIdSearch entityIdSearch = new EntityIdSearch();
        entityIdSearch.setEntityidStr(id.toString());
        slimRequestEntityRoleObjects.setObject(entityIdSearch);
        registryRequestClass.setRequest(slimRequestEntityRoleObjects);
        ObjectMapper objectMapper = new ObjectMapper();
        LOGGER.info(objectMapper.writeValueAsString(registryRequestClass));
        RegistryResponse registryResponse = registryDao.searchEntityRolesByEntityId(adminAccessToken, registryRequestClass).execute().body();
        RegistryEntityRolesWithOsId registryEntityRolesWithOsId = new RegistryEntityRolesWithOsId();
        List<RegistryEntityRolesWithOsId> registryEntityRolesWithOsIds = new ArrayList<>();
        try {
            int i = 0;
            while ((((List<LinkedHashMap<String, Object>>) registryResponse.getResult()).get(i) != null)) {
                registryEntityRolesWithOsId = new RegistryEntityRolesWithOsId(((List<LinkedHashMap<String, Object>>) registryResponse.getResult()).get(i));
                registryEntityRolesWithOsIds.add(registryEntityRolesWithOsId);
                i = i + 1;
            }

        } catch (Exception e) {
            LOGGER.info("Error logged as {}", e);
        }

        return registryEntityRolesWithOsIds;


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

    private List<MemberDTO> convertMember(List<RegistryEntityRolesWithOsId> entityRoles, Map<String, LinkedHashMap> userMap) {
        List<MemberDTO> memberDTOs = new ArrayList<>();

        if (null != entityRoles && !entityRoles.isEmpty() && null != userMap && !userMap.isEmpty()) {
            entityRoles.stream().forEach((RegistryEntityRolesWithOsId entityrolee) -> {
                memberDTOs.add(new MemberDTO(entityrolee.getUserId(), envcryption(entityrolee.getUserId()), entityrolee.getRole(), (String) userMap.get(entityrolee.getUserId()).get("name"), (String) userMap.get(entityrolee.getUserId()).get("photo"), (String) userMap.get(entityrolee.getUserId()).get("profileCardUrl"), entityrolee.isDeleted(), (String) userMap.get(entityrolee.getUserId()).get("emailId"),
                        userMap.get(entityrolee.getUserId()).get("countryCode").toString().concat(userMap.get(entityrolee.getUserId()).get("phoneNumber").toString())));
            });
        }

        return memberDTOs;
    }

    public String envcryption(String key) {
        AesUtil aesUtil = new AesUtil(appContext.getKeySize(), appContext.getIterationCount());
        return aesUtil.encrypt(appContext.getSaltValue(), appContext.getIvValue(),
                appContext.getSecretKey(), key);
    }

    private List<String> getUserIds(List<RegistryEntityRolesWithOsId> entityRoles) {
        List<String> userIds = new ArrayList<>();

        if (null != entityRoles && !entityRoles.isEmpty()) {
            entityRoles.stream().forEach(entiroles -> {
                userIds.add(entiroles.getUserId());
            });
        }
        return userIds;
    }

    public ResponseDTO searchEntityIdByUserIdandEntityIdAndDeleted(Long entityId, String userId, boolean deleted) throws IOException {
        ResponseDTO responseDTO = new ResponseDTO();
        List<Integer> entityIds = new ArrayList<>();
        String adminAccessToken = keycloakService.generateAccessToken(appContext.getAdminUserName());
        RegistryRequestClass registryRequestClass = new RegistryRequestClass();
        SlimRequestEntityRoleObjects slimRequestEntityRoleObjects = new SlimRequestEntityRoleObjects();

        EntityIdAndUserIdSearch entityIdSearch = new EntityIdAndUserIdSearch();
        entityIdSearch.setEntityidStr(entityId.toString());
        entityIdSearch.setUser_id(userId);
        slimRequestEntityRoleObjects.setObject(entityIdSearch);
        registryRequestClass.setRequest(slimRequestEntityRoleObjects);
        ObjectMapper objectMapper = new ObjectMapper();
        LOGGER.info(objectMapper.writeValueAsString(registryRequestClass));
        RegistryResponse registryResponse = registryDao.searchEntityRolesTable(adminAccessToken, registryRequestClass).execute().body();
        RegistryEntityRolesWithOsId registryEntityRolesWithOsId = new RegistryEntityRolesWithOsId();
        List<RegistryEntityRolesWithOsId> registryEntityRolesWithOsIds = new ArrayList<>();
        try {

            int i = 0;
            while ((((List<LinkedHashMap<String, Object>>) registryResponse.getResult()).get(i) != null)) {
                registryEntityRolesWithOsId = new RegistryEntityRolesWithOsId(((List<LinkedHashMap<String, Object>>) registryResponse.getResult()).get(i));
                if (registryEntityRolesWithOsId.isDeleted() == deleted) {
                    entityIds.add(registryEntityRolesWithOsId.getEntity_id());
                }
                i = i + 1;
            }

        } catch (Exception e) {
            HttpUtils.onFailure(HttpStatus.NOT_FOUND.value(), "Error while fetching Data");
            LOGGER.info("Error logged as {}", e);
        }
        responseDTO.setResponseCode(200);
        responseDTO.setMessage("Sucessfully Fetched Data");
        responseDTO.setResponse(entityIds);
        return responseDTO;
    }

    public ResponseDTO searchRoleByUserIdandEntityIdAndDeleted(Long entityId, String userId, boolean deleted) throws IOException {
        ResponseDTO responseDTO = new ResponseDTO();
        List<String> roles = new ArrayList<>();
        String adminAccessToken = keycloakService.generateAccessToken(appContext.getAdminUserName());
        RegistryRequestClass registryRequestClass = new RegistryRequestClass();
        SlimRequestEntityRoleObjects slimRequestEntityRoleObjects = new SlimRequestEntityRoleObjects();

        EntityIdAndUserIdSearch entityIdSearch = new EntityIdAndUserIdSearch();
        entityIdSearch.setEntityidStr(entityId.toString());
        entityIdSearch.setUser_id(userId);
        slimRequestEntityRoleObjects.setObject(entityIdSearch);
        registryRequestClass.setRequest(slimRequestEntityRoleObjects);
        ObjectMapper objectMapper = new ObjectMapper();
        LOGGER.info(objectMapper.writeValueAsString(registryRequestClass));
        RegistryResponse registryResponse = registryDao.searchEntityRolesTable(adminAccessToken, registryRequestClass).execute().body();
        RegistryEntityRolesWithOsId registryEntityRolesWithOsId = new RegistryEntityRolesWithOsId();
        List<RegistryEntityRolesWithOsId> registryEntityRolesWithOsIds = new ArrayList<>();
        try {

            int i = 0;
            while ((((List<LinkedHashMap<String, Object>>) registryResponse.getResult()).get(i) != null)) {
                registryEntityRolesWithOsId = new RegistryEntityRolesWithOsId(((List<LinkedHashMap<String, Object>>) registryResponse.getResult()).get(i));
                if (registryEntityRolesWithOsId.isDeleted() == deleted) {
                    roles.add(registryEntityRolesWithOsId.getRole());
                    LOGGER.info(registryEntityRolesWithOsId.getRole());
                }
                i = i + 1;
            }

        } catch (Exception e) {
            HttpUtils.onFailure(HttpStatus.NOT_FOUND.value(), "Error while fetching Data");
            LOGGER.info("Error logged as {}", e);
        }
        responseDTO.setResponseCode(200);
        responseDTO.setMessage("Sucessfully Fetched Data");
        responseDTO.setResponse(roles);
        return responseDTO;
    }

    public ResponseDTO searchUserIdByEntityIdAndDeleted(EntityIdsDTO entityIdsDTO, boolean deleted) throws IOException {
        ResponseDTO responseDTO = new ResponseDTO();
        List<String> userIds = new ArrayList<>();
        List<Map<String, Object>> finalResponse = new ArrayList<>();
        String adminAccessToken = keycloakService.generateAccessToken(appContext.getAdminUserName());
        RegistryRequestClass registryRequestClass = new RegistryRequestClass();
        SlimRequestEntityRoleObjects slimRequestEntityRoleObjects = new SlimRequestEntityRoleObjects();
        for (Long entityId : entityIdsDTO.getEntityIds()) {
            slimRequestEntityRoleObjects.setObject(new EntityIdSearch(entityId.toString()));
            registryRequestClass.setRequest(slimRequestEntityRoleObjects);
            ObjectMapper objectMapper = new ObjectMapper();
            LOGGER.info(objectMapper.writeValueAsString(registryRequestClass));
            RegistryResponse registryResponse = registryDao.searchEntityRolesTable(adminAccessToken, registryRequestClass).execute().body();
            RegistryEntityRolesWithOsId registryEntityRolesWithOsId = new RegistryEntityRolesWithOsId();
            List<RegistryEntityRolesWithOsId> registryEntityRolesWithOsIds = new ArrayList<>();
            try {

                int i = 0;
                while ((((List<LinkedHashMap<String, Object>>) registryResponse.getResult()).get(i) != null)) {
                    registryEntityRolesWithOsId = new RegistryEntityRolesWithOsId(((List<LinkedHashMap<String, Object>>) registryResponse.getResult()).get(i));
                    if (registryEntityRolesWithOsId.isDeleted() == deleted) {
                        userIds.add(registryEntityRolesWithOsId.getUserId());
                    }
                    i = i + 1;
                }

            } catch (Exception e) {
                List<String> finalIds = new ArrayList<>(userIds);
                Map<String, Object> map =new HashMap<>();
                map.put(entityId.toString(),finalIds);
                userIds.clear();
                finalResponse.add(map);
                LOGGER.info("Error logged as {}", e);

            }
        }
            responseDTO.setResponseCode(200);
            responseDTO.setMessage("Sucessfully Fetched Data");
            responseDTO.setResponse(finalResponse);
            return responseDTO;

        }

        public ResponseDTO searchUserIdByEntityIdAndRoleAndDeleted (Long entityId, String role ,boolean deleted) throws
        IOException {
            ResponseDTO responseDTO = new ResponseDTO();
            List<String> userIds = new ArrayList<>();
            String adminAccessToken = keycloakService.generateAccessToken(appContext.getAdminUserName());
            RegistryRequestClass registryRequestClass = new RegistryRequestClass();
            SlimRequestEntityRoleObjects slimRequestEntityRoleObjects = new SlimRequestEntityRoleObjects();

            EntityIdAndRoleSearch entityIdSearch = new EntityIdAndRoleSearch();
            entityIdSearch.setEntityidStr(entityId.toString());
            entityIdSearch.setRole(role);
            slimRequestEntityRoleObjects.setObject(entityIdSearch);
            registryRequestClass.setRequest(slimRequestEntityRoleObjects);
            ObjectMapper objectMapper = new ObjectMapper();
            LOGGER.info(objectMapper.writeValueAsString(registryRequestClass));
            RegistryResponse registryResponse = registryDao.searchEntityRolesTable(adminAccessToken, registryRequestClass).execute().body();
            RegistryEntityRolesWithOsId registryEntityRolesWithOsId = new RegistryEntityRolesWithOsId();
            List<RegistryEntityRolesWithOsId> registryEntityRolesWithOsIds = new ArrayList<>();
            try {

                int i = 0;
                while ((((List<LinkedHashMap<String, Object>>) registryResponse.getResult()).get(i) != null)) {
                    registryEntityRolesWithOsId = new RegistryEntityRolesWithOsId(((List<LinkedHashMap<String, Object>>) registryResponse.getResult()).get(i));
                    if (registryEntityRolesWithOsId.isDeleted() == deleted) {
                        userIds.add(registryEntityRolesWithOsId.getUserId());
                    }
                    i = i + 1;
                }

            } catch (Exception e) {
                HttpUtils.onFailure(HttpStatus.NOT_FOUND.value(), "Error while fetching Data");
                LOGGER.info("Error logged as {}", e);
            }
            responseDTO.setResponseCode(200);
            responseDTO.setMessage("Sucessfully Fetched Data");
            responseDTO.setResponse(userIds);
            return responseDTO;
        }

        public ResponseDTO searchEntityIdByUserIdAndDeleted (String userId,boolean deleted) throws IOException {
            ResponseDTO responseDTO = new ResponseDTO();
            List<Integer> entityIds = new ArrayList<>();
            String adminAccessToken = keycloakService.generateAccessToken(appContext.getAdminUserName());
            RegistryRequestClass registryRequestClass = new RegistryRequestClass();
            SlimRequestEntityRoleObjects slimRequestEntityRoleObjects = new SlimRequestEntityRoleObjects();

            UserIdSearch entityIdSearch = new UserIdSearch();
            entityIdSearch.setUser_id(userId);
            slimRequestEntityRoleObjects.setObject(entityIdSearch);
            registryRequestClass.setRequest(slimRequestEntityRoleObjects);
            ObjectMapper objectMapper = new ObjectMapper();
            LOGGER.info(objectMapper.writeValueAsString(registryRequestClass));
            RegistryResponse registryResponse = registryDao.searchEntityRolesTable(adminAccessToken, registryRequestClass).execute().body();
            RegistryEntityRolesWithOsId registryEntityRolesWithOsId = new RegistryEntityRolesWithOsId();
            List<RegistryEntityRolesWithOsId> registryEntityRolesWithOsIds = new ArrayList<>();
            try {

                int i = 0;
                while ((((List<LinkedHashMap<String, Object>>) registryResponse.getResult()).get(i) != null)) {
                    registryEntityRolesWithOsId = new RegistryEntityRolesWithOsId(((List<LinkedHashMap<String, Object>>) registryResponse.getResult()).get(i));
                    if (registryEntityRolesWithOsId.isDeleted() == deleted) {
                        entityIds.add(registryEntityRolesWithOsId.getEntity_id());
                    }
                    i = i + 1;
                }

            } catch (Exception e) {
                HttpUtils.onFailure(HttpStatus.NOT_FOUND.value(), "Error while fetching Data");
                LOGGER.info("Error logged as {}", e);
            }
            responseDTO.setResponseCode(200);
            responseDTO.setMessage("Sucessfully Fetched Data");
            responseDTO.setResponse(entityIds);
            return responseDTO;
        }


    }


