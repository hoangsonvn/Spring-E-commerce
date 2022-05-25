package com.demo6.shop.dto;

public class UserDTO {

	private long userId;
	private String email;
	private String password;
	private String fullname;
	private String phone;
	private String address;
	private boolean gender;
	private boolean verify;
	private RoleDTO roleDTO;
	private String avatar;

	public UserDTO() {
	}

	public UserDTO(String email, String fullname, boolean verify, RoleDTO roleDTO) {
		this.email = email;
		this.fullname = fullname;
		this.verify = verify;
		this.roleDTO = roleDTO;
	}

	public UserDTO(String email,long userId, String password, String fullname, String phone, String address, boolean gender, RoleDTO roleDTO, String avatar,boolean verify) {
		this.userId = userId;
		this.email=email;
		this.password = password;
		this.fullname = fullname;
		this.phone = phone;
		this.address = address;
		this.gender = gender;
		this.roleDTO = roleDTO;
		this.avatar = avatar;
		this.verify = verify;
	}

	public UserDTO(long userId, String email, String password, String fullname, String phone, String address, boolean gender, boolean verify, RoleDTO roleDTO, String avatar) {
		this.userId = userId;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.phone = phone;
		this.address = address;
		this.gender = gender;
		this.verify = verify;
		this.roleDTO = roleDTO;
		this.avatar = avatar;
	}

	public UserDTO(String email, String password, String fullname, String phone, String address, boolean gender, boolean verify, RoleDTO roleDTO, String avatar) {
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.phone = phone;
		this.address = address;
		this.gender = gender;
		this.verify = verify;
		this.roleDTO = roleDTO;
		this.avatar = avatar;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public boolean isVerify() {
		return verify;
	}

	public void setVerify(boolean verify) {
		this.verify = verify;
	}

	public RoleDTO getRoleDTO() {
		return roleDTO;
	}

	public void setRoleDTO(RoleDTO roleDTO) {
		this.roleDTO = roleDTO;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	
}
