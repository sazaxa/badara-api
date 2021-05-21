package com.sazaxa.shipmentapi.member.social;

import com.sazaxa.shipmentapi.member.Member;
import com.sazaxa.shipmentapi.member.MemberService;
import com.sazaxa.shipmentapi.member.MemberStatus;
import com.sazaxa.shipmentapi.member.role.Role;
import com.sazaxa.shipmentapi.member.role.RoleName;
import com.sazaxa.shipmentapi.member.role.RoleService;
import com.sazaxa.shipmentapi.member.social.dto.SocialRequestDto;
import com.sazaxa.shipmentapi.member.social.dto.SocialResponseDto;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RequestMapping("/api/v1/social")
@RestController

public class SocialController {

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

    @PostMapping
    public ResponseEntity socialRegister(@RequestBody SocialRequestDto request){

        String password = RandomStringUtils.random(20, 33, 125, true, true);

        if(memberService.isExistsByEmail(request.getEmail())){
            return new ResponseEntity("this email is already taken", HttpStatus.UNAUTHORIZED);
        }

        Role userRole = roleService.findByRoleName(RoleName.ROLE_USER);

        Member member = Member.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(password))
                .phoneNumber(request.getPhoneNumber())
                .name(request.getName())
                .roles(Collections.singleton(userRole))
                .status(MemberStatus.ACTIVATE.name())
                .build();

        memberService.registerMember(member);
        SocialResponseDto socialResponseDto = socialService.register(request, password, member);

        return new ResponseEntity(socialResponseDto, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/check")
    public SocialResponseDto isRegistered(@RequestBody SocialRequestDto request){
        return socialService.isRegistered(request);
    }

}
