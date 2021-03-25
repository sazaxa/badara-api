package com.sazaxa.shipmentapi.recipient.dto;

import com.sazaxa.shipmentapi.recipient.Recipient;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RecipientResponseDto {
    private Long id;
    private String name;
    private String email;
    private String country;
    private String state;
    private String city;
    private String address1;
    private String address2;
    private String address3;
    private String zipcode;
    private String countryCode;
    private String phoneNumber;

    public static RecipientResponseDto of(Recipient recipient){
        return RecipientResponseDto.builder()
                .id(recipient.getId())
                .name(recipient.getName())
                .email(recipient.getEmail())
                .country(recipient.getCountry())
                .state(recipient.getState())
                .city(recipient.getCity())
                .address1(recipient.getAddress1())
                .address2(recipient.getAddress2())
                .address3(recipient.getAddress3())
                .zipcode(recipient.getZipcode())
                .countryCode(recipient.getCountryCode())
                .phoneNumber(recipient.getPhoneNumber())
                .build();
    }

}
