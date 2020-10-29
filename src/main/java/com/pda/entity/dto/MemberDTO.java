package com.pda.entity.dto;

public class MemberDTO {

    private String userId;
    private String encryptedUserId;
    private String role;
    private String name;
    private String photo;
    private String profileCardUrl;
    private boolean deactivated;
    private String emailId;
    private String phoneNumber;


    public MemberDTO(String userId, String encryptedUserId, String role, String name, String photo, String profileCardUrl, boolean deactivated, String emailId, String phoneNumber) {
        this.userId = userId;
        this.encryptedUserId = encryptedUserId;
        this.role = role;
        this.name = name;
        this.photo = photo;
        this.profileCardUrl = profileCardUrl;
        this.deactivated = deactivated;
        this.emailId = emailId;
        this.phoneNumber = phoneNumber;
    }

    public MemberDTO() {
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isDeactivated() {
        return deactivated;
    }

    public void setDeactivated(boolean deactivated) {
        this.deactivated = deactivated;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getProfileCardUrl() {
        return profileCardUrl;
    }

    public void setProfileCardUrl(String profileCardUrl) {
        this.profileCardUrl = profileCardUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEncryptedUserId() {
        return encryptedUserId;
    }

    public void setEncryptedUserId(String encryptedUserId) {
        this.encryptedUserId = encryptedUserId;
    }
}
