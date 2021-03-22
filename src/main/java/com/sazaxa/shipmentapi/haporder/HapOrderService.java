package com.sazaxa.shipmentapi.haporder;

import com.sazaxa.shipmentapi.member.Member;
import com.sazaxa.shipmentapi.member.MemberRepository;
import com.sazaxa.shipmentapi.member.exception.MemberNotFoundException;
import com.sazaxa.shipmentapi.haporder.dto.HapOrderResponseDto;
import com.sazaxa.shipmentapi.haporder.dto.HapOrderSaveRequestDto;
import com.sazaxa.shipmentapi.haporder.dto.HapOrderUpdateRequestDto;
import com.sazaxa.shipmentapi.haporder.dto.HapOrderUpdateStatusRequestDto;
import com.sazaxa.shipmentapi.haporder.exception.HapOrderNotFoundException;
import com.sazaxa.shipmentapi.order.Order;
import com.sazaxa.shipmentapi.order.OrderRepository;
import com.sazaxa.shipmentapi.order.OrderRepositorySupport;
import com.sazaxa.shipmentapi.order.exception.OrderNotFoundException;
import com.sazaxa.shipmentapi.security.UserPrincipalCustom;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class HapOrderService {

    private final HapOrderRepository hapOrderRepository;
    private final OrderRepository orderRepository;
    private final OrderRepositorySupport orderRepositorySupport;
    private final MemberRepository memberRepository;

    public HapOrderService(HapOrderRepository hapOrderRepository, OrderRepository orderRepository, OrderRepositorySupport orderRepositorySupport, MemberRepository memberRepository) {
        this.hapOrderRepository = hapOrderRepository;
        this.orderRepository = orderRepository;
        this.orderRepositorySupport = orderRepositorySupport;
        this.memberRepository = memberRepository;
    }

    public List<HapOrderResponseDto> getAllHapOrders() {
        List<HapOrderResponseDto> hapOrderResponseDtoList = new ArrayList<>();
        List<HapOrder> hapOrders = hapOrderRepository.findAll();

        for (HapOrder hapOrder : hapOrders){
            List<Order> orders = orderRepositorySupport.getProductsByOrderId(hapOrder.getId());
            hapOrderResponseDtoList.add(HapOrderResponseDto.builder()
                    .id(hapOrder.getId())
                    .member(hapOrder.getMember())
                    .hapOrderNumber(hapOrder.getHapOrderNumber())
                    .hapOrderPrice(hapOrder.getHapOrderPrice())
                    .orders(orders)
                    .build());
        }

        return hapOrderResponseDtoList;
    }

    public void saveHapOrders(List<HapOrderSaveRequestDto> request, UserPrincipalCustom currentUser) {

        Member member = memberRepository.findById(currentUser.getId()).orElseThrow(()-> new MemberNotFoundException("no id : " + currentUser.getId()));

        //hapOrder
        String orderNumber = new SimpleDateFormat("yyMMdd").format(new Date()) + "-" + RandomStringUtils.randomAlphanumeric(6).toUpperCase();
        HapOrder hapOrder = HapOrder.builder()
                .member(member)
                .hapOrderNumber(orderNumber)
                .build();
        hapOrderRepository.save(hapOrder);

        //order
        List<Order> orders = new ArrayList<>();
        for (HapOrderSaveRequestDto order : request){
            Order newOrder = Order.builder()
                    .orderName(order.getOrderName())
                    .width(order.getWidth())
                    .depth(order.getDepth())
                    .height(order.getHeight())
                    .volumeWeight(order.getVolumeWeight())
                    .netWeight(order.getNetWeight())
                    .expectedPrice(order.getExpectedPrice())
                    .koreanInvoice(order.getKoreanInvoice())
                    .koreanShippingCompany(order.getKoreanShippingCompany())
                    .recipientName(order.getRecipientName())
                    .recipientPhoneNumber(order.getRecipientPhoneNumber())
                    .recipientAddress(order.getRecipientAddress())
                    .status(HapOrderStatus.CENTER_INCOME.status)
                    .country(order.getCountry())
                    .userMemo(order.getUserMemo())
                    .hapOrder(hapOrder)
                    .build();
            if (order.getKoreanInvoice().isBlank()){
                newOrder.updateStatus(HapOrderStatus.INVOICE.status);
            }
            orders.add(newOrder);
        }
        orderRepository.saveAll(orders);
    }

    public HapOrderResponseDto getHapOrder(Long id) {
        HapOrder hapOrder = hapOrderRepository.findById(id).orElseThrow(()-> new HapOrderNotFoundException("no order id : " + id));
        List<Order> orders = orderRepositorySupport.getProductsByOrderId(hapOrder.getId());

        return HapOrderResponseDto.builder()
                .id(hapOrder.getId())
                .member(hapOrder.getMember())
                .hapOrderPrice(hapOrder.getHapOrderPrice())
                .hapOrderNumber(hapOrder.getHapOrderNumber())
                .orders(orders)
                .build();
    }

    public void updateHapOrder(Long id, HapOrderUpdateRequestDto request) {
        HapOrder hapOrder = hapOrderRepository.findById(id).orElseThrow(()-> new HapOrderNotFoundException("no id : " + id));
        double orderHapPrice = 0;

        for (Order order : request.getOrders()){
            updateOrder(order);
            orderHapPrice += addShippingPrice(order);
        }
        hapOrder.updateOrderPrice(orderHapPrice);
    }

    private double addShippingPrice(Order order) {
        Order newOrder = orderRepository.findById(order.getId()).orElseThrow(()-> new OrderNotFoundException("no id : " + order.getId()));
        return newOrder.getShippingPrice();
    }

    public void updateOrder(Order order){
        Order newOrder = orderRepository.findById(order.getId()).orElseThrow(()-> new OrderNotFoundException("no id : " + order.getId()));
        newOrder.setStatus(order.getStatus());
        newOrder.setRecipientAddress(order.getRecipientAddress());
        newOrder.setAbroadShippingCompany(order.getAbroadShippingCompany());
        newOrder.setAbroadInvoice(order.getAbroadInvoice());
        newOrder.setCountry(order.getCountry());
        newOrder.setAdminNetWeight(order.getAdminNetWeight());
        newOrder.setAdminVolumeWeight(order.getAdminVolumeWeight());
        newOrder.setAdminMemo(order.getAdminMemo());
        newOrder.setShippingPrice(order.getShippingPrice());
    }

    public List<Order> proceedPaymentComplete(Long id, HapOrderUpdateStatusRequestDto request) {
        HapOrder hapOrder = hapOrderRepository.findById(id).orElseThrow(()-> new HapOrderNotFoundException("no haporder id : " + id));
        List<Order> orders = orderRepository.findAllByHapOrder(hapOrder);
        for (Order order : orders){
            order.updateStatus(request.getStatus());
        }
        return orders;
    }
}
