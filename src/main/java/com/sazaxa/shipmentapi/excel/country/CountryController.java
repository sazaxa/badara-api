package com.sazaxa.shipmentapi.excel.country;

import com.sazaxa.shipmentapi.excel.country.dto.CountryResponseDto;
import com.sazaxa.shipmentapi.excel.dto.ExcelSuccessResponseDto;
import com.sazaxa.shipmentapi.excel.errors.ExcelNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/excel/country")
    public ExcelSuccessResponseDto create(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()){
            throw new ExcelNotFoundException("no excel file");
        }
        return countryService.create(file);
    }

    @GetMapping("/country")
    public List<CountryResponseDto> list(){
        return countryService.list();
    }

    @GetMapping("/country/{name}")
    public CountryResponseDto detail(@PathVariable String name){
        return countryService.detail(name);
    }

}
