package com.sazaxa.shipmentapi.member.role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{
    Role findByRoleName(RoleName roleName);
}
