package com.demo6.shop.service.impl;

import com.demo6.shop.convert.PermissionConverter;
import com.demo6.shop.dao.PermissionDao;
import com.demo6.shop.entity.Permission;
import com.demo6.shop.dto.PermissionDTO;
import com.demo6.shop.dto.RoleDTO;
import com.demo6.shop.service.PermissionService;
import com.demo6.shop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private PermissionConverter permissionConverter;
    @Autowired
    private RoleService roleService;

    @Override
    public void editPermission(String[] ids, Long roleId) {
        Optional<RoleDTO> roleOptional = Optional.ofNullable(roleService.findOne(roleId));
        if (!roleOptional.isPresent()) {
            throw new NotFoundException("not found");
        }
        List<PermissionDTO> permissionDTOS = new ArrayList<>();
        for (String id : ids) {
            permissionDTOS.add(this.findOneById(Long.valueOf(id)));
        }
        roleOptional.get().setPermissionDTOS(permissionDTOS);
        roleService.save(roleOptional.get());
    }


    @Override
    public List<PermissionDTO> findAll() {
        List<PermissionDTO> permissionDTOS = new ArrayList<>();
        List<Permission> permissions = permissionDao.findAll();
        for (Permission permission : permissions) {
            permissionDTOS.add(permissionConverter.toDto(permission));
        }
        return permissionDTOS;
    }

    @Override
    public PermissionDTO findOneById(Long id) {
        return permissionConverter.toDto(permissionDao.findOneById(id));

    }

    @Override
    public List<PermissionDTO> findByRoleId(Long roleId) {
        List<PermissionDTO> permissionDTOS = new ArrayList<>();
        List<Permission> permissions = permissionDao.findByRoleId(roleId);
        permissions.forEach(s -> permissionDTOS.add(permissionConverter.toDto(s)));
        return permissionDTOS;
    }
}
