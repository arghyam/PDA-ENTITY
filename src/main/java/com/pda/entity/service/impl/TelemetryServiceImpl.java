package com.pda.entity.service.impl;

import com.pda.entity.dao.Telemetry;
import com.pda.entity.repository.TelemetryRepository;
import com.pda.entity.service.TelemetryService;
import com.pda.entity.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class TelemetryServiceImpl implements TelemetryService {

    @Autowired
    TelemetryRepository telemetryRepository;

    @Override
    public Telemetry create(Telemetry telemetry, BindingResult bindingResult) {
        ValidationUtil.valiadtePojo(bindingResult);

        if (telemetry.getRole() == null) {
            telemetry.setTraineerole(telemetry.getRole());
            telemetry.setTrainerrole(telemetry.getRole());
        } else if (telemetry.getRole().equals("TRAINER")) {
            telemetry.setTrainerrole(telemetry.getRole());
        } else if (telemetry.getRole().equals("OTHER")) {
            telemetry.setTraineerole(telemetry.getRole());
        } else if (telemetry.getRole().equals("TRAINEE")) {
            telemetry.setTraineerole(telemetry.getRole());
        }
        return telemetryRepository.save(telemetry);
    }

    @Override
    public List<Telemetry> createMultiple(List<Telemetry> telemetry, BindingResult bindingResult) {
        ValidationUtil.valiadtePojo(bindingResult);
        return telemetryRepository.saveAll(telemetry);
    }
}
