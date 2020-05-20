package com.socion.entity.dao;


import java.util.LinkedHashMap;

public class RegistryEntityWithOsId {
    private String osid;
    private int entity_id;
    private String name;
    private String business_registration_number;
    private String tax_registration_number;
    private String address_line1;
    private String address_line2;
    private String city;
    private String state ;
    private String country;
    private String pin_code;
    private boolean deleted;
    private boolean is_registered;
    private String created_at;
    private String updated_at;
    private String created_by;
    private String updated_by;
    private String registered_date;
    private String entityidStr;
    public RegistryEntityWithOsId(LinkedHashMap<String, Object> map) {
        this.osid=(String) map.get("osid");
        this.entity_id=(int) map.get("entity_id");
        this.entityidStr=(String) map.get("entityidStr");
        this.name=(String) map.get("name");
        this.business_registration_number=(String) map.get("business_registration_number");
        this.tax_registration_number=(String) map.get("tax_registration_number");
        this.address_line1=(String) map.get("address_line1");
        this.address_line2=(String) map.get("address_line2");
        this.city=(String) map.get("city");
        this.state =(String) map.get("state");
        this.country=(String) map.get("country");
        this.pin_code=(String) map.get("pin_code");
        this.deleted=(boolean) map.get("deleted");
        this.is_registered=(boolean) map.get("is_registered");
        this.created_at=(String) map.get("created_at");
        this.updated_at=(String) map.get("updated_at");
        this.created_by=(String) map.get("created_by");
        this.updated_by=(String) map.get("updated_by");
        this.registered_date=(String) map.get("registered_date");
    }

    public RegistryEntityWithOsId() {

    }



    public String getEntityidStr() {
        return entityidStr;
    }

    public void setEntityidStr(String entityidStr) {
        this.entityidStr = entityidStr;
    }

    public String getOsid() {
        return osid;
    }

    public void setOsid(String osid) {
        this.osid = osid;
    }

    public String getRegistered_date() {
        return registered_date;
    }

    public void setRegistered_date(String registered_date) {
        this.registered_date = registered_date;
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

    public String getBusiness_registration_number() {
        return business_registration_number;
    }

    public void setBusiness_registration_number(String business_registration_number) {
        this.business_registration_number = business_registration_number;
    }

    public String getTax_registration_number() {
        return tax_registration_number;
    }

    public void setTax_registration_number(String tax_registration_number) {
        this.tax_registration_number = tax_registration_number;
    }

    public String getAddress_line1() {
        return address_line1;
    }

    public void setAddress_line1(String address_line1) {
        this.address_line1 = address_line1;
    }

    public String getAddress_line2() {
        return address_line2;
    }

    public void setAddress_line2(String address_line2) {
        this.address_line2 = address_line2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPin_code() {
        return pin_code;
    }

    public void setPin_code(String pin_code) {
        this.pin_code = pin_code;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isIs_registered() {
        return is_registered;
    }

    public void setIs_registered(boolean is_registered) {
        this.is_registered = is_registered;
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
}








