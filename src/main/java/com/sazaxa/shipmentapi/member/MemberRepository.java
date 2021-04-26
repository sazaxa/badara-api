package com.sazaxa.shipmentapi.member;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmail(String email);
    Member findByEmail(String email);
    List<Member> findAllByOrderByCreatedDateDesc();
}
