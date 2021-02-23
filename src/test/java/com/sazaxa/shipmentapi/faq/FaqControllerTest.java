package com.sazaxa.shipmentapi.faq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sazaxa.shipmentapi.faq.dto.FaqResponseDto;
import com.sazaxa.shipmentapi.faq.dto.FaqSaveRequestDto;
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
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FaqController.class)
class FaqControllerTest {

    @MockBean
    private FaqService faqService;

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    String BASE_URL = "/api/v1/faq";

    Long id = 1L;

    @BeforeEach
    void setUp(){
    }


    @Test
    void testGetAllFaq() throws Exception {
        Faq faq = Faq.builder()
                .title("t1")
                .content("c1")
                .build();

        given(faqService.getAllFaq()).willReturn(List.of(faq));

        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("t1")));
    }

    @Test
    void testGetFaq() throws Exception {
        FaqResponseDto responseDto = FaqResponseDto.builder()
                .id(id)
                .title("t1")
                .content("c1")
                .build();

        given(faqService.getFaqById(id)).willReturn(responseDto);

        mockMvc.perform(get(BASE_URL + "/" + id))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("t1")));
    }

    @Test
    void testGetFaqWithNotExsitedFaq() throws Exception {
        given(faqService.getFaqById(1000L)).willThrow(new FaqNotFoundException("no faq id : " + id));

        mockMvc.perform(get(BASE_URL + "/" + 1000))
                .andExpect(status().isNotFound());
    }

    @Test
     void testSaveFaq() throws Exception {

        FaqSaveRequestDto faqSaveRequestDto = FaqSaveRequestDto.builder()
                .build();

        FaqResponseDto faqResponseDto = FaqResponseDto
                .builder().build();

        given(faqService.saveFaq(faqSaveRequestDto)).willReturn(faqResponseDto);

        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(faqSaveRequestDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void testUpdateFaq() throws Exception {
        FaqSaveRequestDto faqSaveRequestDto = FaqSaveRequestDto.builder()
                .build();

        mockMvc.perform(put(BASE_URL + "/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(faqSaveRequestDto)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteFaq() throws Exception {
        mockMvc.perform(delete(BASE_URL + "/" + id))
                .andExpect(status().isNoContent());
    }

}

