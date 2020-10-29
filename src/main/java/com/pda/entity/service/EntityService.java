package com.pda.entity.service;

import com.pda.entity.dao.Entity;
import com.pda.entity.dao.RegisterEntityandDocsOsid;
import com.pda.entity.dao.RegistryEntityRolesWithOsId;
import com.pda.entity.dao.RegistryEntityWithOsId;
import com.pda.entity.dto.*;
import com.pda.entity.dto.v2.EntityIdsDTO;

import com.pda.entity.dto.EntityRole;
import org.json.JSONException;
import org.springframework.validation.BindingResult;

import java.io.IOException;
import java.util.List;

public interface EntityService {

    public Entity convertAndSave(String name, String description);

    Entity create(EntityDTO entityDTO, BindingResult bindingResult);

    Entity getOne(Long id);

    Entity updateEntity(Long id, EntityDTO entityDTO, BindingResult bindingResult);

    void deleteEntity(Long id);

    List<Entity> getAllEntity();

    ResponseDTO pushentitytoregistry(EntityRegistryDto entityRegistryDto) throws IOException;


    ResponseDTO pushEntityDocstoRegistry(EntityDocs entityDocs) throws IOException, JSONException;

    RegisterEntityandDocsOsid searchEntityByEntityId(int entityId) throws IOException;

    ResponseDTO updateRegistryEntity( RegistryEntityWithOsId updatedregistryEntityWithOsId) throws IOException;


    ResponseDTO updateRegistryEntityDocs(String osid,boolean deleted) throws IOException;

    ResponseDTO pushEntityRoletoRegistry(EntityRole entityRole) throws  IOException;

    RegisterEntityandRoleOsids searchEntityByuserIdandentityId(int entityId, String userId) throws IOException;

    ResponseDTO updateRegistryEntityRoles(RegistryEntityRolesWithOsId updatedregistryEntityRolesWithOsId) throws  IOException;

    List<MemberDTO> getEntityadmindetails(Long id) throws IOException;

    ResponseDTO searchEntityIdByUserIdandEntityIdAndDeleted(Long entityId,String userId,boolean deleted) throws IOException;

    ResponseDTO searchRoleByUserIdandEntityIdAndDeleted(Long entityId,String userId,boolean deleted) throws IOException;

    ResponseDTO searchUserIdByEntityIdAndDeleted(EntityIdsDTO entityIdsDTO, boolean deleted) throws IOException;

    ResponseDTO searchUserIdByEntityIdAndRoleAndDeleted(Long entityId,String role,boolean deleted) throws IOException;

    ResponseDTO searchEntityIdByUserIdAndDeleted(String userId,boolean deleted) throws IOException;



}
