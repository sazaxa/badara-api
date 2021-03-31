package com.sazaxa.shipmentapi.member;

import com.sazaxa.shipmentapi.box.Box;
import com.sazaxa.shipmentapi.box.BoxRepository;
import com.sazaxa.shipmentapi.box.dto.BoxResponseDto;
import com.sazaxa.shipmentapi.member.dto.MemberUpdateRequestDto;
import com.sazaxa.shipmentapi.member.exception.MemberNotFoundException;
import com.sazaxa.shipmentapi.member.role.Role;
import com.sazaxa.shipmentapi.member.role.RoleName;
import com.sazaxa.shipmentapi.member.role.RoleService;
import com.sazaxa.shipmentapi.order.Order;
import com.sazaxa.shipmentapi.order.OrderRepository;
import com.sazaxa.shipmentapi.order.dto.OrderResponseDto;
import com.sazaxa.shipmentapi.product.Product;
import com.sazaxa.shipmentapi.product.ProductRepository;
import com.sazaxa.shipmentapi.product.dto.ProductResponseDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final BoxRepository boxRepository;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, RoleService roleService, OrderRepository orderRepository, ProductRepository productRepository, BoxRepository boxRepository) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.boxRepository = boxRepository;
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElseThrow(()-> new MemberNotFoundException("no member id : " + id));
    }

    public List<OrderResponseDto> getMemberByIdWithOrder(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(()-> new MemberNotFoundException("no member id : " + id));
        List<Order> orders = orderRepository.findByMember(member);
        List<OrderResponseDto> responses = new ArrayList<>();
        for (Order order : orders){
            List<Product> products = productRepository.findAllByOrder(order);
            List<Box> boxes = boxRepository.findAllByOrder(order);

            OrderResponseDto response = OrderResponseDto.builder()
                    .id(order.getId())
                    .orderNumber(order.getOrderNumber())
                    .expectedOrderPrice(order.getExpectedOrderPrice())
                    .orderPrice(order.getOrderPrice())
                    .invoice(order.getInvoice())
                    .shippingCompany(order.getShippingCompany())
                    .adminMemo(order.getAdminMemo())
                    .userMemo(order.getUserMemo())
                    .orderStatus(order.getOrderStatus().status)
                    .productResponses(ProductResponseDto.ofList(products))
                    .boxResponses(BoxResponseDto.ofList(boxes))
                    .recipient(order.getRecipient())
                    .build();
            responses.add(response);
        }
        return responses;
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
