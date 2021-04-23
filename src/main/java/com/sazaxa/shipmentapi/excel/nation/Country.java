package com.sazaxa.shipmentapi.excel.nation;

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

    @Column
    private String tax;

    @Builder
    public Country(Long id, String name, Integer number, String code, String tax) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.code = code;
        this.tax = tax;
    }
}
