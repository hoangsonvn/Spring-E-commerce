package com.demo6.shop.controller.client;

import com.demo6.shop.common.ICommon;
import com.demo6.shop.model.RoleDTO;
import com.demo6.shop.model.UserDTO;
import com.demo6.shop.model.UserPrincipal;
import com.demo6.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
@RequestMapping(value = "/client")
public class ProfileClientController {

    @Autowired
    private UserService userService;
    @Autowired
    private ICommon iCommon;

    @GetMapping(value = "/profile")
    public String profile() {
        return "client/profile";
    }

    @GetMapping(value = "/profile-from-cart")
    public String profileFromCart(HttpServletRequest request) {
        request.setAttribute("messageError", "Please update your delivery information.");
        return "client/profile";
    }

    @PostMapping(value = "/profile-update")
    public String profileUpdate(HttpServletRequest request, HttpSession session,
                                @RequestParam(name = "fullname", required = false) String fullname,
                                @RequestParam(name = "phone") String phone,
                                @RequestParam(name = "address") String address,
                                @RequestParam(name = "avatarFile") MultipartFile avatarFile) {
        UserPrincipal userPrincipal = (UserPrincipal) request.getSession().getAttribute("user");
        userPrincipal.setFullname(fullname);
        userPrincipal.setPhone(phone);
        userPrincipal.setAddress(address);
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleId(userPrincipal.getRole().getRoleId());
        roleDTO.setRoleName(userPrincipal.getRole().getRoleName());


        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(userPrincipal.getUserId());
        userDTO.setEmail(userPrincipal.getEmail());
        userDTO.setPhone(userPrincipal.getPhone());
        userDTO.setAddress(userPrincipal.getAddress());
        userDTO.setAvatar(userPrincipal.getAvatar());
        userDTO.setFullname(userPrincipal.getFullname());
        userDTO.setVerify(userPrincipal.isVerify());
        userDTO.setGender(userPrincipal.isGender());
        userDTO.setPassword(userPrincipal.getPassword());
        userDTO.setRoleDTO(roleDTO);
        if (avatarFile != null && avatarFile.getSize() > 0) {
            String avatarFilename=iCommon.image(avatarFile);
          /*  String originalFilename = avatarFile.getOriginalFilename();
            int lastIndex = originalFilename.lastIndexOf(".");
            String ext = originalFilename.substring(lastIndex);
            String avatarFilename = System.currentTimeMillis() + ext;
            File newfile = new File("C:\\image_spring_boot\\" + avatarFilename);
            FileOutputStream fileOutputStream;
            try {
                fileOutputStream = new FileOutputStream(newfile);
                fileOutputStream.write(avatarFile.getBytes());
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            userDTO.setAvatar(avatarFilename);
            userPrincipal.setAvatar(avatarFilename);

        }

        userService.update(userDTO);


        request.setAttribute("messageSuccess", "Update delivery information successful.");
        return "client/profile";
    }

    @PostMapping(value = "/change-password")
    public String changePassword(HttpServletRequest request) {
        String oldpassword = request.getParameter("oldpassword");
        String newpassword = request.getParameter("newpassword");
        String repassword = request.getParameter("repassword");

        UserPrincipal userPrincipal = (UserPrincipal) request.getSession().getAttribute("user");
//        String passwordEncoder = new BCryptPasswordEncoder().encode(oldpassword);

        if (!new BCryptPasswordEncoder().matches(oldpassword, userPrincipal.getPassword())) {
            request.setAttribute("messageSuccess", "invalid password");
        } else if (!newpassword.equals(repassword)) {
            request.setAttribute("messageSuccess", "password and repassword not match!");
        } else {
            UserDTO userDTO = userService.findByEmail(userPrincipal.getEmail());
            userDTO.setPassword(new BCryptPasswordEncoder().encode(newpassword));
            userService.update(userDTO);
            request.setAttribute("messageSuccess", "Change Password successful: ");

        }

        return "client/profile";
    }
}
