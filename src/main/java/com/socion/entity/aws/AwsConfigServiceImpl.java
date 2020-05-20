package com.socion.entity.aws;

import com.socion.entity.config.AppContext;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 *
 */
@Component
@Service
public class AwsConfigServiceImpl implements AwsConfigService {

    @Autowired
    AppContext appContext;

    private static final String X_AMZ_META_TITLE = "x-amz-meta-title";
    private static final String USER_CARD = "UserCard";

    public AmazonS3 awsS3Configuration() {
        AWSCredentials credentials = new BasicAWSCredentials(
                appContext.getAwsAccessKey(),
                appContext.getAwsSecretKey()
        );
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(appContext.getAwsRegion())
                .build();
    }

    @Override
    public PutObjectResult putObjectInAwsS3(String pathOfCertificate, String userId, AmazonS3 amazonS3,String path) {

        PutObjectResult putObjectResult;
        String bucketName = appContext.getAwsS3BucketName();
        PutObjectRequest request1 = new PutObjectRequest(bucketName + path, userId, new File(pathOfCertificate));
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.addUserMetadata(X_AMZ_META_TITLE, USER_CARD);
        request1.setMetadata(metadata);
        putObjectResult = amazonS3.putObject(request1);
        return putObjectResult;
    }
}
