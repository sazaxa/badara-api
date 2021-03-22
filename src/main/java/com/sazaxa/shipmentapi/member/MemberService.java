package com.sazaxa.shipmentapi.member;

import com.sazaxa.shipmentapi.member.dto.MemberOrderResponseDto;
import com.sazaxa.shipmentapi.member.dto.MemberOrderResponseListDto;
import com.sazaxa.shipmentapi.member.dto.MemberUpdateRequestDto;
import com.sazaxa.shipmentapi.member.exception.MemberNotFoundException;
import com.sazaxa.shipmentapi.member.role.Role;
import com.sazaxa.shipmentapi.member.role.RoleName;
import com.sazaxa.shipmentapi.member.role.RoleService;
import com.sazaxa.shipmentapi.haporder.HapOrderService;
import com.sazaxa.shipmentapi.haporder.dto.HapOrderResponseDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final HapOrderService hapOrderService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;


    public MemberService(MemberRepository memberRepository, HapOrderService hapOrderService, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.memberRepository = memberRepository;
        this.hapOrderService = hapOrderService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(()-> new MemberNotFoundException("no member id : " + id));
    }

    public MemberOrderResponseListDto getMemberByIdWithOrder(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(()-> new MemberNotFoundException("no member id : " + id));

        List<MemberOrderResponseDto> hapOrders = new ArrayList<>();

        for (HapOrderResponseDto hapOrder : hapOrderService.getAllHapOrders()){
            if (hapOrder.getMember().getId() == id){
                hapOrders.add(MemberOrderResponseDto.builder()
                        .id(hapOrder.getId())
                        .orderNumber(hapOrder.getHapOrderNumber())
                        .orderPrice(hapOrder.getHapOrderPrice())
                        .orders(hapOrder.getOrders())
                        .build());
            }
        }

        MemberOrderResponseListDto response = MemberOrderResponseListDto.builder()
                .member(member)
                .hapOrders(hapOrders)
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
    public boolean checkMemberPasswordWithId(Long id, String password) {
        Member member = memberRepository.findById(id).orElseThrow(()-> new MemberNotFoundException("no member id : " + id));
        return member.authenticate(password, passwordEncoder);
    }

    public boolean checkMemberPasswordWithEmail(String email, String password) {
        Member member = memberRepository.findByEmail(email);
        return member.authenticate(password, passwordEncoder);
    }

    //로그인한 유저의 Role 확인
    public boolean isAdminRole(String email) {
        Member member = memberRepository.findByEmail(email);
        Role userRole = roleService.findByRoleName(RoleName.ROLE_ADMIN);
        if (member.getRoles().contains(userRole)){
            return true;
        }
        return false;
    }
}
