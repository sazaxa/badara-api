package com.sazaxa.shipmentapi.excel.country;

import com.sazaxa.shipmentapi.excel.dto.ExcelSuccessResponseDto;
import com.sazaxa.shipmentapi.excel.errors.ExcelNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/excel")
@RestController
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/country")
    public ExcelSuccessResponseDto create(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()){
            throw new ExcelNotFoundException("no excel file");
        }

        countryService.create(file);

        return null;
    }
}
