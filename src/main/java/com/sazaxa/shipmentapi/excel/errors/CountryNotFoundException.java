package com.sazaxa.shipmentapi.excel.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
public class CountryNotFoundException extends RuntimeException{
    public CountryNotFoundException(String message){
        super(message);
    }
}
