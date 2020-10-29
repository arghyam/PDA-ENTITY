package com.pda.entity.service.impl;

import com.pda.entity.dto.ResponseDTO;
import com.pda.entity.config.AppContext;
import com.pda.entity.utils.Constant;
import com.pda.entity.utils.FileUtils;
import com.pda.entity.utils.HttpUtils;
import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

@Service
public class S3Uploader {



    private static final Logger LOGGER = LoggerFactory.getLogger(S3Uploader.class);

    @Autowired
    private AmazonS3 amazonClient;

    @Autowired
    private AppContext appContext;

    public ResponseDTO uploadContentToS3(MultipartFile file, String contentType) {
        if (null != file) {
            if (FileUtils.restrictFileType(file)) {
                TransferManager tm = TransferManagerBuilder.standard().withS3Client(amazonClient)
                        .withMultipartUploadThreshold((long) (5 * 1024 * 1025)).build();
                String uploadFileName = file.getOriginalFilename() + "_" + System.currentTimeMillis();
                File nwfile = new File(uploadFileName);
                try {
                    nwfile.createNewFile();
                    FileOutputStream fileOutputStream = new FileOutputStream(nwfile);
                    fileOutputStream.write(file.getBytes());
                    fileOutputStream.close();
                } catch (IOException e) {
                    LOGGER.error("File Conversion Failed:{} " ,e.toString());
                }

                try {
                    String folder = Constant.CONTENT_TYPE_VIDEO.equalsIgnoreCase(contentType) ? Constant.CONTENT_VIDEOS : Constant.CONTENT;
                    Upload upload = tm.upload(appContext.getAwsS3BucketName() + "/" + folder, uploadFileName,
                            nwfile);
                    LOGGER.info(Constant.UPLD_START);
                    upload.waitForCompletion();
                    LOGGER.info(Constant.UPLD_COMPLETE);
                    URL url = amazonClient.getUrl(appContext.getAwsS3BucketName(), folder + "/" + uploadFileName);
                    return HttpUtils.onSuccess(null != url ? url.getFile() : null, Constant.UPLD_COMPLETE);
                } catch (AmazonClientException | InterruptedException e) {
                    LOGGER.error(Constant.UPLD_FAIL + ": " + e.toString());
                    return HttpUtils.onFailure(500, "Upload File Failed: " + Constant.FAIL_S3);
                }
            }
            return HttpUtils.onFailure(400, Constant.FILE_TYPE_NOT_SUPPORTED);
        }
        return HttpUtils.onFailure(400, Constant.PARAMETER_FILE_IS_NOT_PRESENT);
    }


}
