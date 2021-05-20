package com.sazaxa.shipmentapi.member.social;

import com.sazaxa.shipmentapi.member.social.dto.SocialRequestDto;
import com.sazaxa.shipmentapi.member.social.dto.SocialResponseDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class SocialService {


    public SocialResponseDto isRegistered(SocialRequestDto request) {
        return null;
    }
}
