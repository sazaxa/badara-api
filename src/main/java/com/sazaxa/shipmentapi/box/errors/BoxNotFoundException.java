package com.sazaxa.shipmentapi.box.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BoxNotFoundException extends RuntimeException{
    public BoxNotFoundException(String s){
        super(s);
    }
}
