package com.sazaxa.shipmentapi.point.controller;

import com.sazaxa.shipmentapi.point.service.PointService;
import com.sazaxa.shipmentapi.security.CustomUserDetailsService;
import com.sazaxa.shipmentapi.security.jwt.JwtAuthenticationEntryPoint;
import com.sazaxa.shipmentapi.security.jwt.JwtTokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PointController.class)
class PointControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private PointService pointService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testConfigurePoint() throws Exception {
        mockMvc.perform(post("/api/v1/point/config")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"isRegisterActive\" : true, \"RegisterAmount\" : 3000 } "))
                .andExpect(status().isOk());
    }

    @Test
    void testGetDetail() throws Exception {
        mockMvc.perform(get("/api/v1/point/config"))
                .andExpect(status().isOk());
    }
}
