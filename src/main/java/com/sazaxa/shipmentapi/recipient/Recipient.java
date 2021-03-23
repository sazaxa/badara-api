package com.sazaxa.shipmentapi.recipient;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Table(name = "zx_recipient")
@Entity
public class Recipient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String country;

    @Column
    private String state;

    @Column
    private String city;

    @Column(length = 90)
    private String address1;

    @Column(length = 90)
    private String address2;

    @Column(length = 90)
    private String address3;

    @Column
    private String zipcode;

    @Column
    private String countryCode;

    @Column
    private String phoneNumber;



}
