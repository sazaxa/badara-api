package com.sazaxa.shipmentapi.excel.country;

import com.sazaxa.shipmentapi.security.CustomUserDetailsService;
import com.sazaxa.shipmentapi.security.jwt.JwtAuthenticationEntryPoint;
import com.sazaxa.shipmentapi.security.jwt.JwtTokenProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CountryController.class)
class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountryService countryService;

    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @MockBean
    private CustomUserDetailsService userDetailsService;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @Test
    void testCreate() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "excel.xlsx", MediaType.TEXT_PLAIN_VALUE, "USA".getBytes(StandardCharsets.UTF_8));
        mockMvc.perform(multipart("/excel/country").file(file))
                .andExpect(status().isCreated());
    }

    @Test
    void testDetail() throws Exception {
        mockMvc.perform(get("/excel/country" + "/Taiwan"))
                .andExpect(status().isOk());
    }

}
