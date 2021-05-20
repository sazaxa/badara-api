package com.sazaxa.shipmentapi.member.social.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SocialRequestDto {
    private String socialId;
    private String email;
    private String name;
    private String phoneNumber;

    @Builder
    public SocialRequestDto(String socialId, String email, String name, String phoneNumber) {
        this.socialId = socialId;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
