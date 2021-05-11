//package com.sazaxa.shipmentapi.excel.download;
//
//import com.sazaxa.shipmentapi.security.CustomUserDetailsService;
//import com.sazaxa.shipmentapi.security.jwt.JwtAuthenticationEntryPoint;
//import com.sazaxa.shipmentapi.security.jwt.JwtTokenProvider;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(ExcelDownloadController.class)
//class ExcelDownloadControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//
//    @MockBean
//    private CustomUserDetailsService userDetailsService;
//
//    @MockBean
//    private JwtTokenProvider jwtTokenProvider;
//
//    @MockBean
//    private ExcelDownloadService excelDownloadService;
//
//    @Test
//    void testDownloadAllOrders() throws Exception {
//        mockMvc.perform(get("/excel/download/orders"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void testDownloadSelectedOrders() throws Exception {
//        mockMvc.perform(post("/excel/download/orders")
//                .content("{\"orderNumbers\" : [\"test1\", \"test2\"]}"))
//                .andExpect(status().isOk());
//    }
//}
