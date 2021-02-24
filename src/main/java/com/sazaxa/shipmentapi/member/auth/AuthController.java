package com.sazaxa.shipmentapi.member.auth;

import com.sazaxa.shipmentapi.member.Member;
import com.sazaxa.shipmentapi.member.MemberService;
import com.sazaxa.shipmentapi.member.auth.dto.JwtAuthenticationResponse;
import com.sazaxa.shipmentapi.member.role.Role;
import com.sazaxa.shipmentapi.member.role.RoleName;
import com.sazaxa.shipmentapi.member.role.RoleService;
import com.sazaxa.shipmentapi.security.jwt.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RequestMapping("/api/auth")
@RestController
public class AuthController {

    private final MemberService memberService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    public AuthController(MemberService memberService, RoleService roleService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider) {
        this.memberService = memberService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
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

        if(memberService.isExistsByUserId(resource.getEmail())){
            return new ResponseEntity("this email is already taken", HttpStatus.BAD_REQUEST);
        }

        Role userRole = roleService.findByRoleName(RoleName.ROLE_USER);

        Member member = Member.builder()
                .email(resource.getEmail())
                .password(passwordEncoder.encode(resource.getPassword()))
                .name(resource.getName())
                .roles(Collections.singleton(userRole))
                .build();

        memberService.registerMember(member);

        return new ResponseEntity(HttpStatus.CREATED);
    }

}
