package com.sazaxa.shipmentapi.member;

import com.sazaxa.shipmentapi.member.dto.MemberCheckPasswordRequestDto;
import com.sazaxa.shipmentapi.member.dto.MemberOrderResponseListDto;
import com.sazaxa.shipmentapi.member.dto.MemberUpdateRequestDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public List<Member> getAllMembers(){
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable Long id){
        return memberService.getMemberById(id);
    }

    @GetMapping("/{id}/order")
    public MemberOrderResponseListDto getMemberByIdWithOrder(
            @PathVariable Long id){
        return memberService.getMemberByIdWithOrder(id);
    }

    @PutMapping("/{id}")
    public String updateMember(@PathVariable Long id, @RequestBody MemberUpdateRequestDto request){
        memberService.updateMember(id, request);
        return "success";
    }

    @PostMapping("/check/{id}")
    public boolean checkPassword(@PathVariable Long id, @RequestBody MemberCheckPasswordRequestDto request){
        return memberService.checkMember(id, request);
    }

    @PutMapping("/delete/{id}")
    public String deleteMember(@PathVariable Long id){
        memberService.deleteMember(id);
        return "success";
    }

}
