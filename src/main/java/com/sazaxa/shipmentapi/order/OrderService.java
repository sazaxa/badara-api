package com.sazaxa.shipmentapi.order;

import com.sazaxa.shipmentapi.order.dto.OrderSaveRequestDto;
import com.sazaxa.shipmentapi.order.dto.OrderUpdateRequestDto;
import com.sazaxa.shipmentapi.order.exception.OrderNotFoundException;
import com.sazaxa.shipmentapi.product.Product;
import com.sazaxa.shipmentapi.product.ProductRepository;
import org.apache.commons.lang3.RandomStringUtils;
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

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public void saveOrders(List<OrderSaveRequestDto> request) {
        //Product
        List<Product> products = new ArrayList<>();
        for (OrderSaveRequestDto product : request){
            products.add(Product.builder()
                    .productName(product.getProductName())
                    .width(product.getWidth())
                    .depth(product.getDepth())
                    .height(product.getHeight())
                    .volumeWeight(product.getVolumeWeight())
                    .netWeight(product.getNetWeight())
                    .expectedPrice(product.getExpectedPrice())
                    .koreanInvoice(product.getKoreanInvoice())
                    .koreanShippingCompany(product.getKoreanShippingCompany())
                    .recipientName(product.getRecipientName())
                    .recipientPhoneNumber(product.getRecipientPhoneNumber())
                    .recipientAddress(product.getRecipientAddress())
                    .status(OrderStatus.KOREA_SHIPPING.name())
                    .country(product.getCountry())
                    .userMemo(product.getUserMemo())
                    .build());
        }

        for (Product product : products){
            System.out.println("product : " + product.getWidth());
        }

        //Order
        String orderNumber = new SimpleDateFormat("yyMMdd").format(new Date()) + "-" + RandomStringUtils.randomAlphanumeric(6).toUpperCase();
        Order order = Order.builder()
                .orderNumber(orderNumber)
                .products(products)
                .build();

        orderRepository.save(order);

        List<Order> orders = orderRepository.findAll();
        System.out.println("상품 넓이 : " + orders.get(0).getProducts().get(0).getWidth());

    }

    public Order getOrdersById(Long id) {
        return orderRepository.findById(id).orElseThrow(()-> new OrderNotFoundException("no order id :" + id));
    }

    public void updateOrderById(Long id, OrderUpdateRequestDto request) {
    }

    public void deleteOrdersById(Long id) {
        orderRepository.deleteById(id);
    }

}
