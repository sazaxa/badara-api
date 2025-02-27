package com.sazaxa.shipmentapi.notice;

import com.sazaxa.shipmentapi.notice.dto.NoticeResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@DisplayName("NoticeService Class")
public class NoticeServiceNestedTest {

    NoticeRepository noticeRepository = mock(NoticeRepository.class);
    NoticeService noticeService = new NoticeService(noticeRepository);

    @Nested
    @DisplayName("getAllNotice 메소드는")
    class Describe_getAllNotice{
        @Nested
        @DisplayName("만약 List가 비어 있다면")
        class Context_with_Empty{
            @Test
            @DisplayName("List는 비어있다.")
            void it_returns_Empty_list(){
                List<NoticeResponseDto> result = noticeService.list();
                assertThat(result).isEmpty();
            }
        }
    }
}
