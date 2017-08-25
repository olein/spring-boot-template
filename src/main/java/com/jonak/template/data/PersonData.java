package com.jonak.template.data;

public class PersonData {

    private String name;
    private String mobile;
    private String street;
    private int zip;
    private boolean result;

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
}
