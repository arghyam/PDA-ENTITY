package com.socion.entity.controller;

import com.socion.entity.dao.Attestation;
import com.socion.entity.dto.AttestationDTO;
import com.socion.entity.dto.ResponseDTO;
import com.socion.entity.repository.AttestationRepository;
import org.apache.http.HttpStatus;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/")
public class AttestationController {

    @Autowired
    AttestationRepository attestationRepository;

    @Autowired
    ModelMapper modelMapper;

    public static final Logger LOGGER = LoggerFactory.getLogger(AttestationController.class);


    @PostMapping("/attestations")
    public ResponseDTO addAttestation(@RequestBody Attestation attestation) {
        ResponseDTO responseDTO = new ResponseDTO();
        Attestation saveAttestation = attestationRepository.save(attestation);
        responseDTO.setMessage("successfully created attestation");
        responseDTO.setResponse(saveAttestation.getAttestationId());
        responseDTO.setResponseCode(HttpStatus.SC_OK);
        LOGGER.info("successfully created attestation");
        return responseDTO;
    }

    @GetMapping("/attestations")
    public List<AttestationDTO> getAllAttestations() {
        List<Attestation> attestations = attestationRepository.findAll();
        List<AttestationDTO> attestationDTOS = new ArrayList<>();
        attestations.forEach(attestation -> {
            AttestationDTO attestationDTO = convertEntityToDto(attestation);
            attestationDTOS.add(attestationDTO);
        });
        return attestationDTOS;
    }

    @GetMapping("/attestations/{id}")
    public AttestationDTO getAttestation(@PathVariable("id") int id) {
        Attestation attestation = attestationRepository.getOne(id);
        return convertEntityToDto(attestation);
    }

    @GetMapping("/attestations/org")
    public List<AttestationDTO> getAttestationsForOrganization(@RequestParam("orgId") int orgId) {
        List<Attestation> attestations = attestationRepository.findAttestationsByOrgId(orgId);
        List<AttestationDTO> attestationDTOS = new ArrayList<>();
        attestations.forEach(attestation -> {
            AttestationDTO attestationDTO = convertEntityToDto(attestation);
            attestationDTOS.add(attestationDTO);
        });
        return attestationDTOS;
    }


    private AttestationDTO convertEntityToDto(Attestation attestation) {
        return modelMapper.map(attestation, AttestationDTO.class);
    }

}
