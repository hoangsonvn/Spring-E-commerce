package com.demo6.shop.dao;

import com.demo6.shop.entity.Role;

import java.util.List;

public interface RoleDao {
    void save(Role role);
    Role findOne(Long id);
    List<Role> findAll();
    void delete(Long id);
}
