package com.socion.entity.controller;

import com.socion.entity.service.ProgramAdminService;
import com.socion.entity.dao.ProgramAdmin;
import com.socion.entity.dto.ProgramAdminDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class ProgramAdminController {

    @Autowired
    ProgramAdminService programAdminService;

    @PostMapping("/program-admins/")
    public Iterable<ProgramAdmin> addProgramAdmins(@Validated ProgramAdminDTO programAdminDTO, BindingResult bindingResult) {
        return programAdminService.addAdmins(programAdminDTO,bindingResult);
    }

    @GetMapping("/program-admin-info/{userId}")
    public List<ProgramAdmin> getProgramAdminInfo(@PathVariable(value = "userId") String userId) {
        return programAdminService.getByUserId(userId);
    }

    @GetMapping("/program-admin/{entityId}")
    public List<ProgramAdmin> getProgramAdmins(@PathVariable(value = "entityId") Long entityId) {
        return programAdminService.getProgramAdminsByEntityId(entityId);
    }
    
    @DeleteMapping("/program-admin/{entityId}/{userId}")
    public void deleteProgramAdmin(@PathVariable(value = "entityId") Long entityId,@PathParam(value = "userId") String userId) {
         programAdminService.deleteProgramAdmin(entityId, userId);
    }
}
