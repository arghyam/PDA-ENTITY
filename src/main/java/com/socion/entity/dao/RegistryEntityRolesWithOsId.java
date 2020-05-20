package com.socion.entity.dao;

import java.util.Map;

public class RegistryEntityRolesWithOsId {
    private String osid;
    private int entity_id;
    private String role;
    private String user_id;
    private boolean deleted;
    private String created_at;
    private String updated_at;
    private String created_by;
    private String updated_by;
    private String entityidStr;
    public RegistryEntityRolesWithOsId(Map<String, Object> map) {
        this.osid=(String) map.get("osid");
        this.entity_id=(int) map.get("entity_id");
        this.entityidStr=(String) map.get("entityidStr");
        this.role=(String) map.get("role");
        this.user_id=(String) map.get("user_id");
        this.deleted=(boolean) map.get("deleted");
        this.created_at=(String) map.get("created_at");
        this.updated_at=(String) map.get("updated_at");
        this.created_by=(String) map.get("created_by");
        this.updated_by=(String) map.get("updated_by");

    }

    public RegistryEntityRolesWithOsId() {
    }



    public String getOsid() {
        return osid;
    }

    public void setOsid(String osid) {
        this.osid = osid;
    }

    public int getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(int entity_id) {
        this.entity_id = entity_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserId() {
        return user_id;
    }

    public void setUserId(String user_id) {
        this.user_id = user_id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }


    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public String getEntityidStr() {
        return entityidStr;
    }

    public void setEntityidStr(String entityidStr) {
        this.entityidStr = entityidStr;
    }
}
