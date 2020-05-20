package com.socion.entity.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;

public interface AwsConfigService {


    public AmazonS3 awsS3Configuration();

    public PutObjectResult putObjectInAwsS3(String pathOfCertificate, String userId, AmazonS3 amazonS3,String path);

}

