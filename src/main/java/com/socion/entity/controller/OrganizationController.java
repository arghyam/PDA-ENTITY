package com.socion.entity.controller;

import com.socion.entity.dao.Attestation;
import com.socion.entity.dao.Organization;
import com.socion.entity.dto.AttestationDTO;
import com.socion.entity.dto.OrganizationDTO;
import com.socion.entity.dto.ResponseDTO;
import com.socion.entity.dto.SlimOrganizationDTO;
import com.socion.entity.repository.AttestationRepository;
import com.socion.entity.repository.OrganizationRepository;
import com.socion.entity.utils.HttpUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/v1/entity", produces = {"application/json"})
public class OrganizationController {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private AttestationRepository attestationRepository;

    @Autowired
    private ModelMapper modelMapper;

    protected static final Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);

    @GetMapping("/orgs")
    public List<SlimOrganizationDTO> getAllOrganisations() {
        List<Organization> organizations = organizationRepository.findAll();
        List<SlimOrganizationDTO> slimOrganizationDTOS = new ArrayList<>();
        organizations.forEach(organization -> {
            slimOrganizationDTOS.add(convertEntityToDto(organization));
        });
        return slimOrganizationDTOS;
    }

    @GetMapping("/orgs/{id}")
    public OrganizationDTO getOrganizationAndAssociatedAttestations(@PathVariable("id") int id) {
        Organization organization = organizationRepository.findById(id).get();
        OrganizationDTO organizationDTO = new OrganizationDTO();
        organizationDTO.setOrgId(organization.getOrgId());
        organizationDTO.setOrgName(organization.getOrgName());
        List<AttestationDTO> memberAttestations = new ArrayList<>();
        List<AttestationDTO> traineeAttestations = new ArrayList<>();
        organization.getAttestations().forEach(attestation -> {
            AttestationDTO attestationDTO = convertAttestationEntityToDto(attestation);
            if (attestationDTO.getAttestationType().equalsIgnoreCase("trainee")) {
                traineeAttestations.add(attestationDTO);
            } else {
                memberAttestations.add(attestationDTO);
            }
        });
        organizationDTO.setMemberAttestations(memberAttestations);
        organizationDTO.setTraineeAttestations(traineeAttestations);
        return organizationDTO;
    }

    @GetMapping("/orgs/names/{ids}")
    public ResponseDTO getOrganisationNames(@PathVariable("ids") List<Integer> ids) {
        List<Organization> organizations = organizationRepository.findByOrgIdIn(ids);
        List<OrganizationDTO> organizationDTOs = new ArrayList<>();
        for (Organization organization : organizations) {
            organizationDTOs.add(new OrganizationDTO(organization.getOrgId(), organization.getOrgName()));
        }
        return HttpUtils.onSuccess_(organizationDTOs, "Names for provided Org Ids");
    }

    @PostMapping("/orgs")
    public void addOrganization(@RequestBody Organization organization) {
        Set<Attestation> allAttestations = organization.getAttestations();
        Organization persistedOrg = organizationRepository.save(organization);
        for (Attestation attestation : allAttestations) {
           // attestation.setOrganization(persistedOrg);
        }
        attestationRepository.saveAll(allAttestations);

    }

    @PutMapping("/orgs/{id}")
    public void updateOrganization(@RequestParam("id") int id, @RequestBody Organization updatedOrganization) {
        Organization existingOrganization = organizationRepository.findById(id).get();
        if (existingOrganization == null) {
            LOGGER.info("Organization does not exist");
        } else {
            updateOrganizationEntity(existingOrganization, updatedOrganization);
            organizationRepository.save(updatedOrganization);
        }
    }


    private void updateOrganizationEntity(Organization existingOrganization, Organization updatedOrganization) {

        if (updatedOrganization.getOrgId() == 0) {
            updatedOrganization.setOrgId(existingOrganization.getOrgId());
        }
    }

    private SlimOrganizationDTO convertEntityToDto(Organization organization) {
        return modelMapper.map(organization, SlimOrganizationDTO.class);
    }

    private AttestationDTO convertAttestationEntityToDto(Attestation attestation) {
        return  modelMapper.map(attestation, AttestationDTO.class);
    }

}

