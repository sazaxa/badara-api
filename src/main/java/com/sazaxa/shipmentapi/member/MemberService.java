package com.sazaxa.shipmentapi.member;

import com.sazaxa.shipmentapi.member.dto.MemberOrderResponseDto;
import com.sazaxa.shipmentapi.member.dto.MemberOrderResponseListDto;
import com.sazaxa.shipmentapi.member.dto.MemberUpdateRequestDto;
import com.sazaxa.shipmentapi.member.exception.MemberNotFoundException;
import com.sazaxa.shipmentapi.member.role.Role;
import com.sazaxa.shipmentapi.member.role.RoleName;
import com.sazaxa.shipmentapi.order.OrderService;
import com.sazaxa.shipmentapi.order.dto.OrderResponseDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final OrderService orderService;
    private final PasswordEncoder passwordEncoder;


    public MemberService(MemberRepository memberRepository, OrderService orderService, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.orderService = orderService;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(()-> new MemberNotFoundException("no member id : " + id));
    }

    public MemberOrderResponseListDto getMemberByIdWithOrder(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(()-> new MemberNotFoundException("no member id : " + id));

        List<MemberOrderResponseDto> orders = new ArrayList<>();

        for (OrderResponseDto order : orderService.getAllOrders()){
            if (order.getMember().getId() == id){
                orders.add(MemberOrderResponseDto.builder()
                        .id(order.getId())
                        .orderNumber(order.getOrderNumber())
                        .orderPrice(order.getOrderPrice())
                        .products(order.getProducts())
                        .build());
            }
        }

        MemberOrderResponseListDto response = MemberOrderResponseListDto.builder()
                .member(member)
                .orders(orders)
                .build();

        return response;
    }

    public void updateMember(Long id, MemberUpdateRequestDto request) {
        Member member = memberRepository.findById(id).orElseThrow(()-> new MemberNotFoundException("no member id : " + id));
        member.updateMember(passwordEncoder.encode(request.getPassword()), request.getPhoneNumber(), request.getName());
    }

    public void deleteMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(()-> new MemberNotFoundException("no member id : " + id));
        member.updateStatus(MemberStatus.DEACTIVATE.name());
    }

    //유저 email 존재하는지 확인
    public boolean isExistsByEmail(String email){
        return memberRepository.existsByEmail(email);
    }

    //회원 가입
    public Member registerMember(Member resource){
        return memberRepository.save(resource);
    }

    //비밀번호 확인 로직
    public boolean checkMemberPassword(Long id, String password) {
        Member member = memberRepository.findById(id).orElseThrow(()-> new MemberNotFoundException("no member id : " + id));
        return member.authenticate(password, passwordEncoder);
    }

    //로그인한 유저의 Role 확인
    public boolean isAdminRole(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(()-> new MemberNotFoundException("no member id : " + id));
        if (member.getRoles().contains(Role.builder().roleName(RoleName.ROLE_ADMIN).build())){
            return true;
        }
        return false;
    }
}
