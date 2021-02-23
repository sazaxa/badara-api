package com.sazaxa.shipmentapi.member.role;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByRoleName(RoleName roleName){
        return roleRepository.findByRoleName(roleName);
    }

}
