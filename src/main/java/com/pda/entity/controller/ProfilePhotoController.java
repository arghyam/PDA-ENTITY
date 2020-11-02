package com.pda.entity.controller;


import com.pda.entity.aws.AwsConfigService;
import com.pda.entity.dto.ProfileTemplateDto;
import com.pda.entity.dto.ResponseDTO;
import com.pda.entity.service.ProfileService;
import com.pda.entity.config.AppContext;
import com.pda.entity.utils.Constant;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/", produces = {"application/json"})
public class ProfilePhotoController {

    @Autowired
    AwsConfigService awsConfigService;

    @Autowired
    AppContext appContext;

    @Autowired
    ProfileService profileService;

    protected static final Logger LOGGER = LoggerFactory.getLogger(ProfilePhotoController.class);
    private static final String PROFILE_PATH ="/profile-card";


    @PostMapping("template/get-user-profile-card")
    public ResponseDTO generateProfileCardForUser(@RequestBody ProfileTemplateDto templateDto) throws Exception {
        ResponseDTO responseDTO = new ResponseDTO();

        String picId = templateDto.getUserId() + "_PROFILE_" + UUID.randomUUID().toString();
        ResponseDTO responseDTO1 = profileService.generateProfileCard(templateDto,picId);

        try {
            AmazonS3 amazonS3 = awsConfigService.awsS3Configuration();
            awsConfigService.putObjectInAwsS3(responseDTO1.getResponse().toString(), templateDto.getUserId(), amazonS3,PROFILE_PATH);
            String s3BucketUrl = appContext.getAwsS3UrlPrivate() + "profile-card/" + templateDto.getUserId();
            File htmlFile = new File(appContext.getFolderPath() + templateDto.getUserId() + Constant.HTML_FORMAT);
            File pdfFile = new File(appContext.getFolderPath() + templateDto.getUserId() + Constant.PDF_FORMAT);
            File qrcodeFile = new File(appContext.getFolderPath() + picId + Constant.PNG_FORMAT);
            File croppedPdf = new File(appContext.getFolderPath() + templateDto.getUserId() + "cropped" + Constant.PDF_FORMAT);

            if (htmlFile.delete() && pdfFile.delete() && qrcodeFile.delete() && croppedPdf.delete()) {
                LOGGER.info("File deleted successfully");
            } else {
                LOGGER.error("Failed to delete the file");
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
