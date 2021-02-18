package com.sazaxa.shipmentapi.member.dto;

import lombok.Getter;

@Getter
public class MemberUpdateRequestDto {

    private String email;
    private String password;
    private String phoneNumber;
    private String name;

}
