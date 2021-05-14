package com.sazaxa.shipmentapi.order.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PointBadRequestException extends RuntimeException {
    public PointBadRequestException(){
        super("보유하신 포인트가 부족합니다.");
    }
}
