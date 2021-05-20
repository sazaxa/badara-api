package com.sazaxa.shipmentapi.member.social;

import com.sazaxa.shipmentapi.member.MemberService;
import com.sazaxa.shipmentapi.member.role.RoleService;
import com.sazaxa.shipmentapi.member.social.dto.SocialRequestDto;
import com.sazaxa.shipmentapi.member.social.dto.SocialResponseDto;
import com.sazaxa.shipmentapi.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/api/v1/social")
public class SocialController {

    @Autowired
    private JwtTokenProvider tokenProvider;
    private final MemberService memberService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final SocialService socialService;

    public SocialController(MemberService memberService, RoleService roleService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, SocialService socialService) {
        this.memberService = memberService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.socialService = socialService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/check")
    public SocialResponseDto oauthCheck(@RequestBody SocialRequestDto request){
        return socialService.isRegistered(request);
    }
}
