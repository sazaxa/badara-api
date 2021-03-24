package com.sazaxa.shipmentapi.member.auth;

import com.sazaxa.shipmentapi.member.Member;
import com.sazaxa.shipmentapi.member.MemberService;
import com.sazaxa.shipmentapi.member.MemberStatus;
import com.sazaxa.shipmentapi.member.auth.dto.JwtAuthenticationResponse;
import com.sazaxa.shipmentapi.member.dto.MemberSigninRequestDto;
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

import javax.validation.Valid;
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

    @GetMapping("/hue")
    public String test(){
        return "HUE..";
    }

    @PostMapping("/signin")
    public ResponseEntity loginUser(@Valid @RequestBody MemberSigninRequestDto resource){

        //아이디 확인
        if(!memberService.isExistsByEmail(resource.getEmail())){
            return new ResponseEntity("등록되지 않은 계정입니다.", HttpStatus.UNAUTHORIZED);
        }

        //비밀번호 확인
        if (!memberService.checkMemberPasswordWithEmail(resource.getEmail(), resource.getPassword())){
            return new ResponseEntity("비밀번호가 일치하지 않습니다.", HttpStatus.UNAUTHORIZED);
        }

        //Role확인
        if (memberService.isAdminRole(resource.getEmail())){
            return new ResponseEntity("관리자는 로그인 할 수 없습니다.", HttpStatus.FORBIDDEN);
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(resource.getEmail(), resource.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);

        return new ResponseEntity(new JwtAuthenticationResponse(jwt), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.CREATED)
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



    @PostMapping("/signin/admin")
    public ResponseEntity loginAdmin(@RequestBody Member resource){

        if (!memberService.isAdminRole(resource.getEmail())){
            return new ResponseEntity("일반 유저는 접근할 수 없습니다.", HttpStatus.FORBIDDEN);
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(resource.getEmail(), resource.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);

        return new ResponseEntity(new JwtAuthenticationResponse(jwt), HttpStatus.OK);
    }

    @GetMapping("/current")
    @PreAuthorize("hasRole('USER')")
    public Member getCurrentMember(@CurrentUser UserPrincipalCustom userPrincipal){
        return memberService.getMemberById(userPrincipal.getId());
    }

    @GetMapping("/current/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public boolean getCurrentAdmin(@CurrentUser UserPrincipalCustom userPrincipal){
        boolean flag = memberService.isAdminRole(userPrincipal.getEmail());
        if (flag){
            return true;
        }
        return false;
    }

}
