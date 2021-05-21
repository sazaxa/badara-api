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
    public List<Member> list(){
        return memberService.list();
    }

    @GetMapping("/{id}")
    public Member detail(@PathVariable Long id){
        return memberService.detail(id);
    }

    @GetMapping("/{id}/order")
    public List<OrderResponseDto> detailWithOrder(
            @PathVariable Long id){
        return memberService.detailWithOrder(id);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody MemberUpdateRequestDto request){
        memberService.update(id, request);
        return "success";
    }

    @PutMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        memberService.delete(id);
        return "success";
    }

    @GetMapping("/point/{id}")
    public List<PointHistory> getPointHistory(@PathVariable Long id){
        return memberService.getPointHistory(id);
    }

    @PostMapping("/point/{id}")
    public Member updatePoint(@PathVariable Long id, @RequestBody MemberPointRequestDto request){
        return memberService.updatePoint(id,request);
    }

    @PostMapping("/check/{id}")
    public boolean checkPassword(@PathVariable Long id, @RequestBody MemberCheckPasswordRequestDto request){
        return memberService.checkMemberPasswordWithId(id, request.getPassword());
    }
}
