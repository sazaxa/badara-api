package com.sazaxa.shipmentapi.util;

import com.sazaxa.shipmentapi.excel.errors.CountryNotFoundException;
import com.sazaxa.shipmentapi.excel.errors.ExcelBadRequestException;
import com.sazaxa.shipmentapi.excel.errors.ExcelNotFoundException;
import com.sazaxa.shipmentapi.member.errors.MemberNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerErrorAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(MemberNotFoundException.class)
    public ErrorResponse handleMemberNotFound(MemberNotFoundException ex){
        return ErrorResponse.of(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CountryNotFoundException.class)
    public ErrorResponse handleCountryNotFound(CountryNotFoundException ex){
        return ErrorResponse.of(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ExcelNotFoundException.class)
    public ErrorResponse handleExcelNotFound(ExcelNotFoundException ex){
        return ErrorResponse.of(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ExcelBadRequestException.class)
    public ErrorResponse handleExcelBadRequest(ExcelBadRequestException ex){
        return ErrorResponse.of(ex.getMessage());
    }

}
