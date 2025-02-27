package com.sazaxa.shipmentapi.point.service;

import com.sazaxa.shipmentapi.member.MemberRepository;
import com.sazaxa.shipmentapi.point.dto.PointConfigRequestDto;
import com.sazaxa.shipmentapi.point.dto.PointConfigResponseDto;
import com.sazaxa.shipmentapi.point.entity.Point;
import com.sazaxa.shipmentapi.point.repository.PointHistoryRepository;
import com.sazaxa.shipmentapi.point.repository.PointRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class PointServiceTest {

    private PointRepository pointRepository;
    private PointHistoryRepository pointHistoryRepository;
    private MemberRepository memberRepository;
    private PointService pointService;
    private PointConfigRequestDto pointConfigRequestDto;
    private Point point;

    @BeforeEach
    void setUp(){
        pointRepository = mock(PointRepository.class);
        memberRepository = mock(MemberRepository.class);
        pointHistoryRepository = mock(PointHistoryRepository.class);
        pointService = new PointService(pointRepository, pointHistoryRepository, memberRepository);

        pointConfigRequestDto = PointConfigRequestDto.builder()
                .id(1L)
                .isRegisterActive(true)
                .registerAmount(3000D)
                .build();

        point = Point.builder()
                .id(1L)
                .isRegisterActive(false)
                .registerAmount(2000D)
                .build();
    }

    @Test
    void testConfigurePoint(){
        given(pointRepository.findById(1L)).willReturn(Optional.of(point));
        PointConfigResponseDto pointConfigResponseDto = pointService.configurePoint(pointConfigRequestDto);
        assertThat(pointConfigResponseDto.getIsRegisterActive()).isTrue();
    }

    @Test
    void testGetDetail(){
        given(pointRepository.findById(1L)).willReturn(Optional.of(point));
        PointConfigResponseDto pointConfigResponseDto = pointService.detail();
        assertThat(pointConfigResponseDto.getIsRegisterActive()).isFalse();
    }

}
