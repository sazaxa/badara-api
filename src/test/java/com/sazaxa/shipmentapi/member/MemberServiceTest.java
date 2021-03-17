package com.sazaxa.shipmentapi.member;

import com.sazaxa.shipmentapi.member.role.Role;
import com.sazaxa.shipmentapi.member.role.RoleName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

class MemberServiceTest {

    @Test
    void isAdminRole() {
        Role role = Role.builder()
                .roleName(RoleName.ROLE_ADMIN)
                .build();

        Role role2 = Role.builder()
                .roleName(RoleName.ROLE_ADMIN)
                .build();

        Set<Role> roles = new HashSet<>();
        roles.add(role);

        Member member = Member.builder()
                .roles(roles)
                .build();

        System.out.println(member.getRoles().contains(role));

    }
}
