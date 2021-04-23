package com.sazaxa.shipmentapi.excel.country;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

class CountryServiceTest {

    private CountryRepository countryRepository;
    private CountryService countryService;
    private ClassPathResource file;

    @BeforeEach
    void setUp() {
        countryRepository = mock(CountryRepository.class);
        countryService = new CountryService(countryRepository);
        file = new ClassPathResource("국가정보_210423.xlsx");
    }

    @Test
    void testGetFile(){
        assertThat(file.getFilename()).isEqualTo("국가정보_210423.xlsx");
    }

    @Test
    void testCreate() throws IOException {
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        List<Country> countryList = new ArrayList<>();
        for (Row row : sheet){
            if (row.getRowNum() == 0){
                continue;
            }
            countryList.add(Country.builder()
                    .name(row.getCell(0).getStringCellValue())
                    .number((int) row.getCell(1).getNumericCellValue())
                    .code(row.getCell(2).getStringCellValue())
                    .build());
        }
        assertThat(countryList.size()).isEqualTo(233);
    }
}
