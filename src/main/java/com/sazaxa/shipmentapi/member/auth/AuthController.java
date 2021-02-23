package com.sazaxa.shipmentapi.member.auth;

import com.sazaxa.shipmentapi.member.Member;
import com.sazaxa.shipmentapi.member.MemberService;
import com.sazaxa.shipmentapi.member.role.Role;
import com.sazaxa.shipmentapi.member.role.RoleName;
import com.sazaxa.shipmentapi.member.role.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    public AuthController(MemberService memberService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.memberService = memberService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
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
