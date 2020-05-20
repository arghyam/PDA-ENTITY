package com.socion.entity.dao;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
public class EntityRolePk implements Serializable {

    private String userId;

    @Column(name = "entity_id")
    private long entityId;

    @NotNull
    @NotEmpty
    private String role;

    public EntityRolePk() {
    }

    public EntityRolePk(String userId, long entityId, String role) {
        this.userId = userId;
        this.entityId = entityId;
        this.role = role;
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

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        EntityRolePk that = (EntityRolePk) o;

        return userId.equals(that.userId) && entityId == that.entityId && role.equals(that.role);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}