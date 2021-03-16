package com.sazaxa.shipmentapi.config;

import com.sazaxa.shipmentapi.member.role.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitDataConfig implements CommandLineRunner {

    private final RoleRepository roleRepository;

    public InitDataConfig(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //Roles 초기화
        //h2-database 사용시 필요
//        Role userRole = Role.builder().roleName(RoleName.ROLE_USER).build();
//        Role adminRole = Role.builder().roleName(RoleName.ROLE_ADMIN).build();
//        roleRepository.save(userRole);
//        roleRepository.save(adminRole);
    }
}
