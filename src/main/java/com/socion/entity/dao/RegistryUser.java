package com.socion.entity.dao;

public class RegistryUser {

    private String name;

    private String emailId;

    private String salutation;

    private String phoneNumber;

    private String photo;

    private String userId;


    private String crtdDttm;

    private String updtDttm;

    private String osid;

    private boolean active;

    private String countryCode;



    public RegistryUser() {
    }

    private String profileCardUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCrtdDttm() {
        return crtdDttm;
    }

    public void setCrtdDttm(String crtdDttm) {
        this.crtdDttm = crtdDttm;
    }

    public String getUpdtDttm() {
        return updtDttm;
    }

    public void setUpdtDttm(String updtDttm) {
        this.updtDttm = updtDttm;
    }

    public String getOsid() {
        return osid;
    }

    public void setOsid(String osid) {
        this.osid = osid;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getProfileCardUrl() {
        return profileCardUrl;
    }

    public void setProfileCardUrl(String profileCardUrl) {
        this.profileCardUrl = profileCardUrl;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public RegistryUser(String name, String emailId, String salutation, String phoneNumber, String photo, String userId,
                        String crtdDttm, String updtDttm, String osid, boolean active, String countryCode, String profileCardUrl) {
        this.name = name;
        this.emailId = emailId;
        this.salutation = salutation;
        this.phoneNumber = phoneNumber;
        this.photo = photo;
        this.userId = userId;
        this.crtdDttm = crtdDttm;
        this.updtDttm = updtDttm;
        this.osid = osid;
        this.active = active;
        this.countryCode = countryCode;
        this.profileCardUrl = profileCardUrl;
    }


}
