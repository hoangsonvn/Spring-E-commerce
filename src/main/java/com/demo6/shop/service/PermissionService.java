package com.demo6.shop.service;

import com.demo6.shop.dto.PermissionDTO;
import com.demo6.shop.entity.Permission;

import java.util.List;

public interface PermissionService {
    void editPermission(String[] ids,Long roleId);
    List<PermissionDTO> findAll();
    PermissionDTO findOneById(Long id);
    List<PermissionDTO> findByRoleId(Long roleId);

}
