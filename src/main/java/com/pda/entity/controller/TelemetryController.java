package com.pda.entity.controller;

import com.pda.entity.dao.Telemetry;
import com.pda.entity.service.TelemetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/entity")
public class TelemetryController {

    @Autowired
    TelemetryService telemetryService;

    @PostMapping(value = "/telemetry")
    public Telemetry add(@Validated @RequestBody Telemetry topicDTO, BindingResult bindingResult) {
        return telemetryService.create(topicDTO,bindingResult);
    }

    @PostMapping(value = "/list/telemetry")
    public List<Telemetry> addMultiple(@Validated @RequestBody List<Telemetry> topicDTO, BindingResult bindingResult) {
        return telemetryService.createMultiple(topicDTO,bindingResult);


    }
}
