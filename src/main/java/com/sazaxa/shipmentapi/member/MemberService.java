package com.sazaxa.shipmentapi.member;

import com.sazaxa.shipmentapi.member.dto.MemberUpdateRequestDto;
import com.sazaxa.shipmentapi.member.exception.MemberNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(()-> new MemberNotFoundException("no member id : " + id));
    }

    public void updateMember(Long id, MemberUpdateRequestDto request) {
        Member member = memberRepository.findById(id).orElseThrow(()-> new MemberNotFoundException("no member id : " + id));
        member.updateMember(request.getEmail(), request.getPassword(), request.getPhoneNumber(), request.getName());
    }

    public void deleteMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(()-> new MemberNotFoundException("no member id : " + id));
        member.updateStatus(MemberStatus.DEACTIVATE.name());
    }
}
