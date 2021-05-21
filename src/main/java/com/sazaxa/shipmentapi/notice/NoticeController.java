package com.sazaxa.shipmentapi.notice;

import com.sazaxa.shipmentapi.notice.dto.NoticeRequestDto;
import com.sazaxa.shipmentapi.notice.dto.NoticeResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/notices")
@RestController
public class NoticeController {

    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping
    public List<NoticeResponseDto> list(){
        return noticeService.list();
    }

    @PostMapping
    public Notice create(@RequestBody NoticeRequestDto request){
        return noticeService.create(request);
    }


}
