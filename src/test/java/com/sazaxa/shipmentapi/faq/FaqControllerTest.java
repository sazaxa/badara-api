package com.sazaxa.shipmentapi.faq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sazaxa.shipmentapi.faq.dto.FaqResponseDto;
import com.sazaxa.shipmentapi.faq.dto.FaqSaveRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FaqController.class)
class FaqControllerTest {

    @MockBean
    private FaqService faqService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper = new ObjectMapper();

    String BASE_URL = "/api/v1/faq";

    @Test
    void testGetAllFaq() throws Exception {
        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk());
    }

    @Test
    void testGetFaqById() throws Exception {
        Long id = 1L;
        mockMvc.perform(get(BASE_URL + "/" + id))
                .andExpect(status().isOk());
    }

    @Test
     void testSaveFaq() throws Exception {

        //given
        FaqSaveRequestDto faqSaveResponseDto = new FaqSaveRequestDto(new Faq("t1", "c1"));
        FaqResponseDto faqResponseDto = new FaqResponseDto(new Faq("t1", "c1"));

        given(faqService.saveFaq(faqSaveResponseDto)).willReturn(faqResponseDto);

        MvcResult result = mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(faqSaveResponseDto)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println("content : " + content);

//        //when
//        final ResultActions actions = mockMvc.perform(post(BASE_URL)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON)
//                .content(content));
//
//        //then
//        actions
//                .andExpect(status().isCreated())
//                .andExpect(content().string(containsString("t1")));
//
//        verify(faqService).saveFaq(any());
    }

    void testUpdateFaq(){

    }


    void testDeleteFaq(){

    }

}

