package com.sazaxa.shipmentapi.util;

public class ErrorResponse {

    private String message;

    public ErrorResponse(String message){
        this.message = message;
    }

    public static ErrorResponse of(String message){
        return new ErrorResponse(message);
    }

    public String getMessage(){
        return message;
    }

}
