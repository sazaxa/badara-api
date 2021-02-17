package com.sazaxa.shipmentapi.order.dto;


public class OrderSaveRequestDto {

    private String recipientName;
    private String recipientPhoneNumber;
    private String recipientAddress;
    private String productName;
    private String koreanInvoice;
    private String country;
    private double width;
    private double depth;
    private double height;
    private double volumeWeight;
    private double netWeight;
    private double expectedPrice;

    public OrderSaveRequestDto() {
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

    public String getProductName() {
        return productName;
    }

    public String getKoreanInvoice() {
        return koreanInvoice;
    }

    public String getCountry() {
        return country;
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

    public double getExpectedPrice() {
        return expectedPrice;
    }
}
