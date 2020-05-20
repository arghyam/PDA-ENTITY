package com.socion.entity.dto.v2;

import com.socion.entity.dao.EntityRolePk;

import java.util.LinkedHashMap;

public class RegistryEntityRolesWithOsId {

    private EntityRolePk entityRolePk;
    private String role;
        private boolean deleted;
    private String updatedAt;
    private String userId;
    private String updatedBy;
    private String createdAt;
    private String entityidStr;
    private int entityId;
    private String createdBy;
    private String id;

    public RegistryEntityRolesWithOsId() {
    }

    public RegistryEntityRolesWithOsId(LinkedHashMap<String, Object> map) {
        this.role = (String) map.get("role");
        this.deleted =  (boolean)map.get("deleted");
        this.updatedAt = (String) map.get("updated_at");
        this.userId =  (String)map.get("user_id");
        this.updatedBy = (String) map.get("updated_by");
        this.createdAt = (String) map.get("created_at");
        this.entityidStr = (String) map.get("entityidStr");
        this.entityId = (int) map.get("entity_id");
        this.createdBy = (String) map.get("created_by");
        this.id = (String) map.get("osid");
    }

    public EntityRolePk getEntityRolePk() {
        return entityRolePk;
    }

    public void setEntityRolePk(EntityRolePk entityRolePk) {
        this.entityRolePk = entityRolePk;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getEntityidStr() {
        return entityidStr;
    }

    public void setEntityidStr(String entityidStr) {
        this.entityidStr = entityidStr;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}


