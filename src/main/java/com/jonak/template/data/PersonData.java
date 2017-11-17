package com.jonak.template.data;

import java.io.Serializable;

public class PersonData implements Serializable{

    private String name;
    private String mobile;
    private String street;
    private int zip;
    private boolean result;
    private int personId;

    public PersonData() {
    }

    public PersonData(String name, String mobile, String street, int zip) {
        this.name = name;
        this.mobile = mobile;
        this.street = street;
        this.zip = zip;
    }

    public PersonData(boolean result) {
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public PersonData withPersonId(int personId) {
        this.personId = personId;
        return this;
    }

    public int getPersonId() {
        return personId;
    }
}
