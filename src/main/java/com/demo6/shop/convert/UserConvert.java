package com.demo6.shop.convert;

import com.demo6.shop.entity.Role;
import com.demo6.shop.entity.User;
import com.demo6.shop.dto.RoleDTO;
import com.demo6.shop.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserConvert {
    public UserDTO toDTO(User user) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleId(user.getRole().getRoleId());
        roleDTO.setRoleName(user.getRole().getRoleName());

        UserDTO userDTO = new UserDTO();
        userDTO.setFullname(user.getFullname());
        userDTO.setUserId(user.getUserId());
        userDTO.setAvatar(user.getAvatar());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setAddress(user.getAddress());
        userDTO.setPhone(user.getPhone());
        userDTO.setVerify(user.isVerify());
        userDTO.setGender(user.isGender());
        userDTO.setRoleDTO(roleDTO);
        return userDTO;
    }

    public User toEntity(UserDTO userDTO){
        Role role = new Role();
        role.setRoleId(userDTO.getRoleDTO().getRoleId());

        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setAddress(userDTO.getAddress());
        user.setAvatar(userDTO.getAvatar());
        user.setFullname(userDTO.getFullname());
        user.setVerify(userDTO.isVerify());
        user.setGender(userDTO.isGender());
        user.setPassword(userDTO.getPassword());
        user.setRole(role);
        return user;
    }
}
