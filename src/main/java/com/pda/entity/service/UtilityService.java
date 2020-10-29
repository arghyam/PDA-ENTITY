package com.pda.entity.service;

import com.pda.entity.dto.EntityDTO;
import com.pda.entity.dto.RequestDTO;
import com.pda.entity.dto.ResponseDTO;
import org.springframework.validation.BindingResult;

public interface UtilityService {

    public ResponseDTO createEntity(EntityDTO requestDTO, BindingResult bindingResult);

    public ResponseDTO createProgrDBMulti(RequestDTO payload, BindingResult bindingResult);

}
