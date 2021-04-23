package com.sazaxa.shipmentapi.excel.country.dto;

import com.sazaxa.shipmentapi.excel.country.Country;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public static CountryResponseDto toEntity(Country country){
        return CountryResponseDto.builder()
                .id(country.getId())
                .name(country.getName())
                .number(country.getNumber())
                .code(country.getCode())
                .build();
    }

}
