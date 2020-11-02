package com.pda.entity.controller;

import com.pda.entity.dao.Program;
import com.pda.entity.dto.MemberDTO;
import com.pda.entity.service.ProgramService;
import com.pda.entity.dto.ProgramDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/entity", produces = {"application/json"})
public class ProgramController {

    @Autowired
    ProgramService programService;

    @PostMapping(value = "/program")
    public Program create(@RequestBody ProgramDTO programDTO, BindingResult bindingResult) {
        return programService.create(programDTO, bindingResult);
    }

    @PutMapping(value = "/program/{id}")
    public Program update(@PathVariable(value = "id") Long id, @Validated @RequestBody ProgramDTO programDTO, BindingResult bindingResult) {
        return programService.update(programDTO, bindingResult, id);
    }

    @GetMapping(value = "/program/{id}")
    public Program get(@PathVariable(value = "id") Long id) {
        return programService.getOne(id);
    }

    @GetMapping(value = "/program")
    public List<Program> getAll() {
        return programService.getAll();
    }

    @GetMapping(value = "/program/entity/{entityId}")
    public List<Program> getProgramForEntity(@PathVariable(value = "entityId") Long entityId) {
        return programService.getProgramsByEntityId(entityId);
    }

    @GetMapping(value = "/program/members")
    public List<MemberDTO> getMemberForProgram(@RequestParam(value = "programId") Long programId, @RequestParam(value = "entityId") Long entityId) {
        return programService.getMemberForProgramId(programId, entityId);
    }
}
