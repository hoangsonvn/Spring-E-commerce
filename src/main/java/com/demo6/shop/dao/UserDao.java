package com.demo6.shop.dao;

import com.demo6.shop.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserDao {

	void insert(User user);
	
	void update(User user);
	
	void delete(long userId);
	
	User findById(long userId);
	
	List<User> findAll(int pageIndex, int pageSize);

	User findByEmailOrPhoneAndPassword(String account, String password, boolean verity);

	User loadUserByUsername(String account);
	
	User findByEmail(String email);
	
	int count();
}
