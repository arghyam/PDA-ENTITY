package com.socion.entity.service.impl;

import com.socion.entity.config.AppContext;
import com.socion.entity.dto.ProfileTemplateDto;
import com.socion.entity.dto.ResponseDTO;
import com.socion.entity.service.ProfileService;
import com.socion.entity.utils.AesUtil;
import com.socion.entity.utils.Constant;
import com.socion.entity.utils.PdfUtils;
import com.itextpdf.html2pdf.HtmlConverter;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import java.io.*;

@Service
public class ProfileServiceImpl implements ProfileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileServiceImpl.class);
    //TODO move profile picture url to props
    private static final String PROFILE_BACKGROUND_IAMGE = "Dummy profile picture url";
    @Autowired
    AppContext appContext;

    @Override
    public ResponseDTO generateProfileCard(ProfileTemplateDto templateDto,String picId) throws Exception {
        ResponseDTO responseDTO = new ResponseDTO();


        //File htmlTemplateFile = ResourceUtils.getFile("classpath:templates/"+appContext.getProfileTemplatePath());

        //String htmlString = FileUtils.readFileToString(htmlTemplateFile, "UTF-8");

	Resource resource = new ClassPathResource("templates/"+appContext.getProfileTemplatePath());

	InputStream input = resource.getInputStream();

	String htmlString = new BufferedReader(
                new InputStreamReader(input, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));

        String name = templateDto.getUserName();
        String photo = templateDto.getPhoto();
        String userId = templateDto.getUserId();

        if (photo.length() < 1) {
            photo = PROFILE_BACKGROUND_IAMGE;
        }


        htmlString = htmlString.replace("$profilePicPath", photo);
        htmlString = htmlString.replace("$name", name);

        ResponseDTO responseDTO1 = generateQrCodeOfAttestation(templateDto.getUserId(), picId);


        if (responseDTO1.getResponseCode() == 200) {
            htmlString = htmlString.replace("$qrcodepath", responseDTO1.getResponse().toString());
        }

        File newHtmlFile = new File(appContext.getFolderPath() + userId + Constant.HTML_FORMAT);

        FileUtils.writeStringToFile(newHtmlFile, htmlString, "UTF-8");

        String src = appContext.getFolderPath() + userId + Constant.HTML_FORMAT;
        String dest = appContext.getFolderPath() + userId + Constant.PDF_FORMAT;
        String cropPdfDest = appContext.getFolderPath() + userId + "cropped" + Constant.PDF_FORMAT;
        HtmlConverter.convertToPdf(new File(src), new File(dest));

        LOGGER.info("Successfully converted HTML to PDF");

        String pathOfCropedPDF = "";
        pathOfCropedPDF = PdfUtils.cropPdf(dest, cropPdfDest, 21, 20, 0, 619);
        if (!pathOfCropedPDF.isEmpty() || !pathOfCropedPDF.equals("")) {
            responseDTO.setResponseCode(HttpStatus.SC_OK);
            responseDTO.setMessage("SuccessFully Generated Profile card file...  ");
            responseDTO.setResponse(pathOfCropedPDF);
        } else {
            responseDTO.setResponseCode(HttpStatus.SC_BAD_REQUEST);
            responseDTO.setMessage("Problem with Generating Profile card file...  ");
        }

        return responseDTO;
    }

    private ResponseDTO generateQrCodeOfAttestation(String userId, String picId) {

        AesUtil aesUtil = new AesUtil(appContext.getKeySize(), appContext.getIterationCount());
        String encryptedUserId = aesUtil.encrypt(appContext.getSaltValue(), appContext.getIvValue(),
                appContext.getSecretKey(), userId);

        ResponseDTO responseDTO = new ResponseDTO();
        ByteArrayOutputStream bout =
                QRCode.from(encryptedUserId)
                        .withSize(250, 250)
                        .to(ImageType.PNG)
                        .stream();

        try {
            OutputStream out = new FileOutputStream(appContext.getFolderPath() + picId + ".png");
            bout.writeTo(out);
            out.flush();
            out.close();
            responseDTO.setResponse(appContext.getFolderPath() + picId + ".png");
            responseDTO.setMessage("Successfully created Qr Code");
            responseDTO.setResponseCode(HttpStatus.SC_OK);

        } catch (FileNotFoundException e) {
            responseDTO.setMessage("There is issue during creation Qr Code");
            responseDTO.setResponseCode(HttpStatus.SC_BAD_REQUEST);
        } catch (IOException e) {
            LOGGER.info("EXCEPTION:{}", e.getMessage());
        }

        return responseDTO;

    }

}
