package com.sazaxa.shipmentapi.member.social.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SocialResponseDto {
    private Boolean isRegistered;

    @Builder
    public SocialResponseDto(Boolean isRegistered) {
        this.isRegistered = isRegistered;
    }
}
