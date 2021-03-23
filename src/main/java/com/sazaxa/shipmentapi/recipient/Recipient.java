package com.sazaxa.shipmentapi.recipient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sazaxa.shipmentapi.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
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

    @OneToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @JoinColumn(name="member_id")
    private Member member;

    public Recipient(String name, String email, String country, String state, String city, String address1, String address2, String address3, String zipcode, String countryCode, String phoneNumber, Member member) {
        this.name = name;
        this.email = email;
        this.country = country;
        this.state = state;
        this.city = city;
        this.address1 = address1;
        this.address2 = address2;
        this.address3 = address3;
        this.zipcode = zipcode;
        this.countryCode = countryCode;
        this.phoneNumber = phoneNumber;
        this.member = member;
    }
}
