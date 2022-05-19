package com.demo6.shop.service.impl;

import com.demo6.shop.controller.authen.Oauth2Controller;
import com.demo6.shop.convert.RoleConverter;
import com.demo6.shop.entity.Role;
import com.demo6.shop.model.RoleDTO;
import com.demo6.shop.model.UserDTO;
import com.demo6.shop.model.UserPrincipal;
import com.demo6.shop.service.Oauth2Service;
import com.demo6.shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class Oauth2Serviceimpl implements Oauth2Service {
    private static Logger logger = LoggerFactory.getLogger(Oauth2Serviceimpl.class);

    @Autowired
    private RoleConverter roleConverter;
    @Autowired
    private UserService userService;
    @Override
    public UserPrincipal getInfo(String name,String nameEmail) {
        UserPrincipal userPrincipal;
        RoleDTO roleDTO = new RoleDTO(2,"ROLE_USER");
        Role role = roleConverter.toEntity(roleDTO);
        UserDTO userDTO = userService.findByEmail(nameEmail);
        if (userDTO==null) {
            UserDTO newUserDTO = new UserDTO(name,nameEmail,true,roleDTO);
            logger.info("nếu không có gender sẽ là false");
            UserDTO insertDto = userService.insert(newUserDTO);
            userPrincipal = new UserPrincipal(insertDto.getEmail(),"", SecurityContextHolder.getContext().getAuthentication().getAuthorities(),insertDto.getUserId(),insertDto.getFullname(),insertDto.getEmail(), insertDto.isVerify(),role);
        } else {
             userPrincipal = new UserPrincipal(userDTO.getFullname(), "", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
            userPrincipal.setFullname(userDTO.getFullname());
            userPrincipal.setEmail(userDTO.getEmail());
            userPrincipal.setAvatar(userDTO.getAvatar());
            userPrincipal.setRole(role);
            userPrincipal.setUserId(userDTO.getUserId());
            userPrincipal.setAddress(userDTO.getAddress());
            userPrincipal.setPhone(userDTO.getPhone());

        }
        return userPrincipal;
    }
}
