package com.sazaxa.shipmentapi.member.auth;

import com.sazaxa.shipmentapi.member.Member;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth")
@RestController
public class AuthController {

    @PostMapping("/signup")
    public ResponseEntity registerMember(@RequestBody Member resource ){

//        if(memberService.isExistsByUserId(resource.getUserId())){
//            return new ResponseEntity("userId is already taken", HttpStatus.BAD_REQUEST);
//        }

//        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
//                .orElseThrow(() -> new ResourceNotFoundException("User Role not set."));

//        Member member = Member.builder()
//                .userId(resource.getUserId())
//                .password(passwordEncoder.encode(resource.getPassword()))
//                .name(resource.getName())
//                .roles(Collections.singleton(userRole))
//                .build();
//
//        memberService.registerMember(member);

        return new ResponseEntity(HttpStatus.CREATED);
    }

}
