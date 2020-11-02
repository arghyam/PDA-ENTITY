package com.pda.entity.dao;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProgramAdminPk implements Serializable {


    private String userId;

    private long entityId;

    public ProgramAdminPk() {
    }

    public ProgramAdminPk(String userId, long entityId) {
        this.userId = userId;
        this.entityId = entityId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getEntityId() {
        return entityId;
    }

    public void setEntityId(long entityId) {
        this.entityId = entityId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProgramAdminPk that = (ProgramAdminPk) o;

        if (!userId.equals(that.userId)) return false;
        return entityId == that.entityId;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }


}