package com.sazaxa.shipmentapi.recipient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sazaxa.shipmentapi.member.Member;
import com.sazaxa.shipmentapi.util.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "badara_recipient")
@Entity
public class Recipient extends BaseEntity {

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

    @Column
    private String address1;

    @Column
    private String address2;

    @Column
    private String address3;

    @Column
    private String zipcode;

    @Column
    private String countryCode;

    @Column
    private String phoneNumber;

    @Column
    private String memo;

    @OneToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    @JoinColumn(name="member_id")
    private Member member;

    @Builder
    public Recipient(String name, String email, String country, String state, String city, String address1, String address2, String address3, String zipcode, String countryCode, String phoneNumber, String memo, Member member) {
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
        this.memo = memo;
        this.member = member;
    }

    public void updateRecipient(String name, String email, String country, String state, String city, String address1, String address2, String address3, String zipcode, String countryCode, String phoneNumber, String memo) {
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
        this.memo = memo;
    }
}
