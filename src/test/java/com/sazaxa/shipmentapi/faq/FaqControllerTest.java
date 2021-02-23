package com.sazaxa.shipmentapi.faq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sazaxa.shipmentapi.faq.dto.FaqResponseDto;
import com.sazaxa.shipmentapi.faq.dto.FaqSaveRequestDto;
import com.sazaxa.shipmentapi.faq.dto.FaqUpdateRequestDto;
import com.sazaxa.shipmentapi.faq.exception.FaqNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FaqController.class)
class FaqControllerTest {

    @MockBean
    private FaqService faqService;

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper;
    String BASE_URL;
    Long id;
    Faq faq;
    FaqUpdateRequestDto faqUpdateRequestDto;
    FaqSaveRequestDto faqSaveRequestDto;
    FaqResponseDto faqResponseDto;

    @BeforeEach
    void setUp(){
        objectMapper = new ObjectMapper();
        BASE_URL = "/api/v1/faq";
        id =  1L;

        faq = Faq.builder()
                .title("t1")
                .content("c1")
                .build();

        faqUpdateRequestDto = FaqUpdateRequestDto.builder()
                .title("t1")
                .content("c1")
                .build();
        faqSaveRequestDto = FaqSaveRequestDto.builder()
                .title("t1")
                .content("c1")
                .build();
        faqResponseDto = FaqResponseDto.builder()
                .id(id)
                .title("t1")
                .content("c1")
                .build();

        given(faqService.getAllFaq()).willReturn(List.of(faq));
        given(faqService.getFaqById(id)).willReturn(faqResponseDto);
        given(faqService.getFaqById(1000L)).willThrow(new FaqNotFoundException("no faq id : " + id));
        given(faqService.saveFaq(any(FaqSaveRequestDto.class))).willReturn(faqResponseDto);
        given(faqService.updateFaq(eq(1L), any(FaqUpdateRequestDto.class))).willReturn(faqResponseDto);
        given(faqService.deleteFaqById(1000L)).willThrow(new FaqNotFoundException("no faq id : " + id));
    }

    @Test
    void testGetAllFaq() throws Exception {
        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("t1")));

        verify(faqService).getAllFaq();
    }

    @Test
    void testGetFaq() throws Exception {
        mockMvc.perform(get(BASE_URL + "/" + id))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("t1")));

        verify(faqService).getFaqById(id);
    }

    @Test
    void testGetFaqWithNotExistedFaq() throws Exception {
        mockMvc.perform(get(BASE_URL + "/" + 1000))
                .andExpect(status().isNotFound());
    }

    @Test
     void testSaveFaq() throws Exception {
        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(faqSaveRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("t1")));

        verify(faqService).saveFaq(any(FaqSaveRequestDto.class));
    }

    @Test
    void testUpdateFaq() throws Exception {
        mockMvc.perform(put(BASE_URL + "/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(faqUpdateRequestDto)))
                .andExpect(status().isOk());

        verify(faqService).updateFaq(eq(id), any(FaqUpdateRequestDto.class));
    }

    @Test
    void testDeleteWithExistedFaq() throws Exception {
        mockMvc.perform(delete(BASE_URL + "/" + id))
                .andExpect(status().isNoContent());

        verify(faqService).deleteFaqById(id);
//        verify(faqService).deleteFaqById(eq(id));
    }

    @Test
    void testDeleteWithNotExistedFaq() throws Exception {
        mockMvc.perform(delete(BASE_URL + "/" + "1000"))
                .andExpect(status().isNotFound());

        verify(faqService).deleteFaqById(1000L);
    }

}

