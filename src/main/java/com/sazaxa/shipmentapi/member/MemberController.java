package com.sazaxa.shipmentapi.member;

import com.sazaxa.shipmentapi.member.dto.MemberUpdateRequestDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public List<Member> getMembers(){
        return memberService.getMembers();
    }

    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable Long id){
        return memberService.getMemberById(id);
    }

    @PutMapping("/{id}")
    public String updateMember(@PathVariable Long id, @RequestBody MemberUpdateRequestDto request){
        memberService.updateMember(id, request);
        return "success";
    }

    @PutMapping("/{id}")
    public String checkPassword(@PathVariable Long id, @RequestBody MemberUpdateRequestDto request){
        memberService.updateMember(id, request);
        return "success";
    }

    @PutMapping("/delete/{id}")
    public String deleteMember(@PathVariable Long id){
        memberService.deleteMember(id);
        return "success";
    }

}
