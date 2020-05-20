package com.socion.entity.service;

import com.socion.entity.dao.Program;
import com.socion.entity.dao.ProgramRoles;
import com.socion.entity.dto.ProgramRolesDTO;
import com.socion.entity.dto.ResponseDTO;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface ProgramRolesService {

    public Iterable<ProgramRoles> convertAndSaveAll(List<String> userIds, String role, Program program);

    ResponseDTO addRole(ProgramRolesDTO programRolesDTO, BindingResult bindingResult, Long programId);

    boolean checkEligibleTrainer(long id, String userId);

    public List<ProgramRoles> getRoles(Long programId);
}
