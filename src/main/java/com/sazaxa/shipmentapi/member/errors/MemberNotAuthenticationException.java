package com.sazaxa.shipmentapi.member.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class MemberNotAuthenticationException extends RuntimeException{
    public MemberNotAuthenticationException(){
        super("자격이 없습니다.");
    }
}
