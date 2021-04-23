package com.sazaxa.shipmentapi.excel.country;

import com.sazaxa.shipmentapi.excel.ExcelExtension;
import com.sazaxa.shipmentapi.excel.country.dto.CountryResponseDto;
import com.sazaxa.shipmentapi.excel.dto.ExcelSuccessResponseDto;
import com.sazaxa.shipmentapi.excel.errors.CountryNotFoundException;
import com.sazaxa.shipmentapi.excel.errors.ExcelExtensionException;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public ExcelSuccessResponseDto create(MultipartFile file) throws IOException {
        Workbook workbook = createWorkbook(file);
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
        countryRepository.saveAll(countryList);
        return new ExcelSuccessResponseDto();
    }

    public CountryResponseDto detail(String name) {
        Country country = countryRepository.findByName(name).orElseThrow(()-> new CountryNotFoundException("no name : " + name));
        return CountryResponseDto.of(country);
    }

    private Workbook createWorkbook(MultipartFile file) throws IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (extension.equals(ExcelExtension.XLSX.getExtension())) {
            return new XSSFWorkbook(file.getInputStream());
        }
        if (extension.equals(ExcelExtension.XLS.getExtension())) {
            return new HSSFWorkbook(file.getInputStream());
        }
        throw new ExcelExtensionException(extension);
    }

    public List<CountryResponseDto> list() {
        List<Country> countryList = countryRepository.findAll();
        return CountryResponseDto.ofList(countryList);
    }
}
