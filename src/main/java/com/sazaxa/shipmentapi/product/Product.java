package com.sazaxa.shipmentapi.product;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "zx_product")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private double width;

    @Column
    private double depth;

    @Column
    private double height;

    @Column
    private double volumeWeight;

    @Column
    private double netWeight;

    @Column
    private double shippingWeight;

    @Builder
    public Product(String name, double width, double depth, double height, double volumeWeight, double netWeight) {
        this.name = name;
        this.width = width;
        this.depth = depth;
        this.height = height;
        this.volumeWeight = volumeWeight;
        this.netWeight = netWeight;
    }

}
