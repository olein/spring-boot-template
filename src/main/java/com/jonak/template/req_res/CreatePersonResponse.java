package com.jonak.template.req_res;

public class CreatePersonResponse {

    private boolean result;

    public CreatePersonResponse(boolean result) {
        this.result = result;
    }

    public CreatePersonResponse() {
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
