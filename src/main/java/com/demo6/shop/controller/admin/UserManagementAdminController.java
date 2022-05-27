package com.demo6.shop.controller.admin;

import com.demo6.shop.common.ICommon;
import com.demo6.shop.constant.SystemConstant;
import com.demo6.shop.service.RoleService;
import com.demo6.shop.service.UserService;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

//@PreAuthorize("hasAnyAuthority('USER_READ','USER_CREATE','USER_UPDATE','USER_DELETE')")
@Controller
@RequestMapping("/admin")
public class UserManagementAdminController {
    @Autowired
    private ICommon iCommon;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @PreAuthorize("hasAuthority('USER_READ')")
    @GetMapping("/user-list")
    public String userList(HttpServletRequest request,
                           @RequestParam(value = "pageIndex", required = false) Integer pageIndex) {
        pageIndex = Optional.ofNullable(pageIndex).orElse(0);
        int count = userService.count();
        Integer totalPage = iCommon.totalPage(count, SystemConstant.PAGESIZE);
        iCommon.notificate(request);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("users", userService.findAll(pageIndex, SystemConstant.PAGESIZE));
        return "admin/user/listUser";
    }

    @PreAuthorize("hasAuthority('USER_CREATE')")
    @GetMapping(value = "/user-create")
    public String userCreate(HttpServletRequest request) {
        request.setAttribute("roles", roleService.findAll());
        return "admin/user/createUser";
    }

    @PreAuthorize("hasAuthority('USER_CREATE')")
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @PostMapping(value = "/user-create")
    public String userCreate(HttpServletRequest request, @RequestParam(name = "email") String email,
                             @RequestParam(name = "fullName", required = false) String fullName, @RequestParam(name = "gender") boolean gender, @RequestParam(name = "phone") String phone,
                             @RequestParam(name = "address") String address, @RequestParam(name = "roleId") long roleId,
                             @RequestParam(name = "password") String password, @RequestParam(name = "repassword") String repassword, @RequestParam(name = "avatarFile") MultipartFile avatarFile) {
        if (!Arrays.asList(ContentType.IMAGE_JPEG.getMimeType(), ContentType.IMAGE_PNG.getMimeType(), ContentType.IMAGE_GIF.getMimeType()).contains(avatarFile.getContentType())) {
            return "redirect:/admin/home?imageformat=imagefileformat";
        }
        if (userService.findByEmail(email) != null) {
            request.setAttribute("roles", roleService.findAll());
            request.setAttribute("message", "Email already exists!");
            return "admin/user/createUser";
        }
        if (!password.equals(repassword)) {
            request.setAttribute("roles", roleService.findAll());
            request.setAttribute("message", "Password do not match!");
            return "admin/user/createUser";
        }
        userService.userCreate(email, fullName, gender, phone, address, roleId, password, repassword, avatarFile);
        return "redirect:/admin/user-list?messagecreate=userCreate";
    }

    @PreAuthorize("hasAuthority('USER_UPDATE')")
    @GetMapping(value = "user-update")
    public String userUpdate(HttpServletRequest request, @RequestParam(name = "userId") long userId) {
        request.setAttribute("roles", roleService.findAll());
        request.setAttribute("user", userService.findById(userId));
        return "admin/user/updateUser";
    }

    @PreAuthorize("hasAuthority('USER_UPDATE')")
    @ExceptionHandler(MultipartException.class)
    @PostMapping(value = "user-update")
    public String userUpdate(HttpServletRequest request, @RequestParam(name = "email") String email, @RequestParam(name = "userId") long userId,
                             @RequestParam(name = "fullName", required = false) String fullName, @RequestParam(name = "gender") boolean gender, @RequestParam(name = "phone") String phone,
                             @RequestParam(name = "address") String address, @RequestParam(name = "roleId") long roleId,
                             @RequestParam(name = "password") String password, @RequestParam(name = "repassword", required = false) String repassword,
                             @RequestParam(name = "avatarFile") MultipartFile avatarFile, @RequestParam(name = "avatar") String avatar) {
        if (avatarFile.getSize() > 0) {
            if (!Arrays.asList(ContentType.IMAGE_JPEG.getMimeType(), ContentType.IMAGE_PNG.getMimeType(), ContentType.IMAGE_GIF.getMimeType()).contains(avatarFile.getContentType())) {
                return "redirect:/admin/home?imageformat=imagefileformat";
            }
        }
        if (!password.equals(repassword)) {
            request.setAttribute("message", "Password do not match!");
            request.setAttribute("roles", roleService.findAll());
            request.setAttribute("user", userService.findById(userId));
            return "admin/user/updateUser";
        }
        userService.userUpdate(email, userId, fullName, gender, phone, address, roleId, password, repassword, avatarFile, avatar);
        return "redirect:/admin/user-list?messageupdate=userUpdate";
    }

    @PreAuthorize("hasAuthority('USER_DELETE')")
    @GetMapping(value = "/user-delete")
    public String userDelete(HttpServletRequest request) {
        String[] userIds = request.getParameterValues("userId");
        if (userIds == null) {
            return "redirect:/admin/user-list?tick=tick";
        }
        for (String userId : userIds) {
            userService.delete(Long.parseLong(userId));
        }
        return "redirect:/admin/user-list?messagedelete=userDelete";
    }
}
