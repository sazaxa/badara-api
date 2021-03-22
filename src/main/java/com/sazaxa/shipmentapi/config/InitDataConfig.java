package com.sazaxa.shipmentapi.config;

import com.sazaxa.shipmentapi.member.MemberRepository;
import com.sazaxa.shipmentapi.member.role.RoleRepository;
import com.sazaxa.shipmentapi.member.role.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitDataConfig implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final RoleService roleService;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    public InitDataConfig(RoleRepository roleRepository, RoleService roleService, MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.roleService = roleService;
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        //Roles 초기화
        //h2-database 사용시 필요
//        Role userRole = Role.builder().roleName(RoleName.ROLE_USER).build();
//        Role adminRole = Role.builder().roleName(RoleName.ROLE_ADMIN).build();
//        roleRepository.save(userRole);
//        roleRepository.save(adminRole);
//
//        Role initAdminRole = roleService.findByRoleName(RoleName.ROLE_ADMIN);
//        Member member = Member.builder()
//                .email("admin@whosegoods.com")
//                .password(passwordEncoder.encode("gntmrntm1@"))
//                .status(MemberStatus.ACTIVATE.name())
//                .roles(Collections.singleton(initAdminRole))
//                .build();
//        memberRepository.save(member);
    }
}
