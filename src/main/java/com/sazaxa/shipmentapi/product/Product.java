package com.sazaxa.shipmentapi.product;

import com.sazaxa.shipmentapi.order.Order;

import javax.persistence.*;
import java.math.BigInteger;

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

    @ManyToOne(optional = true)
    @JoinColumn(name="order_id")
    private Order order;

    public Product() {}

    public Product(String name, double width, double depth, double height, double volumeWeight, double netWeight) {
        this.name = name;
        this.width = width;
        this.depth = depth;
        this.height = height;
        this.volumeWeight = volumeWeight;
        this.netWeight = netWeight;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getWidth() {
        return width;
    }

    public double getDepth() {
        return depth;
    }

    public double getHeight() {
        return height;
    }

    public double getVolumeWeight() {
        return volumeWeight;
    }

    public double getNetWeight() {
        return netWeight;
    }

    public double getShippingWeight() {
        return shippingWeight;
    }

    public Order getOrder() {
        return order;
    }
}
