package com.sazaxa.shipmentapi.member;

import com.sazaxa.shipmentapi.box.Box;
import com.sazaxa.shipmentapi.box.BoxRepository;
import com.sazaxa.shipmentapi.box.dto.BoxResponseDto;
import com.sazaxa.shipmentapi.member.dto.MemberPointRequestDto;
import com.sazaxa.shipmentapi.member.dto.MemberUpdateRequestDto;
import com.sazaxa.shipmentapi.member.errors.MemberNotFoundException;
import com.sazaxa.shipmentapi.member.role.Role;
import com.sazaxa.shipmentapi.member.role.RoleName;
import com.sazaxa.shipmentapi.member.role.RoleService;
import com.sazaxa.shipmentapi.order.Order;
import com.sazaxa.shipmentapi.order.OrderRepository;
import com.sazaxa.shipmentapi.order.dto.OrderResponseDto;
import com.sazaxa.shipmentapi.point.entity.Point;
import com.sazaxa.shipmentapi.point.entity.PointHistory;
import com.sazaxa.shipmentapi.point.repository.PointHistoryRepository;
import com.sazaxa.shipmentapi.point.service.PointService;
import com.sazaxa.shipmentapi.product.Product;
import com.sazaxa.shipmentapi.product.ProductRepository;
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
    private final PointService pointService;
    private final PointHistoryRepository pointHistoryRepository;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, RoleService roleService, OrderRepository orderRepository, ProductRepository productRepository, BoxRepository boxRepository, PointService pointService, PointHistoryRepository pointHistoryRepository) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.boxRepository = boxRepository;
        this.pointService = pointService;
        this.pointHistoryRepository = pointHistoryRepository;
    }

    public List<Member> list() {
        return memberRepository.findAllByOrderByCreatedDateDesc();
    }

    public Member detail(Long id) {
        return findMember(id);
    }

    public List<OrderResponseDto> detailWithOrder(Long id) {
        Member member = findMember(id);
        List<Order> orders = orderRepository.findByMemberOrderByCreatedDateDesc(member);
        List<OrderResponseDto> responses = new ArrayList<>();
        for (Order order : orders){
            List<Box> boxes = boxRepository.findAllByOrder(order);

            OrderResponseDto response = OrderResponseDto.builder()
                    .id(order.getId())
                    .orderNumber(order.getOrderNumber())
                    .expectedOrderPrice(order.getExpectedOrderPrice())
                    .extraPrice(order.getExtraPrice())
                    .orderPrice(order.getOrderPrice())
                    .invoice(order.getInvoice())
                    .shippingCompany(order.getShippingCompany())
                    .adminMemo(order.getAdminMemo())
                    .userMemo(order.getUserMemo())
                    .orderStatus(order.getOrderStatus().status)
                    .depositName(order.getDepositName())
                    .discountPrice(order.getDiscountPrice())
                    .boxes(makeBoxResponseDtoList(boxes))
                    .recipient(order.getRecipient())
                    .build();
            responses.add(response);
        }
        return responses;
    }

    public void update(Long id, MemberUpdateRequestDto request) {
        Member member = findMember(id);
        member.updateMember(passwordEncoder.encode(request.getPassword()), request.getPhoneNumber(), request.getName());
    }

    public void delete(Long id) {
        Member member = findMember(id);
        member.updateStatus(MemberStatus.DEACTIVATE.name());
    }

    //유저 email 존재하는지 확인
    public boolean isExistsByEmail(String email){
        return memberRepository.existsByEmail(email);
    }

    //회원 가입
    public Member registerMember(Member resource){
        Point point = pointService.getPointInfo();
        resource.updatePoint(0D);

        if(point.getIsRegisterActive()){
            resource.updatePoint(point.getRegisterAmount());
            pointHistoryRepository.save(
                    PointHistory.builder()
                    .member(resource)
                    .section("회원가입")
                    .deposit(point.getRegisterAmount())
                    .balance(point.getRegisterAmount())
                    .build());
        }

        return memberRepository.save(resource);
    }

    //비밀번호 확인 로직
    public boolean checkMemberPasswordWithId(Long id, String password) {
        Member member = findMember(id);
        return member.authenticate(password, passwordEncoder);
    }

    public boolean checkMemberPasswordWithEmail(String email, String password) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(()->new MemberNotFoundException("no user email : " + email));
        return member.authenticate(password, passwordEncoder);
    }

    //로그인한 유저의 Role 확인
    public boolean isAdminRole(String email) {

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(()->new MemberNotFoundException("no user email : " + email));

        Role userRole = roleService.findByRoleName(RoleName.ROLE_ADMIN);
        if (member.getRoles().contains(userRole)){
            return true;
        }
        return false;
    }

    public Member findMember(Long id){
        return memberRepository.findById(id).orElseThrow(()-> new MemberNotFoundException("no member id : " + id));
    }

    public Member updatePoint(Long id,MemberPointRequestDto request) {
        Member member =  memberRepository.findById(id).orElseThrow(()-> new MemberNotFoundException("no member id : " + id));

        PointHistory pointHistory = PointHistory.builder()
                .member(member)
                .section("관리자 지급")
                .detail(request.getDetail())
                .deposit(request.getPoint())
                .balance(member.getPoint() + request.getPoint())
                .build();

        pointHistoryRepository.save(pointHistory);

        member.updatePoint(pointHistory.getBalance());
        return member;
    }


    public List<PointHistory> getPointHistory(Long id) {
        return pointService.getPointHistoryWithMember(findMember(id));
    }

    private List<BoxResponseDto> makeBoxResponseDtoList(List<Box> boxes) {
        List<BoxResponseDto> boxResponseDtoList = new ArrayList<>();
        for (Box box : boxes){
            List<Product> products = productRepository.findByBox(box);
            boxResponseDtoList.add(BoxResponseDto.of(box, products));
        }
        return boxResponseDtoList;
    }
}
