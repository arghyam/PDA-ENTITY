package com.socion.entity.service;

import com.socion.entity.dao.Telemetry;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface TelemetryService {

    Telemetry create(Telemetry telemetry, BindingResult bindingResult);

    List<Telemetry> createMultiple(List<Telemetry> telemetry, BindingResult bindingResult);
}
