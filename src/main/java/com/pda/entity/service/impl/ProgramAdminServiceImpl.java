package com.pda.entity.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.pda.entity.dao.Entity;
import com.pda.entity.dao.ProgramAdmin;
import com.pda.entity.dao.ProgramAdminPk;
import com.pda.entity.dto.ProgramAdminDTO;
import com.pda.entity.exceptions.NotFoundException;
import com.pda.entity.repository.ProgramAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.pda.entity.service.EntityService;
import com.pda.entity.service.ProgramAdminService;

@Service
public class ProgramAdminServiceImpl implements ProgramAdminService {

    @Autowired
    ProgramAdminRepository programAdminRepository;

    @Autowired
    EntityService entityService;

    @Override
    public Iterable<ProgramAdmin> convertAndSaveAll(List<String> userIds, Entity entity) {
        List<ProgramAdmin> programAdmins = new ArrayList<>();
        userIds.forEach(userId -> {
            ProgramAdmin admin = new ProgramAdmin();
            admin.setId(new ProgramAdminPk(userId, entity.getId()));
            admin.setEntity(entity);
            programAdmins.add(admin);
        });
        return programAdminRepository.saveAll(programAdmins);
    }

    @Override
    public Iterable<ProgramAdmin> addAdmins(ProgramAdminDTO programAdminDTO, BindingResult bindingResult) {
        Entity entity = entityService.getOne(programAdminDTO.getEntityId());

        if(entity == null) {
            throw new NotFoundException("Entity not found with ID "+programAdminDTO.getEntityId());
        }
        return convertAndSaveAll(programAdminDTO.getProgramAdmins(),entity);
    }

    @Override
    public List<ProgramAdmin> getByUserId(String userId) {
        return programAdminRepository.getByUserId(userId);
    }
    
    @Override
    public void deleteProgramAdmin(Long entityId, String userId) {
         programAdminRepository.deleteByEntityIdAndUserId(entityId, userId);
    }

	@Override
	public List<ProgramAdmin> getProgramAdminsByEntityId(Long entityId) {
		return programAdminRepository.getByEntityId(entityId);
	}
	
	@Override
	public void deleteProgramAdminsByEntityId(Long entityId) {
		programAdminRepository.deleteByEntityId(entityId);
	}
}
