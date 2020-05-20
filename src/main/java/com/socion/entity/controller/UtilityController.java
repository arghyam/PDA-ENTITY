package com.socion.entity.controller;

import com.socion.entity.service.UtilityService;
import com.socion.entity.dto.EntityDTO;
import com.socion.entity.dto.RequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socion.entity.dto.ResponseDTO;

@RestController
@RequestMapping("/api/v1/entity/utility")
public class UtilityController {
	
	@Autowired
	UtilityService utilityService;
	

	@PostMapping(path="/entity", produces = {"application/json"}, consumes = {"application/json"})
	public ResponseDTO createEntity(@Validated @RequestBody EntityDTO payload,BindingResult bindingResult) {
		return  utilityService.createEntity(payload,bindingResult);
	}
	
	
	@PostMapping(path="/createProgramsDB-multiple-prog", produces = {"application/json"}, consumes = {"application/json"} )
    public ResponseDTO createProgramsDBMulti(@Validated @RequestBody(required = false) RequestDTO payload, BindingResult bindingResult) {
    	return utilityService.createProgrDBMulti(payload,bindingResult);
    }
}
