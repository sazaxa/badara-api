package com.sazaxa.shipmentapi.member.auth;

import com.sazaxa.shipmentapi.member.Member;
import com.sazaxa.shipmentapi.member.MemberService;
import com.sazaxa.shipmentapi.member.auth.dto.JwtAuthenticationResponse;
import com.sazaxa.shipmentapi.member.role.Role;
import com.sazaxa.shipmentapi.member.role.RoleName;
import com.sazaxa.shipmentapi.member.role.RoleService;
import com.sazaxa.shipmentapi.security.CurrentUser;
import com.sazaxa.shipmentapi.security.UserPrincipalCustom;
import com.sazaxa.shipmentapi.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RequestMapping("/api/v1/auth")
@RestController
public class AuthController {

    @Autowired
    private JwtTokenProvider tokenProvider;

    private final MemberService memberService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthController(MemberService memberService, RoleService roleService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.memberService = memberService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signin")
    public ResponseEntity loginUser(@RequestBody Member resource){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(resource.getEmail(), resource.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);

        return new ResponseEntity(new JwtAuthenticationResponse(jwt), HttpStatus.OK);

    }

    @PostMapping("/signup")
    public ResponseEntity registerMember(@RequestBody Member resource ){

        if(memberService.isExistsByEmail(resource.getEmail())){
            return new ResponseEntity("this email is already taken", HttpStatus.UNAUTHORIZED);
        }

        Role userRole = roleService.findByRoleName(RoleName.ROLE_USER);

        Member member = Member.builder()
                .email(resource.getEmail())
                .password(passwordEncoder.encode(resource.getPassword()))
                .phoneNumber(resource.getPhoneNumber())
                .name(resource.getName())
                .roles(Collections.singleton(userRole))
                .build();

        memberService.registerMember(member);

        return new ResponseEntity(member, HttpStatus.CREATED);
    }

    @GetMapping("/current")
    @PreAuthorize("hasRole('USER')")
    public Member getCurrentMember(@CurrentUser UserPrincipalCustom userPrincipal){
        return memberService.getMemberById(userPrincipal.getId());
    }

}
