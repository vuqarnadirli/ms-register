package com.thetradingpit.msregister.model;


public enum RequestTypeEnum {
    CREATE_CLICK("createClick"),
    CREATE_CONVERSION("createConversion");

    private String requestType;

    RequestTypeEnum(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestType() {
        return requestType;
    }

}
