package com.jonak.template.req_res;

public class GetPersonRequest {

    private int personId;

    public GetPersonRequest() {
    }

    public GetPersonRequest(int personId) {
        this.personId = personId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}
