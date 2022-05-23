package com.demo6.shop.dao;

import com.demo6.shop.entity.Permission;

import java.util.List;

public interface PermissionDao {
    Permission findOneById(Long id);
    List<Permission> findAll();
}
