package com.socion.entity.controller;

import com.socion.entity.service.ProgramRolesService;
import com.socion.entity.dto.ProgramRolesDTO;
import com.socion.entity.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/entity", produces = {"application/json"})
public class ProgramRolesController {

    @Autowired
    ProgramRolesService programRolesService;

    @PostMapping(value = "/program/{programId}/roles/")
    public ResponseDTO addContentAdmin(@Validated @RequestBody ProgramRolesDTO programRolesDTO, BindingResult bindingResult, @PathVariable(value = "programId") Long programId) {
        return programRolesService.addRole(programRolesDTO, bindingResult, programId);
    }

    @PostMapping(value = "/program/trainer")
    public void addTrainer(@Validated @RequestBody ProgramRolesDTO programRolesDTO, BindingResult bindingResult) {
        programRolesService.addRole(programRolesDTO, bindingResult, programRolesDTO.getProgramId());
    }
}
