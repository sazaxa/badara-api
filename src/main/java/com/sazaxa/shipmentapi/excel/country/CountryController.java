package com.sazaxa.shipmentapi.excel.country;

import com.sazaxa.shipmentapi.excel.dto.ExcelSuccessResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/excel")
@RestController
public class CountryController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/country")
    public ExcelSuccessResponseDto create(){
        return null;
    }
}
