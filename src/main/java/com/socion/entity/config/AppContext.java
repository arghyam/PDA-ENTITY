package com.socion.entity.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {
    @Value("${aws-accesskey}")
    private String awsAccessKey;

    @Value("${aws-qrcode}")
    private String awsQrcode;

    @Value("${aws-secretkey}")
    private String awsSecretKey;

    @Value("${registry-base-url}")
    private String registryBaseUrl;

    @Value("${keycloak.auth-server-url}")
    private String keyCloakServiceUrl;

    @Value("${keycloak.realm}")
    private String realm;
    @Value("${admin-user-username}")
    private String adminUserName;

    @Value("${admin-user-password}")
    private String adminUserpassword;
    @Value("${keycloak-client-id}")
    private String clientId;
    
// Adding the client secret as a configurable property while authenticating towards keycloak

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    @Value("${client.granttype}")
    private String grantType;


    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }

    public String getAdminUserpassword() {
        return adminUserpassword;
    }

    public void setAdminUserpassword(String adminUserpassword) {
        this.adminUserpassword = adminUserpassword;
    }

    public String getKeyCloakServiceUrl() {
        return keyCloakServiceUrl;
    }

    public void setKeyCloakServiceUrl(String keyCloakServiceUrl) {
        this.keyCloakServiceUrl = keyCloakServiceUrl;
    }

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public String getRegistryBaseUrl() {
        return registryBaseUrl;
    }

    public void setRegistryBaseUrl(String registryBaseUrl) {
        this.registryBaseUrl = registryBaseUrl;
    }

    @Value("${aws-region}")
    private String awsRegion;

    @Value("${aws-s3-bucket-name}")
    private String awsS3BucketName;
    @Value("${aws-s3-url}")
    private String awsS3Url;

    @Value("${salt-value}")
    private String saltValue;

    @Value("${iv-value}")
    private String ivValue;

    @Value("${secret-key}")
    private String secretKey;

    @Value("${key-size}")
    private int keySize;

    @Value("${iteration-count}")
    private int iterationCount;

    @Value("${iam-baseurl}")
    private String iamBaseUrl;

    @Value("${file-folder-path}")
    private String folderPath;

    @Value("${member-attestation-template-path}")
    private String memberAttestationTemplatePath;

    @Value("${trainee-attestation-template-path}")
    private String tranieeAttestationTemplatePath;

    @Value("${profile-front-template-path}")
    private String profileTemplatePath;

    public String getAwsS3Url() {
        return awsS3Url;
    }

    public String getAwsQrcode() {
        return awsQrcode;
    }

    public void setAwsQrcode(String awsQrcode) {
        this.awsQrcode = awsQrcode;
    }

    public void setAwsS3Url(String awsS3Url) {
        this.awsS3Url = awsS3Url;
    }

    public String getAwsAccessKey() {
        return awsAccessKey;
    }

    public void setAwsAccessKey(String awsAccessKey) {
        this.awsAccessKey = awsAccessKey;
    }

    public String getAwsSecretKey() {
        return awsSecretKey;
    }

    public void setAwsSecretKey(String awsSecretKey) {
        this.awsSecretKey = awsSecretKey;
    }

    public String getAwsRegion() {
        return awsRegion;
    }

    public void setAwsRegion(String awsRegion) {
        this.awsRegion = awsRegion;
    }

    public String getAwsS3BucketName() {
        return awsS3BucketName;
    }

    public void setAwsS3BucketName(String awsS3BucketName) {
        this.awsS3BucketName = awsS3BucketName;
    }

    public String getSaltValue() {
        return saltValue;
    }

    public void setSaltValue(String saltValue) {
        this.saltValue = saltValue;
    }

    public String getIvValue() {
        return ivValue;
    }

    public void setIvValue(String ivValue) {
        this.ivValue = ivValue;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public int getKeySize() {
        return keySize;
    }

    public void setKeySize(int keySize) {
        this.keySize = keySize;
    }

    public int getIterationCount() {
        return iterationCount;
    }

    public void setIterationCount(int iterationCount) {
        this.iterationCount = iterationCount;
    }

    public String getIamBaseUrl() {
        return iamBaseUrl;
    }

    public void setIamBaseUrl(String iamBaseUrl) {
        this.iamBaseUrl = iamBaseUrl;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public String getMemberAttestationTemplatePath() {
        return memberAttestationTemplatePath;
    }

    public void setMemberAttestationTemplatePath(String memberAttestationTemplatePath) {
        this.memberAttestationTemplatePath = memberAttestationTemplatePath;
    }

    public String getTranieeAttestationTemplatePath() {
        return tranieeAttestationTemplatePath;
    }

    public void setTranieeAttestationTemplatePath(String tranieeAttestationTemplatePath) {
        this.tranieeAttestationTemplatePath = tranieeAttestationTemplatePath;
    }

    public String getProfileTemplatePath() {
        return profileTemplatePath;
    }

    public void setProfileTemplatePath(String profileTemplatePath) {
        this.profileTemplatePath = profileTemplatePath;
    }
}
