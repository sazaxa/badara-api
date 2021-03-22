package com.sazaxa.shipmentapi.haporder.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class HapOrderNotFoundException extends RuntimeException {
    public HapOrderNotFoundException(String s) {
        super(s);
    }
}

