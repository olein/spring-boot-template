package com.jonak.template.req_res;

public class GetPersonResponse {

    private String name;
    private String mobile;
    private String street;
    private Boolean result;

    public GetPersonResponse() {
    }

    public GetPersonResponse(String name, String mobile, String street) {
        this.name = name;
        this.mobile = mobile;
        this.street = street;
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

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }
}
