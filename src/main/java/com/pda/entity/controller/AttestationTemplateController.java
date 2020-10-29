package com.pda.entity.controller;


import com.pda.entity.aws.AwsConfigService;
import com.pda.entity.dto.ResponseDTO;
import com.pda.entity.dto.TemplateDto;
import com.pda.entity.service.AttestationService;
import com.pda.entity.config.AppContext;
import com.pda.entity.utils.Constant;
import com.pda.entity.utils.HttpUtils;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/entity", produces = {"application/json"})
public class AttestationTemplateController {


    @Autowired
    private AwsConfigService awsConfigService;

    @Autowired
    private AppContext appContext;

    @Autowired
    private AttestationService attestationService;

    protected static final Logger logger = LoggerFactory.getLogger(AttestationTemplateController.class);
    private static final String ATTESTATION_PATH = "/attestation";


    @PostMapping("template/generate-multiple-attestation")
    public ResponseDTO generateMutipleAttestations(@RequestBody List<TemplateDto> templateDtos) throws IOException, DocumentException,InterruptedException {
        List<Map<String, String>> attestationResp = new ArrayList<>();
        for (TemplateDto dto : templateDtos) {
            ResponseDTO resp = createCertificate(dto);
            if (200 == resp.getResponseCode() && null != resp.getResponse() && !"".equals((String) resp.getResponse())) {
                Map<String, String> map = new HashMap<>();
                map.put("sessionId", dto.getSessionId().toString());
                map.put("userId", dto.getUserId());
                map.put("attestationUrl", (String) resp.getResponse());
                map.put("role", dto.getUserRole());
                attestationResp.add(map);
            }
        }
        return HttpUtils.onSuccess_(attestationResp, "Attestations Generated");
    }

    @PostMapping("template/generate-attestation")
    public ResponseDTO generateAttestations(@RequestBody TemplateDto templateDto) throws IOException, DocumentException {
        ResponseDTO resp = createCertificate(templateDto);
        Map<String, String> map = new HashMap<>();
        if (200 == resp.getResponseCode() && null != resp.getResponse() && !"".equals(resp.getResponse())) {
            map.put("sessionId", templateDto.getSessionId().toString());
            map.put("userId", templateDto.getUserId());
            map.put("attestationUrl", (String) resp.getResponse());
            map.put("role", templateDto.getUserRole());
        }
        return HttpUtils.onSuccess_(map, "Attestations Generated");
    }


    @PostMapping("template/get-users-attestation")
    public ResponseDTO createCertificate(@RequestBody TemplateDto templateDto) throws IOException, DocumentException, HeadlessException {
        String awsAttestationId = null;
        ResponseDTO responseDTO = new ResponseDTO();
        if (templateDto.getUserRole().equalsIgnoreCase(Constant.TRAINEE)) {
            awsAttestationId = templateDto.getSessionId() + Constant.TRAINEE + templateDto.getUserId();
        } else {
            awsAttestationId = templateDto.getSessionId() + templateDto.getUserRole() + templateDto.getUserId();
        }
        ResponseDTO responseDTO1 = attestationService.userAttestation(templateDto);
        try {
            AmazonS3 amazonS3 = awsConfigService.awsS3Configuration();
            logger.info("Attestation upload into aws starts...");
            awsConfigService.putObjectInAwsS3(responseDTO1.getResponse().toString(), awsAttestationId, amazonS3,ATTESTATION_PATH);
            String s3BucketUrl = appContext.getAwsS3Url() + "attestation/" + awsAttestationId;
            File htmlFile = new File(appContext.getFolderPath() + awsAttestationId + Constant.HTML_FORMAT);
            File pdfFile = new File(appContext.getFolderPath() + awsAttestationId + Constant.PDF_FORMAT);
            File qrcodeFile = new File(appContext.getFolderPath() + awsAttestationId + Constant.PNG_FORMAT);
            File pdfCropedFile = new File(appContext.getFolderPath() + awsAttestationId + "1" + Constant.PDF_FORMAT);

            if (htmlFile.delete() && pdfFile.delete() && pdfCropedFile.delete() && qrcodeFile.delete()) {
                logger.info("File deleted successfully");
            } else {
                logger.error("Failed to delete the file");
            }
            responseDTO.setResponse(s3BucketUrl);
            responseDTO.setMessage("SuccessFully uploaded Attestation to AWS S3");
            responseDTO.setResponseCode(HttpStatus.OK.value());
        } catch (AmazonServiceException e) {
            responseDTO.setResponse(e);
            responseDTO.setMessage("Issue with  upload Attestation to AWS S3");
            responseDTO.setResponseCode(HttpStatus.NOT_FOUND.value());
        } catch (SdkClientException e) {
            responseDTO.setResponse(e);
            responseDTO.setMessage("Issue with  upload Attestation to AWS S3");
            responseDTO.setResponseCode(HttpStatus.NOT_FOUND.value());
        }
        return responseDTO;
    }
}

