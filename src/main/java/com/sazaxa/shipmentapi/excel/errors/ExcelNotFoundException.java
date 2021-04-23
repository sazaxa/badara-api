package com.sazaxa.shipmentapi.excel.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExcelNotFoundException extends RuntimeException {
    public ExcelNotFoundException(String s) {
    }
}
