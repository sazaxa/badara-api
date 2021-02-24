package com.sazaxa.shipmentapi.member.role;

import com.sazaxa.shipmentapi.member.role.exception.RoleNotFoundException;
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
        return roleRepository.findByRoleName(roleName).orElseThrow(()-> new RoleNotFoundException("no roleName : " + roleName));
    }

}
