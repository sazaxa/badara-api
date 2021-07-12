package com.sazaxa.shipmentapi.excel.errors;

public class CountryNotFoundException extends RuntimeException{
    public CountryNotFoundException(String message){
        super(message);
    }
}
