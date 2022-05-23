package com.demo6.shop.service;

import com.demo6.shop.model.RoleDTO;

import java.util.List;

public interface RoleService {
	RoleDTO findOne(Long id);
	List<RoleDTO> findAll();
	void save(RoleDTO roleDTO);
	void delete(Long id);

}
