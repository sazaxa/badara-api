package com.sazaxa.shipmentapi.member.role;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long>{
    Optional<Role> findByRoleName(RoleName roleName);
}
