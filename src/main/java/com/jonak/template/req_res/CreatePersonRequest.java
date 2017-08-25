package com.jonak.template.req_res;

public class CreatePersonRequest {

    private String name;
    private String mobile;
    private String street;
    private int zip;

    public CreatePersonRequest() {
    }

    public CreatePersonRequest(String name, String mobile, String street, int zip) {
        this.name = name;
        this.mobile = mobile;
        this.street = street;
        this.zip = zip;
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
}
