package com.sazaxa.shipmentapi.order;

import com.sazaxa.shipmentapi.security.CustomUserDetailsService;
import com.sazaxa.shipmentapi.security.jwt.JwtAuthenticationEntryPoint;
import com.sazaxa.shipmentapi.security.jwt.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @MockBean
    private CustomUserDetailsService userDetailsService;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    private String BASE_URL;

//    {
//        "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI1IiwiaWF0IjoxNjE2NTU4MjcwLCJleHAiOjE2MTcxNjMwNzB9.pPuRdqNh9m7AKLyCB86XIawiColY-pOhwUMx2cyLNnU1NMbuF6LhJxcdAmg49BrrE5eypO6DCMseBuoHGVgywA",
//            "tokenType": "Bearer"
//    }

    @BeforeEach
    void setUp(){
        BASE_URL = "/api/v1/orders";
    }

    @Test
    void testGetOrder() throws Exception {
        Long id = 1L;
        Order order = Order.builder()
                .id(id)
                .orderNumber("dummy-test-number")
                .build();

        mockMvc.perform(get(BASE_URL + "/" + id))
                .andExpect(status().isOk());

    }

}
