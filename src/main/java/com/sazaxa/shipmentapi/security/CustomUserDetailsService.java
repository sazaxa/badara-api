package com.sazaxa.shipmentapi.security;

import com.sazaxa.shipmentapi.member.Member;
import com.sazaxa.shipmentapi.member.MemberRepository;
import com.sazaxa.shipmentapi.member.errors.MemberNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);
        return UserPrincipalCustom.create(member);
    }

    public UserDetails loadUserById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("no member id : " + id ));

        return UserPrincipalCustom.create(member);
    }
}
