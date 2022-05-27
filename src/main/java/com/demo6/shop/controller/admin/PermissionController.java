package com.demo6.shop.controller.admin;

import com.demo6.shop.dao.UserDao;
import com.demo6.shop.dto.PermissionDTO;
import com.demo6.shop.dto.RoleDTO;
import com.demo6.shop.dto.UserDTO;
import com.demo6.shop.dto.UserPrincipal;
import com.demo6.shop.entity.Permission;
import com.demo6.shop.service.PermissionService;
import com.demo6.shop.service.RoleService;
import com.demo6.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.ResourceBundle;

//@PreAuthorize("hasAnyAuthority('PERMISSION_READ','PERMISSION_UPDATE')")
@Controller
public class PermissionController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");

    @PreAuthorize("hasAuthority('PERMISSION_READ')")
    @GetMapping("/admin/listpermission")
    public String rolePermission(HttpServletRequest request) {
        request.setAttribute("roles", roleService.findAll());
        return "admin/permission/listPermission";
    }

    @PreAuthorize("hasAuthority('PERMISSION_UPDATE')")
    @PostMapping("/admin/listpermission")
    public String PRolePermission(HttpServletRequest request) {
        String[] ids = request.getParameterValues("roleIds");
        for (String id : ids) {
            roleService.delete(Long.valueOf(id));
        }
        request.setAttribute("roles", roleService.findAll());
        return "/admin/permission/listPermission";
    }

    @PreAuthorize("hasAuthority('PERMISSION_UPDATE')")
    @GetMapping("/admin/editpermission")
    public String URolePermission(HttpServletRequest request, @RequestParam(value = "roleId") Long roleId) {
        List<PermissionDTO> permissionUser = permissionService.findByRoleId(roleId);
        List<PermissionDTO> permissionDTOS = permissionService.findAll();
        String tick = request.getParameter("tick");
        if (tick != null) {
            request.setAttribute("tick", resourceBundle.getString("tick"));
        }
        request.setAttribute("userPermission", permissionUser);
        request.setAttribute("permissions", permissionDTOS);
        request.setAttribute("roleId", roleId);
        return "/admin/permission/editPermission";
    }

    @PreAuthorize("hasAuthority('PERMISSION_UPDATE')")
    @PostMapping("/admin/editpermission")
    public String editRolePermission(HttpServletRequest request, @RequestParam(value = "roleId") Long roleId) {
        String[] ids = request.getParameterValues("ids");
        if (ids == null) {
            return "redirect:/admin/editpermission?roleId=" + roleId + "&tick=tick";
        }
        permissionService.editPermission(ids, roleId);
        return "redirect:/admin/listpermission";
    }
}
