package com.sazaxa.shipmentapi.point.service;

import com.sazaxa.shipmentapi.member.Member;
import com.sazaxa.shipmentapi.member.MemberRepository;
import com.sazaxa.shipmentapi.point.dto.PointConfigRequestDto;
import com.sazaxa.shipmentapi.point.dto.PointConfigResponseDto;
import com.sazaxa.shipmentapi.point.entity.Point;
import com.sazaxa.shipmentapi.point.entity.PointHistory;
import com.sazaxa.shipmentapi.point.errors.PointNotFoundException;
import com.sazaxa.shipmentapi.point.repository.PointHistoryRepository;
import com.sazaxa.shipmentapi.point.repository.PointRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class PointService {
    private final PointRepository pointRepository;
    private final PointHistoryRepository pointHistoryRepository;
    private final MemberRepository memberRepository;

    public PointService(PointRepository pointRepository, PointHistoryRepository pointHistoryRepository, MemberRepository memberRepository) {
        this.pointRepository = pointRepository;
        this.pointHistoryRepository = pointHistoryRepository;
        this.memberRepository = memberRepository;
    }

    public PointConfigResponseDto configurePoint(PointConfigRequestDto pointConfigRequestDto) {
        Point point = getPointInfo();
        point.update(pointConfigRequestDto);
        return PointConfigResponseDto.of(point);
    }

    public PointConfigResponseDto detail() {
        return PointConfigResponseDto.of(getPointInfo());
    }

    public Point getPointInfo(){
        return pointRepository.findById(1L).orElseThrow(()-> new PointNotFoundException("no point id : " + "1"));
    }

    public List<PointHistory> getPointHistoryWithMember(Member member){
        return pointHistoryRepository.findByMember(member);
    }

}
