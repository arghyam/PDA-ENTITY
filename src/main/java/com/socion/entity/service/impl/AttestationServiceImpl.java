package com.socion.entity.service.impl;

import com.socion.entity.config.AppContext;
import com.socion.entity.dto.ResponseDTO;
import com.socion.entity.dto.TemplateDto;
import com.socion.entity.service.AttestationService;
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

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@SuppressWarnings("unchecked")
public class AttestationServiceImpl implements AttestationService {

    @Autowired
    AppContext appContext;

    private static final Logger LOGGER = LoggerFactory.getLogger(AttestationServiceImpl.class);
    private static final String ATTESTATION_START_MESSAGE = "Attestation begin for the user {} for the role {}";

    /**
     *
     * @param templateDto
     * @return
     * @throws IOException
     */
    @Override
    public ResponseDTO userAttestation(TemplateDto templateDto) throws IOException {

        LOGGER.info(ATTESTATION_START_MESSAGE , templateDto.getUserId(), templateDto.getUserRole());
        ResponseDTO responseDTO = new ResponseDTO();

        File htmlTemplateFile = getTemplateFile(templateDto.getUserRole());

        String htmlString = FileUtils.readFileToString(htmlTemplateFile, StandardCharsets.UTF_8);
        String studentName = templateDto.getName();
        String sessionName = templateDto.getSessionName();
        String courseName = templateDto.getProgramName();
        String profilePicPath = templateDto.getProfilePicPath();
        String entityName=templateDto.getEntityName();
        String noOfParticipants = Integer.toString(templateDto.getNoOfParticipants());
        String dt = "";
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(templateDto.getSessionEndDate());
            dt = simpleDateFormat.format(date);
        } catch (ParseException e) {
            LOGGER.error("Error logged as ", e);
        }

        String role = templateDto.getUserRole();

        String userRoleValue;

        if (role.equalsIgnoreCase(Constant.TRAINEE)) {
            userRoleValue = Constant.TRAINEE;
            htmlString = htmlString.replace("$role", "Trainee");
        } else {
            userRoleValue = role;
            htmlString = htmlString.replace("$role", role);
        }
        String awsAttestationId = templateDto.getSessionId() + userRoleValue + templateDto.getUserId();


        //TODO move profile picture url to props
        if (profilePicPath == null || profilePicPath.length() < 1) {
            profilePicPath = "Dummy profile picture url";
        }

        htmlString = htmlString.replace("$entityName", entityName);
        htmlString = htmlString.replace("$profilePic", profilePicPath);
        htmlString = htmlString.replace("$studentName", studentName);
        if (dt != (null)) {
            htmlString = htmlString.replace("$dt", dt);
        }
        if (!templateDto.getUserRole().equalsIgnoreCase(Constant.TRAINEE)) {
            htmlString = htmlString.replace("$noOfParticipants", noOfParticipants);
        }
        htmlString = htmlString.replace("$courseName", courseName);
        htmlString = htmlString.replace("$sessionName", sessionName);

        String awsUrl = appContext.getAwsQrcode() + templateDto.getSessionId() + "/" + templateDto.getUserId() + "/" + userRoleValue;

        ResponseDTO responseDTO1 = generateQrCodeOfAttestation(awsUrl, awsAttestationId);


        if (responseDTO1.getResponseCode() == 200) {

            htmlString = htmlString.replace("$qrcodepath", responseDTO1.getResponse().toString());

        }

        File newHtmlFile = new File(appContext.getFolderPath() + awsAttestationId + Constant.HTML_FORMAT);
        LOGGER.info("Created Attestation in html format");
        FileUtils.writeStringToFile(newHtmlFile, htmlString, "UTF-8");
        String src = appContext.getFolderPath() + awsAttestationId + Constant.HTML_FORMAT;
        String dest = appContext.getFolderPath() + awsAttestationId + Constant.PDF_FORMAT;
        String cropPdfDest = appContext.getFolderPath() + awsAttestationId + "1" + Constant.PDF_FORMAT;
        HtmlConverter.convertToPdf(new File(src), new File(dest));
        LOGGER.info("Successfully converted HTML to PDF");
        String pathOfCropedPDF = "";
        pathOfCropedPDF = PdfUtils.cropPdf(dest, cropPdfDest, 50, 30, -100, 570);

        if (!pathOfCropedPDF.isEmpty() || !pathOfCropedPDF.equals("")) {
            responseDTO.setResponseCode(HttpStatus.SC_OK);
            responseDTO.setMessage("SuccessFully Generated attestation file...  ");
            responseDTO.setResponse(pathOfCropedPDF);
        } else {
            responseDTO.setResponseCode(HttpStatus.SC_BAD_REQUEST);
            responseDTO.setMessage("Problem with Generating Attestation file...  ");
        }
        return responseDTO;
    }

    public File getTemplateFile(String role) {
        if (role.equalsIgnoreCase(Constant.TRAINEE)) {
            return new File(appContext.getTranieeAttestationTemplatePath());
        } else {
            return new File(appContext.getMemberAttestationTemplatePath());
        }
    }

    /**
     *
     * @param awsUrl
     * @param awsAttestationId
     * @return
     */
    private ResponseDTO generateQrCodeOfAttestation(String awsUrl, String awsAttestationId) {
        ResponseDTO responseDTO = new ResponseDTO();
        ByteArrayOutputStream bout =
                QRCode.from(awsUrl)
                        .withSize(250, 250)
                        .to(ImageType.PNG)
                        .stream();

        try {
            OutputStream out = new FileOutputStream(appContext.getFolderPath() + awsAttestationId + ".png");
            bout.writeTo(out);
            out.flush();
            out.close();
            responseDTO.setResponse(appContext.getFolderPath() + awsAttestationId + ".png");
            responseDTO.setMessage("Successfully created Qr Code");
            responseDTO.setResponseCode(HttpStatus.SC_OK);

        } catch (FileNotFoundException e) {
            responseDTO.setMessage("There is issue during creation Qr Code");
            responseDTO.setResponseCode(HttpStatus.SC_BAD_REQUEST);
        } catch (IOException e) {
            LOGGER.error("Error logged as ", e);
        }

        return responseDTO;
    }


}

// Reading the email templates failed since they couldn’t be accessed at runtime. This change is to fix this.

