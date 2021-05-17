package com.sazaxa.shipmentapi.member;

import com.sazaxa.shipmentapi.member.dto.MemberCheckPasswordRequestDto;
import com.sazaxa.shipmentapi.member.dto.MemberPointRequestDto;
import com.sazaxa.shipmentapi.member.dto.MemberUpdateRequestDto;
import com.sazaxa.shipmentapi.order.dto.OrderResponseDto;
import com.sazaxa.shipmentapi.point.entity.PointHistory;
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
    public List<Member> getList(){
        return memberService.getList();
    }

    @GetMapping("/{id}")
    public Member getDetail(@PathVariable Long id){
        return memberService.getDetail(id);
    }

    @GetMapping("/{id}/order")
    public List<OrderResponseDto> getDetailWithOrder(
            @PathVariable Long id){
        return memberService.getDetailWithOrder(id);
    }

    @GetMapping("/point/{id}")
    public List<PointHistory> getPointHistory(@PathVariable Long id){
        return memberService.getPointHistory(id);
    }

    @PostMapping("/point/{id}")
    public Member updatePoint(@PathVariable Long id, @RequestBody MemberPointRequestDto request){
        return memberService.updatePoint(request);
    }

    @PostMapping("/check/{id}")
    public boolean checkPassword(@PathVariable Long id, @RequestBody MemberCheckPasswordRequestDto request){
        return memberService.checkMemberPasswordWithId(id, request.getPassword());
    }

    @PutMapping("/{id}")
    public String updateMember(@PathVariable Long id, @RequestBody MemberUpdateRequestDto request){
        memberService.updateMember(id, request);
        return "success";
    }

    @PutMapping("/delete/{id}")
    public String deleteMember(@PathVariable Long id){
        memberService.deleteMember(id);
        return "success";
    }

}
