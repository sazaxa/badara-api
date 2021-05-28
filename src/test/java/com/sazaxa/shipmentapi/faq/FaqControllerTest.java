package com.sazaxa.shipmentapi.faq;

import com.sazaxa.shipmentapi.faq.errors.FaqNotFoundException;
import com.sazaxa.shipmentapi.security.CustomUserDetailsService;
import com.sazaxa.shipmentapi.security.jwt.JwtAuthenticationEntryPoint;
import com.sazaxa.shipmentapi.security.jwt.JwtTokenProvider;
import org.hamcrest.core.StringContains;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FaqController.class)
@DisplayName("FaqController 테스트")
class FaqControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FaqService faqService;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    private ClassPathResource IMAGE;
    private static final String  BASE_URL = "/api/v1/faq";

    private static final Long EXISTED_ID =1L;
    private static final String EXISTED_FAQ_TITLE = "existedTitle";
    private static final String EXISTED_FAQ_CONTENT = "existedContent";

    private static final Long NOT_EXISTED_ID =100L;

    private Faq existedFaq;

    @BeforeEach
    void setUp(){
        IMAGE = new ClassPathResource("국가정보_210423.xlsx");

        existedFaq = Faq.builder()
                .id(EXISTED_ID)
                .title(EXISTED_FAQ_TITLE)
                .content(EXISTED_FAQ_CONTENT)
                .build();
    }

    @Nested
    @DisplayName("list 메소드는")
    class Describe_list{
        @DisplayName("전체 FAQ 리스트와 OK를 리턴한다.")
        @Test
        void itReturnFaqListAndOkHttpStatus() throws Exception {
            given(faqService.list()).willReturn(List.of(existedFaq));

            mockMvc.perform(get(BASE_URL))
                    .andExpect(content().string(StringContains.containsString(EXISTED_FAQ_TITLE)))
                    .andExpect(status().isOk());

            verify(faqService).list();
        }
    }

    @Nested
    @DisplayName("detail 메소드는")
    class Describe_detail{
        @Nested
        @DisplayName("만약 존재하는 아이디가 주어진다면")
        class Context_WithExistedId{
            @Test
            @DisplayName("주어진 아이디에 해당하는 FAQ와 OK를 리턴한다.")
            void itReturn() throws Exception {
                given(faqService.detail(EXISTED_ID)).willReturn(existedFaq);

                mockMvc.perform(get(BASE_URL + "/" + EXISTED_ID))
                        .andExpect(content().string(StringContains.containsString(EXISTED_FAQ_TITLE)))
                        .andExpect(status().isOk());

                verify(faqService).detail(EXISTED_ID);
            }
        }

        @Nested
        @DisplayName("만약 존재하지 않는 아이디가 주어진다면")
        class Context_WithNotExistedId{
            @Test
            @DisplayName("FAQ를 찾을수 없다는 예외를 던지고 NOT_FOUND를 리턴한다.")
            void itReturn() throws Exception {
                given(faqService.detail(NOT_EXISTED_ID)).willThrow(new FaqNotFoundException("no faq id" + NOT_EXISTED_ID));

                mockMvc.perform(get(BASE_URL + "/" + NOT_EXISTED_ID))
                        .andExpect(status().isNotFound());

                verify(faqService).detail(NOT_EXISTED_ID);
            }
        }

    }

    @Test
    void testUploadImage() throws Exception {
        MockMultipartFile image = new MockMultipartFile("image", "green.PNG", MediaType.IMAGE_PNG_VALUE, IMAGE.getInputStream());
        mockMvc.perform(multipart(BASE_URL + "/" +"/upload").file(image))
                .andExpect(status().isCreated());
    }

}
