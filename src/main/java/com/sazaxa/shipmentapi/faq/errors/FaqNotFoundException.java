package com.sazaxa.shipmentapi.faq.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FaqNotFoundException extends RuntimeException {

    public FaqNotFoundException(String s) {
        super(s);
    }

}
