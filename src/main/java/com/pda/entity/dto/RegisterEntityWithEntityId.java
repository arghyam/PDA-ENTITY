package com.pda.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterEntityWithEntityId {
    private String entityidStr;

    public String getEntityidStr() {
        return entityidStr;
    }

    public void setEntityidStr(String entityidStr) {
        this.entityidStr = entityidStr;
    }
}
