package com.sazaxa.shipmentapi.excel.errors;

public class ExcelBadRequestException extends RuntimeException{
    public ExcelBadRequestException(String message){
        super(message);
    }
}
