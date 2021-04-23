package com.sazaxa.shipmentapi.excel.country;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }
}
