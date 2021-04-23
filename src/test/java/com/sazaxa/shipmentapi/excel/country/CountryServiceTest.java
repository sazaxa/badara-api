package com.sazaxa.shipmentapi.excel.country;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class CountryServiceTest {

    private CountryRepository countryRepository;
    private CountryService countryService;
    private ClassPathResource file;

    @BeforeEach
    void setUp() {
        countryRepository = mock(CountryRepository.class);
        countryService = new CountryService(countryRepository);
        file = new ClassPathResource("국가정보_21423.xlsx");
    }

    @Test
    void testGetFile(){
        assertThat(file.getFilename()).isEqualTo("국가정보_21423.xlsx");
    }
    
    @Test
    void testCreate() {
    }
}
