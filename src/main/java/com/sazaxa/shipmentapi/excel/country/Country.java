package com.sazaxa.shipmentapi.excel.country;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Integer number;

    @Column
    private String code;

    @Builder
    public Country(Long id, String name, Integer number, String code) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.code = code;
    }
}
