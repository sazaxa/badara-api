package com.sazaxa.shipmentapi.recipient.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecipientNotFoundException extends RuntimeException {
    public RecipientNotFoundException(String s){
        super(s);
    }
}
