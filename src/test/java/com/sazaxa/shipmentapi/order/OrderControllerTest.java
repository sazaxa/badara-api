package com.sazaxa.shipmentapi.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sazaxa.shipmentapi.order.dto.OrderSaveRequestDto;
import com.sazaxa.shipmentapi.order.dto.OrderUpdateRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    private final String BASE_URL = "/api/v1/orders";
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    void getOrders() throws Exception {
        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk());
    }

    @Test
    void saveOrders() throws Exception {
        List<OrderSaveRequestDto> orderSaveRequestDtoList = new ArrayList<>();
        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderSaveRequestDtoList)))
                .andExpect(status().isCreated());
    }

    @Test
    void getOrdersById() throws Exception {
        Long id = 1L;
        mockMvc.perform(get(BASE_URL + "/" + id))
                .andExpect(status().isOk());
    }

    @Test
    void updateOrderById() throws Exception {
        OrderUpdateRequestDto orderUpdateRequestDto = new OrderUpdateRequestDto();
        Long id = 1L;
        mockMvc.perform(put(BASE_URL + "/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderUpdateRequestDto)))
                .andExpect(status().isOk());
    }

}