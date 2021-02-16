package com.sazaxa.shipmentapi.order;

import javax.persistence.*;

@Table(name = "zx_order")
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
    private String country;

    @Column
    private String recipientAddress;

    @Column
    private double orderWeight;

    @Column
    private String memo;

    @Column
    private double price;

    @Column
    private String recipientName;

    @Column
    private String recipientPhoneNumber;

    @Column
    private String koreanInvoice;

    public Order() {}

    public Order(Long id, String status, String orderNumber, String country, String recipientAddress, double orderWeight, String memo, double price, String recipientName, String recipientPhoneNumber, String koreanInvoice) {
        this.id = id;
        this.status = status;
        this.orderNumber = orderNumber;
        this.country = country;
        this.recipientAddress = recipientAddress;
        this.orderWeight = orderWeight;
        this.memo = memo;
        this.price = price;
        this.recipientName = recipientName;
        this.recipientPhoneNumber = recipientPhoneNumber;
        this.koreanInvoice = koreanInvoice;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getCountry() {
        return country;
    }

    public String getAddress() {
        return recipientAddress;
    }

    public double getOrderWeight() {
        return orderWeight;
    }

    public String getMemo() {
        return memo;
    }

    public double getPrice() {
        return price;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public String getRecipientPhoneNumber() {
        return recipientPhoneNumber;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public String getKoreanInvoice() {
        return koreanInvoice;
    }
}
