package com.sazaxa.shipmentapi.excel.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExcelBadRequestException extends RuntimeException{
    public ExcelBadRequestException(String message){
        super(message);
    }
}
