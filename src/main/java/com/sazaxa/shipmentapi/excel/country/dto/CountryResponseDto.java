package com.sazaxa.shipmentapi.excel.country.dto;

import com.sazaxa.shipmentapi.excel.country.Country;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class CountryResponseDto {
    private Long id;
    private String name;
    private Integer number;
    private String code;

    @Builder
    public CountryResponseDto(Long id, String name, Integer number, String code) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.code = code;
    }

    public static CountryResponseDto of(Country country){
        return CountryResponseDto.builder()
                .id(country.getId())
                .name(country.getName())
                .number(country.getNumber())
                .code(country.getCode())
                .build();
    }

    public static List<CountryResponseDto> ofList(List<Country> countryList){
        List<CountryResponseDto> countryResponseDtoList = new ArrayList<>();
        for (Country country : countryList){
            countryResponseDtoList.add(CountryResponseDto.of(country));
        }
        return countryResponseDtoList;
    }

}
