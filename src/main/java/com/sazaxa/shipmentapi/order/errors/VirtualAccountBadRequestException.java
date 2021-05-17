package com.sazaxa.shipmentapi.order.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class VirtualAccountBadRequestException extends RuntimeException {
    public VirtualAccountBadRequestException(){
        super("가상계좌 입금 완료 요청이 잘못되었습니다.");
    }
}
