package com.sazaxa.shipmentapi.order;

import com.sazaxa.shipmentapi.order.dto.OrderResponseDto;
import com.sazaxa.shipmentapi.order.dto.OrderSaveRequestDto;
import com.sazaxa.shipmentapi.order.exception.OrderNotFoundException;
import com.sazaxa.shipmentapi.product.Product;
import com.sazaxa.shipmentapi.product.ProductRepository;
import com.sazaxa.shipmentapi.product.exception.ProductNotFoundException;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public List<OrderResponseDto> getOrders() {
        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();
        OrderResponseDto orderResponseDto = new OrderResponseDto();

        List<Order> Orders = orderRepository.findAll();
        for (Order order : Orders){
            orderResponseDtoList.add(orderResponseDto.of(order));
        }
        return orderResponseDtoList;
    }

    public void saveOrders(List<OrderSaveRequestDto> request) {
        List<Order> orders = new ArrayList<>();
        String orderNumber = new SimpleDateFormat("yyMMdd").format(new Date()) + "-" + RandomStringUtils.randomAlphanumeric(6).toUpperCase();

        for (OrderSaveRequestDto data : request){

            productRepository.save(
                    Product.builder()
                    .name(data.getProductName())
                    .width(data.getWidth())
                    .depth(data.getDepth())
                    .height(data.getHeight())
                    .volumeWeight(data.getVolumeWeight())
                    .netWeight(data.getNetWeight())
                    .build());

            Product product = productRepository.findById(productRepository.count()).orElseThrow(()->new ProductNotFoundException("no product id : " + productRepository.count()));

            Order order = Order.builder()
                    .recipientName(data.getRecipientName())
                    .recipientPhoneNumber(data.getRecipientPhoneNumber())
                    .recipientAddress(data.getRecipientAddress())
                    .koreanInvoice(data.getKoreanInvoice())
                    .koreanShippingCompany(data.getKoreanInvoice())
                    .status(OrderStatus.KOREA_SHIPPING.status)
                    .orderNumber(orderNumber)
                    .country(data.getCountry())
                    .userMemo(data.getUserMemo())
                    .product(product)
                    .build();

            if (StringUtils.isNotBlank(data.getKoreanInvoice())){ order.updateOrderStatus(OrderStatus.INVOICE.status); }

            orders.add(order);
        }
        orderRepository.saveAll(orders);
    }

    public OrderResponseDto getOrdersById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new OrderNotFoundException("no order id :" + id));
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        return orderResponseDto.of(order);
    }

    public void deleteOrdersById(Long id) {
        orderRepository.deleteById(id);
    }

}
