package com.demo6.shop.service;

import com.demo6.shop.model.UserDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface UserService {
	void userUpdate(long userId, String fullName, boolean gender, String phone, String address, long roleId, String password, String repassword, MultipartFile avatarFile,String avatar);

	void userCreate(String email, String fullName, boolean gender, String phone, String address, long roleId, String password, String repassword, MultipartFile avatarFile);

	UserDTO insert(UserDTO userDTO);
	
	void update(UserDTO userDTO);
	
	void delete(long userId);
	
	UserDTO findById(long userId);
	
	List<UserDTO> findAll(int pageIndex, int PageSize);

	UserDTO findByEmailOrPhoneAndPassword(String account, String password, boolean verity);
	
	UserDTO findByEmail(String email);
	
	int count();
	
}
