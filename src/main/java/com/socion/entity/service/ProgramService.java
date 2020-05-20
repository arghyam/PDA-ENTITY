package com.socion.entity.service;

import com.socion.entity.dao.Entity;
import com.socion.entity.dao.Program;
import com.socion.entity.dto.MemberDTO;
import com.socion.entity.dto.ProgramDTO;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface ProgramService {

    public Program convertAndSave(ProgramDTO programDTO, Entity entity);

    public Program create(ProgramDTO programDTO, BindingResult bindingResult);

    Program update(ProgramDTO programDTO, BindingResult bindingResult, Long id);

    Program getOne(Long id);

    List<Program> getAll();

    List<Program> getProgramsByEntityId(Long entityId);

    public List<MemberDTO> getMemberForProgramId(Long programId, Long entityId);
}
