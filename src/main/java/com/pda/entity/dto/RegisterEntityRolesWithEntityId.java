package com.pda.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterEntityRolesWithEntityId {
    private String entityidStr;
@JsonProperty(value="user_id")
    private String userid;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getEntityidStr() {
        return entityidStr;
    }

    public void setEntityidStr(String entityidStr) {
        this.entityidStr = entityidStr;
    }
}
