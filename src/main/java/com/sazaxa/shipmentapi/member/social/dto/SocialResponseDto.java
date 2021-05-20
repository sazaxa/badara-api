package com.sazaxa.shipmentapi.member.social.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SocialResponseDto {
    private Boolean isRegistered;
    private String socialId;
    private String email;
    private String password;

    @Builder
    public SocialResponseDto(Boolean isRegistered, String socialId, String email, String password) {
        this.isRegistered = isRegistered;
        this.socialId = socialId;
        this.email = email;
        this.password = password;
    }
}
