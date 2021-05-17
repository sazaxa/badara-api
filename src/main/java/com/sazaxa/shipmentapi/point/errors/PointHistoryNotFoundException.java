package com.sazaxa.shipmentapi.point.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PointHistoryNotFoundException extends RuntimeException {
    public PointHistoryNotFoundException(String s) {
        super(s);
    }
}
