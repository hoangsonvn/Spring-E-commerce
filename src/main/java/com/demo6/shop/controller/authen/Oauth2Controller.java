package com.demo6.shop.controller.authen;

import com.demo6.shop.service.Oauth2Service;
import com.demo6.shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class Oauth2Controller {
    private static Logger logger = LoggerFactory.getLogger(Oauth2Controller.class);

    @Autowired
    private UserService userService;
    @Autowired
    private Oauth2Service oauth2Service;

    @GetMapping("/loginSuccess")
    public String getLoginInfo(HttpSession session, OAuth2AuthenticationToken authentication) {
        OAuth2User oAuth2User = authentication.getPrincipal();
        Map<?, ?> map = oAuth2User.getAttributes(); //get info user
        String hiddenChangePassord = "hidden";
        session.setAttribute("hidden", hiddenChangePassord);
        logger.info("hide changePassword in profile");
        String name = (String) map.get("name");
        String nameEmail = (String) map.get("email");
/*
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleId(2);
        roleDTO.setRoleName("ROLE_USER");

        Role role = new Role();
        role.setRoleId(2);
        role.setRoleName("ROLE_USER");

        UserDTO userDTO = userService.findByEmail(nameEmail);
        if (userDTO == null) {
            UserDTO newUserDTO = new UserDTO(name,nameEmail,true,roleDTO);
            *//*newUserDTO.setFullname(name);
            newUserDTO.setEmail(nameEmail);
            newUserDTO.setVerify(true);
            newUserDTO.setRoleDTO(roleDTO);*//*
          UserDTO insertDto = userService.insert(newUserDTO);
          //  UserPrincipal userPrincipal = new UserPrincipal(newUserDTO.getEmail(), "", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
            *//*UserPrincipal userPrincipal = new UserPrincipal(insertDto.getEmail(),"",
                    SecurityContextHolder.getContext().getAuthentication().getAuthorities(), insertDto.getUserId(), insertDto.getEmail(),insertDto.getFullname()
                    ,insertDto.getPhone(),insertDto.getAddress(), insertDto.isGender() ,insertDto.isVerify(),role,insertDto.getAvatar());
                    userPrincipal.setFullname(newUserDTO.getFullname());*//*
                    UserPrincipal userPrincipal = new UserPrincipal(insertDto.getEmail(),"",SecurityContextHolder.getContext().getAuthentication().getAuthorities(),insertDto.getUserId(),insertDto.getEmail(),insertDto.getFullname(), insertDto.isVerify(),role);
           *//* userPrincipal.setEmail(newUserDTO.getEmail());
            userPrincipal.setAvatar(newUserDTO.getAvatar());
            userPrincipal.setVerify(newUserDTO.isVerify());
            userPrincipal.setRole(role);
            userPrincipal.setUserId(userService.insert(newUserDTO).getUserId());*//*
            session.setAttribute("user", userPrincipal);

        } else {
            UserPrincipal userPrincipal = new UserPrincipal(userDTO.getFullname(), "", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
            userPrincipal.setFullname(userDTO.getFullname());
            userPrincipal.setEmail(userDTO.getEmail());
            userPrincipal.setAvatar(userDTO.getAvatar());
            userPrincipal.setRole(role);
            userPrincipal.setUserId(userDTO.getUserId());
            userPrincipal.setAddress(userDTO.getAddress());
            userPrincipal.setPhone(userDTO.getPhone());
            session.setAttribute("user", userPrincipal);
        }*/
        session.setAttribute("user", oauth2Service.getInfo(name, nameEmail));

        return "redirect:/client/home";
    }
}