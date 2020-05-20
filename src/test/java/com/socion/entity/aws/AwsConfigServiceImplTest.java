package com.socion.entity.aws;

import com.socion.entity.aws.AwsConfigServiceImpl;
import com.socion.entity.config.AppContext;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Ignore
@RunWith(MockitoJUnitRunner.class)
public class AwsConfigServiceImplTest {

    @InjectMocks
    AwsConfigServiceImpl awsConfigService;

    private static final String BUCKET_NAME = "Socion";
    private static final String REGION = "south-asia";
    private static final String SECRET = "secret";
    private static final String ACCESS_TOKEN = "access-token";
    private static final String URL = "{URL}";
    private static final String PATH_TO_FILE = "/home";
    private static final String USER_ID = "3042f443-6eed-4142-a32f-b693973b1905";

    @Mock
    AppContext appContext;

    @Mock
    AmazonS3 amazonS3;

    @Mock
    PutObjectResult putObjectResult;

    @Test
    public void awsS3ConfigurationTest() {
        when(appContext.getAwsAccessKey()).thenReturn(ACCESS_TOKEN);
        when(appContext.getAwsSecretKey()).thenReturn(SECRET);
        when(appContext.getAwsRegion()).thenReturn(REGION);
        AmazonS3 amazonS3 = awsConfigService.awsS3Configuration();
        assertEquals(amazonS3.getUrl(BUCKET_NAME,BUCKET_NAME).toString(),URL);
    }

    @Test
    public void putObjectInAwsS3Test() {
        when(appContext.getAwsS3BucketName()).thenReturn(BUCKET_NAME);
        when(amazonS3.putObject(any())).thenReturn(putObjectResult);
        PutObjectResult putObjectResult = awsConfigService.putObjectInAwsS3(PATH_TO_FILE,USER_ID,amazonS3,PATH_TO_FILE);
        System.out.println(putObjectResult.getVersionId());
    }
}
