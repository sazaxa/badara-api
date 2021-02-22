package com.sazaxa.shipmentapi.order;

import com.sazaxa.shipmentapi.order.dto.OrderResponseDto;
import com.sazaxa.shipmentapi.order.dto.OrderSaveRequestDto;
import com.sazaxa.shipmentapi.order.dto.OrderUpdateRequestDto;
import com.sazaxa.shipmentapi.order.exception.OrderNotFoundException;
import com.sazaxa.shipmentapi.product.Product;
import com.sazaxa.shipmentapi.product.ProductRepository;
import com.sazaxa.shipmentapi.product.ProductRepositorySupport;
import com.sazaxa.shipmentapi.product.exception.ProductNotFoundException;
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
    private final ProductRepositorySupport productRepositorySupport;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, ProductRepositorySupport productRepositorySupport) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.productRepositorySupport = productRepositorySupport;
    }

    public List<OrderResponseDto> getOrders() {
        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();
        List<Order> orders = orderRepository.findAll();

        for (Order order : orders){
            List<Product> products = productRepositorySupport.getProductsByOrderId(order.getId());
            orderResponseDtoList.add(OrderResponseDto.builder()
                    .id(order.getId())
                    .orderNumber(order.getOrderNumber())
                    .orderPrice(order.getOrderPrice())
                    .products(products)
                    .build());
        }

        return orderResponseDtoList;
    }

    public void saveOrders(List<OrderSaveRequestDto> request) {

        //Order
        String orderNumber = new SimpleDateFormat("yyMMdd").format(new Date()) + "-" + RandomStringUtils.randomAlphanumeric(6).toUpperCase();
        Order order = Order.builder()
                .orderNumber(orderNumber)
                .build();
        orderRepository.save(order);

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
                    .order(order)
                    .build());
        }
        productRepository.saveAll(products);
    }

    public OrderResponseDto getOrdersById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new OrderNotFoundException("no order id : " + id));
        List<Product> products = productRepositorySupport.getProductsByOrderId(order.getId());

        return OrderResponseDto.builder()
                .id(order.getId())
                .orderPrice(order.getOrderPrice())
                .orderNumber(order.getOrderNumber())
                .products(products)
                .build();
    }

    public void updateOrderById(Long id, OrderUpdateRequestDto request) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new OrderNotFoundException("no id : " + id));
        order.updateOrderPrice(request.getOrderPrice());

        for (Product product : request.getProducts()){
            updateProduct(product);
        }
    }

    public void updateProduct(Product product){
        Product newProduct = productRepository.findById(product.getId()).orElseThrow(()-> new ProductNotFoundException("no id : " + product.getId()));
        newProduct.setStatus(product.getStatus());
        newProduct.setRecipientAddress(product.getRecipientAddress());
        newProduct.setAbroadShippingCompany(product.getAbroadShippingCompany());
        newProduct.setAbroadInvoice(product.getAbroadInvoice());
        newProduct.setAdminMemo(product.getAdminMemo());
    }

}
