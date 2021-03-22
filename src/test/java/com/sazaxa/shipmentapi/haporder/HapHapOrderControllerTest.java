package com.sazaxa.shipmentapi.haporder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sazaxa.shipmentapi.haporder.dto.HapOrderSaveRequestDto;
import com.sazaxa.shipmentapi.haporder.dto.HapOrderUpdateRequestDto;
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

@WebMvcTest(HapOrderController.class)
class HapHapOrderControllerTest {

    private final String BASE_URL = "/api/v1/orders";
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private HapOrderService hapOrderService;

    @Test
    void getOrders() throws Exception {
        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk());
    }

    @Test
    void saveOrders() throws Exception {
        List<HapOrderSaveRequestDto> hapOrderSaveRequestDtoList = new ArrayList<>();
        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(hapOrderSaveRequestDtoList)))
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
        HapOrderUpdateRequestDto hapOrderUpdateRequestDto = new HapOrderUpdateRequestDto();
        Long id = 1L;
        mockMvc.perform(put(BASE_URL + "/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(hapOrderUpdateRequestDto)))
                .andExpect(status().isOk());
    }

}
