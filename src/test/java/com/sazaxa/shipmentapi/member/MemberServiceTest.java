package com.sazaxa.shipmentapi.member;

import com.sazaxa.shipmentapi.member.role.Role;
import com.sazaxa.shipmentapi.member.role.RoleName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
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

    @Test
    void isAdminRoleWithRepository() {

        Role userRole = Role.builder()
                .roleName(RoleName.ROLE_ADMIN)
                .build();

        Member member = Member.builder()
                .email("admin@whosegoods.com")
                .password("testpw")
                .roles(Collections.singleton(userRole))
                .status(MemberStatus.ACTIVATE.name())
                .build();

        Set<Role> set = member.getRoles();
        Iterator<Role> iter = set.iterator();
        while (iter.hasNext()){
            System.out.println("ROLE CHECK : " + iter.next().getRoleName());
        }

        if (set.contains(RoleName.ROLE_ADMIN)){
            System.out.println("admin check");
        }

        if (member.getRoles().contains(Role.builder().roleName(RoleName.ROLE_ADMIN).build())){
            System.out.println("check1");
        }
        System.out.println("check2");

    }
}
