package com.demo6.shop.service.impl;

import com.demo6.shop.convert.RoleConverter;
import com.demo6.shop.dao.RoleDao;
import com.demo6.shop.entity.Role;
import com.demo6.shop.dto.RoleDTO;
import com.demo6.shop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RoleConverter roleConvert;

    @Override
    public RoleDTO findOne(Long id) {
        return roleConvert.toDto(roleDao.findOne(id));
    }

    @Override
    public List<RoleDTO> findAll() {
        List<Role> roles = roleDao.findAll();
        List<RoleDTO> roleDTOs = new ArrayList<RoleDTO>();
        for (Role role : roles) {
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setRoleId(role.getRoleId());
            roleDTO.setRoleName(role.getRoleName());
            roleDTOs.add(roleDTO);
        }
        return roleDTOs;
    }

    @Override
    public void save(RoleDTO roleDTO) {
        roleDao.save(roleConvert.toEntity(roleDTO));
    }

    @Override
    public void delete(Long id) {
        roleDao.delete(id);
    }


}
