package com.socion.entity.dao;

import java.util.LinkedHashMap;

public class RegistryEntityDocsWithOsId {
    private String osid;
    private int entity_id;
    private String name;
    private String content_type;
    private String content_url ;
    private String vimeo_url;
    private boolean deleted;
    private String entity_document_id;
    private String created_at;
    private String updated_at;
    private String created_by;
    private String updated_by;
    private String entityidStr;
    public RegistryEntityDocsWithOsId(LinkedHashMap<String, Object> map) {
        this.osid=(String) map.get("osid");
        this.entity_id=(int) map.get("entity_id");
        this.entityidStr=(String) map.get("entityidStr");
        this.content_type=(String) map.get("content_type");
        this.content_url=(String) map.get("content_url");
        this.name=(String) map.get("name");
        this.vimeo_url=(String) map.get("vimeo_url");
        this.entity_document_id=(String) map.get("entity_document_id");
        this.deleted=(boolean) map.get("deleted");
        this.created_at=(String) map.get("created_at");
        this.updated_at=(String) map.get("updated_at");
        this.created_by=(String) map.get("created_by");
        this.updated_by=(String) map.get("updated_by");

    }

    public RegistryEntityDocsWithOsId() {

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public String getContent_url() {
        return content_url;
    }

    public void setContent_url(String content_url) {
        this.content_url = content_url;
    }

    public String getVimeo_url() {
        return vimeo_url;
    }

    public void setVimeo_url(String vimeo_url) {
        this.vimeo_url = vimeo_url;
    }


    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getEntity_document_id() {
        return entity_document_id;
    }

    public void setEntity_document_id(String entity_document_id) {
        this.entity_document_id = entity_document_id;
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
