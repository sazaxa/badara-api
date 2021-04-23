package com.sazaxa.shipmentapi.excel.country;

import com.sazaxa.shipmentapi.security.CustomUserDetailsService;
import com.sazaxa.shipmentapi.security.jwt.JwtAuthenticationEntryPoint;
import com.sazaxa.shipmentapi.security.jwt.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CountryController.class)
class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @MockBean
    private CustomUserDetailsService userDetailsService;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @Test
    void testCreate() throws Exception {
        mockMvc.perform(post("/excel/country"))
                .andExpect(status().isCreated());
    }

}
