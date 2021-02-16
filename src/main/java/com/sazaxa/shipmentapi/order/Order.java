package com.sazaxa.shipmentapi.order;

import javax.persistence.*;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String status;

    @Column
    private String orderNumber;

    @Column
    private String address;

    @Column
    private double orderWeight;

    @Column
    private String memo;

    public Order() { }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getAddress() {
        return address;
    }

    public double getOrderWeight() {
        return orderWeight;
    }

    public String getMemo() {
        return memo;
    }
    
}
