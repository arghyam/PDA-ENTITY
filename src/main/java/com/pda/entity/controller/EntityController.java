package com.pda.entity.controller;


import com.pda.entity.dao.Entity;
import com.pda.entity.dao.RegisterEntityandDocsOsid;
import com.pda.entity.dao.RegistryEntityRolesWithOsId;
import com.pda.entity.dao.RegistryEntityWithOsId;
import com.pda.entity.dto.*;
import com.pda.entity.dto.EntityRole;
import com.pda.entity.dto.v2.EntityIdsDTO;
import com.pda.entity.service.EntityRolesService;
import com.pda.entity.service.EntityService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class EntityController {

    @Autowired
    EntityService entityService;
    @Autowired
    EntityRolesService entityRolesService;

    @PostMapping("/entity")
    public Entity addEntity(@Validated @RequestBody EntityDTO entityDTO, BindingResult bindingResult) {
        return entityService.create(entityDTO, bindingResult);
    }

    @GetMapping("/entity/{id}")
    public Entity getEntity(@RequestParam(name = "id") Long id) {
        return entityService.getOne(id);
    }

    @GetMapping("/entity/role/{entity_id}")
    public List<MemberDTO> getEntityadmin(@PathVariable(name = "entity_id") Long id) {
        return entityRolesService.getEntityadmindetails(id);
    }


    @PutMapping("/entity/{id}")
    public Entity updateEntity(@PathVariable(value = "id") Long id, @Validated @RequestBody EntityDTO entityDTO, BindingResult bindingResult) {
        return entityService.updateEntity(id, entityDTO, bindingResult);
    }

    @DeleteMapping("/entity/{id}")
    public void deleteEntity(@PathVariable(value = "id") Long id) {
        entityService.deleteEntity(id);
    }

    @GetMapping("/entity")
    public List<Entity> getAllEntity() {
        return entityService.getAllEntity();
    }

    @PostMapping("/entity/registerEntity")
    public ResponseDTO pushentitytoregistry(@RequestBody EntityRegistryDto entityRegistryDto) throws IOException {
        return entityService.pushentitytoregistry(entityRegistryDto);
    }

    @PostMapping("/entity/registerEntityDocs")
    public ResponseDTO pushentityDocstoregistry(@RequestBody EntityDocs entityDocs) throws IOException, JSONException {
        return entityService.pushEntityDocstoRegistry(entityDocs);
    }

    @PostMapping("/entity/registerEntityRoles")
    public ResponseDTO pushentityRoletoregistry(@RequestBody EntityRole entityRole) throws IOException, JSONException {
        return entityService.pushEntityRoletoRegistry(entityRole);
    }
//    @GetMapping("/entity/entitySearchwithUserId/{userId}")
//    public List<Integer> entitysearchwith(@PathVariable(value="userId")String userId ) throws IOException {
////        return entityService.searchEntityByuserId(userId);
//    }
    @GetMapping("/entity/registryRolesSearch/{entityId}/{userId}")
    public RegisterEntityandRoleOsids registerEntityandRoleOsids(@PathVariable(value="entityId")int entityId, @PathVariable(value="userId")String userId) throws IOException {
        return entityService.searchEntityByuserIdandentityId(entityId,userId);

    }

    @GetMapping("/entity/registryRolesSearch/ids/{entityId}/{userId}/{deleted}")
    public ResponseDTO getEntityIdsFromEntityRole(@PathVariable(value="entityId")Long entityId, @PathVariable(value="userId")String userId, @PathVariable(value ="deleted") boolean deleted) throws IOException {
        return entityService.searchEntityIdByUserIdandEntityIdAndDeleted(entityId,userId,deleted);
    }

    @GetMapping("/entity/registryRolesSearch/ids/{userId}/{deleted}")
    public ResponseDTO getEntityIdsFromEntityRoleTable( @PathVariable(value="userId")String userId, @PathVariable(value ="deleted") boolean deleted) throws IOException {
        return entityService.searchEntityIdByUserIdAndDeleted(userId,deleted);
    }


    @GetMapping("/entity/registryRolesSearch/roles/{entityId}/{userId}/{deleted}")
    public ResponseDTO getRoleFromEntityRole(@PathVariable(value="entityId")Long entityId, @PathVariable(value="userId")String userId, @PathVariable(value ="deleted") boolean deleted) throws IOException {
        return entityService.searchRoleByUserIdandEntityIdAndDeleted(entityId,userId,deleted);
    }

    @PostMapping("/entity/registryRolesSearch/userIds/{deleted}")
    public ResponseDTO getUserIdsFromEntityRole(@RequestBody EntityIdsDTO entityIdsDTO, @PathVariable(value ="deleted") boolean deleted) throws IOException {
        return entityService.searchUserIdByEntityIdAndDeleted(entityIdsDTO,deleted);
    }

    @GetMapping("/entity/registryRolesSearch/userIds/{entityId}/{role}/{deleted}")
    public ResponseDTO getUserIdsFromEntityRole(@PathVariable(value="entityId")Long entityId,@PathVariable(value="role") String role,@PathVariable(value = "deleted") boolean deleted) throws IOException {
        return entityService.searchUserIdByEntityIdAndRoleAndDeleted(entityId,role,deleted);
    }

    @GetMapping("/entity/registrySearch/role/{entity_id}")
    public List<MemberDTO> getRegistryEntityadmin(@PathVariable(name = "entity_id") Long id) throws IOException {
        return entityService.getEntityadmindetails(id);
    }


    @GetMapping(value = "/entity/registrySearch/{entityId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public RegisterEntityandDocsOsid searchEntityByEntityId(@PathVariable(value="entityId")int entityId) throws IOException {
        return entityService.searchEntityByEntityId(entityId);
    }
    @PutMapping("entity/registryEntityUpdate")
    public ResponseDTO updateRegistryEntity(@RequestBody RegistryEntityWithOsId updatedregistryEntityWithOsId) throws IOException {
        return entityService.updateRegistryEntity(updatedregistryEntityWithOsId);
    }
    @PutMapping("entity/registryEntityDocsUpdate/{docsid}/{bool}")
    public ResponseDTO updateRegistryDocsEntity(@PathVariable(value="docsid") String osid,@PathVariable(value="bool") boolean deleted) throws IOException {
        return entityService.updateRegistryEntityDocs(osid,deleted);
    }

    @PutMapping("entity/registryEntityRolesUpdate")
    public ResponseDTO updateRegistryRolesEntity(@RequestBody RegistryEntityRolesWithOsId updatedregistryEntityRolesWithOsId) throws IOException {
        return entityService.updateRegistryEntityRoles(updatedregistryEntityRolesWithOsId);
    }




}