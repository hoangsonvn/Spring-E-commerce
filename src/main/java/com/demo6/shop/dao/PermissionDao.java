package com.demo6.shop.dao;

import com.demo6.shop.entity.Permission;

import java.util.List;

public interface PermissionDao {
    List<Permission> findByRoleId(Long roleId);
    Permission findOneById(Long id);
    List<Permission> findAll();
}
