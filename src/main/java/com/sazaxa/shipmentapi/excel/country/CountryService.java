package com.sazaxa.shipmentapi.excel.country;

import com.sazaxa.shipmentapi.excel.dto.ExcelSuccessResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Transactional
@Service
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public ExcelSuccessResponseDto create(MultipartFile file) {
        return null;
    }
}
