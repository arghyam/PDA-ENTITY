package com.pda.entity.dto;

import java.util.List;

public class UserRequestTDO {

    List<String> userIds;

    public UserRequestTDO() {
    }

    public UserRequestTDO(List<String> userIds) {
        this.userIds = userIds;
    }

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }
}
