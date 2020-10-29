package com.pda.entity.service;

import com.pda.entity.dao.Program;
import com.pda.entity.dao.ProgramRoles;
import com.pda.entity.dto.ProgramRolesDTO;
import com.pda.entity.dto.ResponseDTO;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface ProgramRolesService {

    public Iterable<ProgramRoles> convertAndSaveAll(List<String> userIds, String role, Program program);

    ResponseDTO addRole(ProgramRolesDTO programRolesDTO, BindingResult bindingResult, Long programId);

    boolean checkEligibleTrainer(long id, String userId);

    public List<ProgramRoles> getRoles(Long programId);
}
