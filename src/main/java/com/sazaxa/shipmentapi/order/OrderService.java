package com.sazaxa.shipmentapi.order;

import com.sazaxa.shipmentapi.order.dto.OrderSaveRequestDto;
import com.sazaxa.shipmentapi.product.Product;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class OrderService {
    public void getOrders() {
    }


    public void saveOrders(List<OrderSaveRequestDto> request) {
        int orderAmount = request.size();
        String status = "checking";
        for (OrderSaveRequestDto data : request){
            Product product = new Product(
                    data.getProductName(),
                    data.getWidth(),
                    data.getDepth(),
                    data.getHeight(),
                    data.getVolumeWeight(),
                    data.getNetWeight()
            );

            /*
                String recipientName, String recipientPhoneNumber, String recipientAddress, String koreanInvoice, String status, String orderNumber, String country, Product product) {

             */
            Order order = Order.builder()
                    .recipientName(data.getRecipientName())
                    .recipientPhoneNumber(data.getRecipientPhoneNumber())
                    .recipientAddress(data.getRecipientAddress())
                    .koreanInvoice(data.getKoreanInvoice())
                    .status(status)
                    .orderNumber()
                    .country(data.getCountry())
                    .product(product)
                    .build();
        }
    }
}
