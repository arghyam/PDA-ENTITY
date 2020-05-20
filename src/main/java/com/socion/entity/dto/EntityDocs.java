package com.socion.entity.dto;

public class EntityDocs {


    private String entity_document_id;
    private String content_type;
    private String content_url;
    private String vimeo_url;
    private String name;
    private boolean deleted;
    private String created_at;
    private String updated_at;
    private String created_by;
    private String updated_by;
    private int entity_id;
    private String entityidStr;

    public String getEntityidStr() {
        return entityidStr;
    }

    public void setEntityidStr(String entityidStr) {
        this.entityidStr = entityidStr;
    }

    public String getEntity_document_id() {
        return entity_document_id;
    }

    public void setEntity_document_id(String entity_document_id) {
        this.entity_document_id = entity_document_id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(int entity_id) {
        this.entity_id = entity_id;
    }
}
