package com.sazaxa.shipmentapi.notice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class NoticeServiceTest {

    private NoticeRepository noticeRepository = mock(NoticeRepository.class);
    private NoticeService noticeService;

    @BeforeEach
    void setUp(){
        noticeService = new NoticeService(noticeRepository);

        Notice notice = Notice.builder()
                .title("t1")
                .content("c1")
                .build();
        given(noticeRepository.findAll()).willReturn(List.of(notice));
    }

    @Test
    void testGetAllNotices(){
        List<Notice> noticeList = noticeService.getAllNotice();
        assertThat(noticeList).isNotEmpty();
        assertThat(noticeList.get(0).getTitle()).isEqualTo("t1");
    }

}