package com.sazaxa.shipmentapi.order;

import com.sazaxa.shipmentapi.product.Product;

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


    @ManyToOne(targetEntity= Product.class, fetch=FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    public Order() {}
    
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

    public Product getProduct() {
        return product;
    }
}
