package com.sazaxa.shipmentapi.member.auth;

import com.sazaxa.shipmentapi.member.Member;
import com.sazaxa.shipmentapi.member.MemberService;
import com.sazaxa.shipmentapi.member.MemberStatus;
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

        /*
        1. 아이디, 비밀번호 확인
        2. Role확인 : 당신이 관리자여서 로그인이 안된다.
         */

        if(!memberService.isExistsByEmail(resource.getEmail())){
            return new ResponseEntity("등록되지 않은 계정입니다.", HttpStatus.UNAUTHORIZED);
        }
        
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
                .status(MemberStatus.ACTIVATE.name())
                .build();

        memberService.registerMember(member);

        return new ResponseEntity(member, HttpStatus.CREATED);
    }

    @GetMapping("/current")
    @PreAuthorize("hasRole('USER')")
    public Member getCurrentMember(@CurrentUser UserPrincipalCustom userPrincipal){
        return memberService.getMemberById(userPrincipal.getId());
    }

    @PostMapping("/signin/admin")
    public ResponseEntity loginAdmin(@RequestBody Member resource){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(resource.getEmail(), resource.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);

        /*
        * 이 계정이 role_admin이면 통과 아니며 error
        * */

        return new ResponseEntity(new JwtAuthenticationResponse(jwt), HttpStatus.OK);
    }

    @GetMapping("/current/admin")
    @PreAuthorize("hasRole('USER')")
    public boolean getCurrentAdmin(@CurrentUser UserPrincipalCustom userPrincipal){
        /*
        현재 로인한 계정이 role_admin이면 true
        아니면 error
         */
        return memberService.getMemberById(userPrincipal.getId());
    }


    /*
    1. admin으로 로그인 했을 때 role 확인해서 admin이 아니면 error 맞으면 OK.
    2. /cuurrent
    * */

}
