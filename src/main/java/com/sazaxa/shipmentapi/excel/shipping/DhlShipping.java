package com.sazaxa.shipmentapi.excel.shipping;

import com.sazaxa.shipmentapi.util.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "zx_dhl_shipping")
@Entity
public class DhlShipping extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String country;

    @Column
    private double weight;

    @Column
    private double price;

    @Builder
    public DhlShipping(String country, double weight, double price){
        this.country = country;
        this.weight = weight;
        this.price = price;
    }

}
