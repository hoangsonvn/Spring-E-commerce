package com.demo6.shop.convert;

import com.demo6.shop.entity.Role;
import com.demo6.shop.dto.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RoleConverter {
    @Autowired
    private PermissionConverter permissionConverter;

    public Role toEntity(RoleDTO roleDTO) {
        Role role = new Role();
        role.setRoleId(roleDTO.getRoleId());
        role.setRoleName(roleDTO.getRoleName());
        role.setTitle(role.getTitle());
        role.setDescription(roleDTO.getDescription());
        try {
            role.setPermissions(roleDTO.getPermissionDTOS().stream().map(s -> permissionConverter.toEntity(s)).collect(Collectors.toList()));
        } catch (Exception e) {
        }
        return role;
    }

    public RoleDTO toDto(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleId(role.getRoleId());
        roleDTO.setRoleName(role.getRoleName());
        roleDTO.setTitle(role.getTitle());
        roleDTO.setDescription(role.getDescription());
        return roleDTO;
    }
}
