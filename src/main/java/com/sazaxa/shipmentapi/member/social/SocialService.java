package com.sazaxa.shipmentapi.member.social;

import com.sazaxa.shipmentapi.member.Member;
import com.sazaxa.shipmentapi.member.social.dto.SocialRequestDto;
import com.sazaxa.shipmentapi.member.social.dto.SocialResponseDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class SocialService {

    private final MemberSocialRepository memberSocialRepository;

    public SocialService(MemberSocialRepository memberSocialRepository) {
        this.memberSocialRepository = memberSocialRepository;
    }

    public SocialResponseDto isRegistered(SocialRequestDto request) {
        for (MemberSocial memberSocial : memberSocialRepository.findAll()){
              if (memberSocial.getSocialId().equals(request.getSocialId())){
                  return SocialResponseDto.builder()
                          .socialId(request.getSocialId())
                          .email(request.getEmail())
                          .password(memberSocial.getPassword())
                          .isRegistered(Boolean.TRUE)
                          .build();
              }
        }

        return SocialResponseDto.builder()
                .socialId(request.getSocialId())
                .email(request.getEmail())
                .isRegistered(Boolean.FALSE)
                .build();
    }


    public SocialResponseDto register(SocialRequestDto request, String password, Member member) {
        memberSocialRepository.save(MemberSocial.builder()
                .socialId(request.getSocialId())
                .password(password)
                .type("KAKAO")
                .member(member)
                .build());

        return SocialResponseDto.builder()
                .email(request.getEmail())
                .password(password)
                .build();
    }
}
