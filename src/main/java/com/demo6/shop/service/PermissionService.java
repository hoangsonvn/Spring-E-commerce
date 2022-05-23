package com.demo6.shop.service;

import com.demo6.shop.model.PermissionDTO;

import java.util.List;

public interface PermissionService {
    void editPermission(String[] ids,Long roleId);
    List<PermissionDTO> findAll();
    PermissionDTO findOneById(Long id);

}
