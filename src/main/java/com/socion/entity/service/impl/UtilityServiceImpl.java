package com.socion.entity.service.impl;

import com.socion.entity.dao.Entity;
import com.socion.entity.dto.EntityDTO;
import com.socion.entity.dto.ProgramDTO;
import com.socion.entity.dto.RequestDTO;
import com.socion.entity.dto.ResponseDTO;
import com.socion.entity.exceptions.ValidationError;
import com.socion.entity.service.*;
import com.socion.entity.utils.HttpUtils;
import com.socion.entity.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Service
public class UtilityServiceImpl implements UtilityService {

    @Autowired
    private ProgramService programService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private ContentService contentService;

    @Autowired
    private ProgramAdminService programAdminService;

    @Autowired
    private ProgramRolesService programRolesService;

    @Autowired
    private EntityService entityService;


    @Override
    public ResponseDTO createEntity(EntityDTO requestDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return valiadtePojo(bindingResult);
        }
        Entity savedEntity = entityService.convertAndSave(requestDTO.getName(), requestDTO.getDescription());

        if (requestDTO.getProgramAdmin() != null) {
            programAdminService.convertAndSaveAll(requestDTO.getProgramAdmin(), savedEntity);
        }
        return HttpUtils.onSuccess_("Successfully Created", "Success");
    }

    public ResponseDTO createProgrDBMulti(RequestDTO payload, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return valiadtePojo(bindingResult);
        }

        List<ProgramDTO> programs = payload.getPrograms();
        Entity savedEntity = entityService.convertAndSave(payload.getName(), payload.getDescription());
        if (payload.getProgramAdmins() != null && payload.getProgramAdmins().size() > 0) {
            programAdminService.convertAndSaveAll(payload.getProgramAdmins(), savedEntity);
        }
        for (ProgramDTO pro : programs) {
            createProgrDB(savedEntity, pro);
        }
        return HttpUtils.onSuccess_("Saved Programs Topic & Content", "SUCCESS");
    }

    private ResponseDTO createProgrDB(Entity entity, ProgramDTO payload) {
        return HttpUtils.onSuccess_("Saved Program Topic & Content", "SUCCESS");
    }

    public ResponseDTO valiadtePojo(BindingResult bindingResult) {
        List<ValidationError> errorList = new ArrayList<>();

        for (FieldError error : bindingResult.getFieldErrors()) {
            ValidationError validationError = new ValidationError(error.getField(), error.getDefaultMessage());
            errorList.add(validationError);
        }
        return HttpUtils.onFailure(422, "SUCCESS", errorList);
    }
}
