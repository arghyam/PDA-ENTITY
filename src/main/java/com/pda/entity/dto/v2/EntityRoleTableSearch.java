package com.pda.entity.dto.v2;

public class EntityRoleTableSearch {
     private boolean entityIdReq;
     private boolean userIdReq;
     private boolean roleReq;

    public boolean isEntityIdReq() {
        return entityIdReq;
    }

    public void setEntityIdReq(boolean entityIdReq) {
        this.entityIdReq = entityIdReq;
    }

    public boolean isUserIdReq() {
        return userIdReq;
    }

    public void setUserIdReq(boolean userIdReq) {
        this.userIdReq = userIdReq;
    }

    public boolean isRoleReq() {
        return roleReq;
    }

    public void setRoleReq(boolean roleReq) {
        this.roleReq = roleReq;
    }
}
