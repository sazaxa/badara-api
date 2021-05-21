package com.sazaxa.shipmentapi.notice;

import com.sazaxa.shipmentapi.notice.dto.NoticeRequestDto;
import com.sazaxa.shipmentapi.notice.dto.NoticeResponseDto;
import com.sazaxa.shipmentapi.order.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    public List<NoticeResponseDto> list() {
        List<Notice> notices = noticeRepository.findAll();
        List<NoticeResponseDto> responses = new ArrayList<>();
        for (Notice notice : notices){
            NoticeResponseDto response = NoticeResponseDto.builder()
                    .title(notice.getTitle())
                    .content(notice.getContent())
                    .orderStatus(notice.getOrderStatus().status)
                    .build();
            responses.add(response);
        }
        return responses;

    }

    public Notice create(NoticeRequestDto request) {
        Notice notice = Notice.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .orderStatus(OrderStatus.findByKorean(request.getOrderStatus()))
                .build();
        return noticeRepository.save(notice);
    }

}
