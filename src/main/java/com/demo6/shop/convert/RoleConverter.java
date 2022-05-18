package com.demo6.shop.convert;

import com.demo6.shop.entity.Role;
import com.demo6.shop.model.RoleDTO;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {
    public Role toEntity(RoleDTO roleDTO){
        Role role = new Role();
        role.setRoleId(roleDTO.getRoleId());
        role.setRoleName(roleDTO.getRoleName());
        return role;
    }
}
