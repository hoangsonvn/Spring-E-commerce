package com.demo6.shop.controller.admin;

import com.demo6.shop.common.ICommon;
import com.demo6.shop.constant.SystemConstant;
import com.demo6.shop.service.RoleService;
import com.demo6.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class UserManagementAdminController {
    @Autowired
    private ICommon iCommon;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/user-list")
    public String userList(HttpServletRequest request,
                           @RequestParam(value = "pageIndex", required = false) Integer pageIndex) {
        pageIndex = Optional.ofNullable(pageIndex).orElse(0);
        int count = userService.count();
        Integer totalPage = iCommon.totalPage(count, SystemConstant.PAGESIZE);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("pageIndex", pageIndex);

        request.setAttribute("users", userService.findAll(pageIndex, SystemConstant.PAGESIZE));
        return "admin/user/listUser";
    }

    @GetMapping(value = "/user-create")
    public String userCreate(HttpServletRequest request) {
        request.setAttribute("roles", roleService.findAll());
        return "admin/user/createUser";
    }

    @PostMapping(value = "/user-create")
    public String userCreate(HttpServletRequest request, @RequestParam(name = "email") String email,
                             @RequestParam(name = "fullName", required = false) String fullName, @RequestParam(name = "gender") boolean gender, @RequestParam(name = "phone") String phone,
                             @RequestParam(name = "address") String address, @RequestParam(name = "roleId") long roleId,
                             @RequestParam(name = "password") String password, @RequestParam(name = "repassword") String repassword, @RequestParam(name = "avatarFile") MultipartFile avatarFile) {


        //usercreate(String email,String fullName,boolean gender,String phone,String address, long roleId,String password,String repassword,MultipartFile avatarFile);


        if (userService.findByEmail(email) != null) {
            request.setAttribute("roles", roleService.findAll());
            request.setAttribute("message", "Email already exists!");
            return "admin/user/createUser";
        }
        if (!password.equals(repassword) || password == null || repassword == null) {
            request.setAttribute("roles", roleService.findAll());
            request.setAttribute("message", "Password do not match!");
            return "admin/user/createUser";
        }

        userService.userCreate(email, fullName, gender, phone, address, roleId, password, repassword, avatarFile);

  /*      UserDTO userDTO = new UserDTO();
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleId(roleId);

        userDTO.setEmail(email);
        userDTO.setFullname(fullName);
        userDTO.setGender(gender);
        userDTO.setPhone(phone);
        userDTO.setAddress(address);
        userDTO.setRoleDTO(roleDTO);
        userDTO.setVerify(true);
        if (avatarFile != null && avatarFile.getSize() > 0) {
       *//*     String originalFilename = avatarFile.getOriginalFilename();
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
            }*//*
           // iCommon.image(avatarFile);

            userDTO.setAvatar(  iCommon.image(avatarFile));
        }

        userDTO.setPassword(new BCryptPasswordEncoder().encode(repassword));
        userService.insert(userDTO);*/

        return "redirect:/admin/user-list";
         /*   request.setAttribute("message", "Password do not match!");
            request.setAttribute("roles", roleService.findAll());
            return "admin/user/createUser";*/

    }


    @GetMapping(value = "user-update")
    public String userUpdate(HttpServletRequest request, @RequestParam(name = "userId") long userId) {
        request.setAttribute("roles", roleService.findAll());
        request.setAttribute("user", userService.findById(userId));
        return "admin/user/updateUser";
    }

    @PostMapping(value = "user-update")
    public String userUpdate(HttpServletRequest request, @RequestParam(name = "userId") long userId,
                             @RequestParam(name = "fullName", required = false) String fullName, @RequestParam(name = "gender") boolean gender, @RequestParam(name = "phone") String phone,
                             @RequestParam(name = "address") String address, @RequestParam(name = "roleId") long roleId,
                             @RequestParam(name = "password") String password, @RequestParam(name = "repassword", required = false) String repassword,
                             @RequestParam(name = "avatarFile") MultipartFile avatarFile, @RequestParam(name = "avatar") String avatar) {


        if (!password.equals(repassword) || password == null) {
            request.setAttribute("message", "Password do not match!");
            request.setAttribute("roles", roleService.findAll());
            request.setAttribute("user", userService.findById(userId));
            return "admin/user/updateUser";
        }

     /*   UserDTO userDTO = userService.findById(userId);
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setRoleId(roleId);

        userDTO.setFullname(fullName);
        userDTO.setGender(gender);
        userDTO.setPhone(phone);
        userDTO.setAddress(address);
        userDTO.setRoleDTO(roleDTO);
        if (avatarFile != null && avatarFile.getSize() > 0) {
            String originalFilename = avatarFile.getOriginalFilename();
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
            }
            userDTO.setAvatar(avatarFilename);
        } else {
            userDTO.setAvatar(avatar);
        }

            userDTO.setPassword(new BCryptPasswordEncoder().encode(repassword));
            userService.update(userDTO);*/
        userService.userUpdate(userId, fullName, gender, phone, address, roleId, password, repassword, avatarFile, avatar);
        return "redirect:/admin/user-list";


    }

    // Delete user

    @GetMapping(value = "/user-delete")
    public String userDelete(HttpServletRequest request) {
        String[] userIds = request.getParameterValues("userId");
        for (String userId : userIds) {
            userService.delete(Long.parseLong(userId));
        }
        return "redirect:/admin/user-list";
    }
}
