package com.demo6.shop.controller.admin;

import com.demo6.shop.dto.PermissionDTO;
import com.demo6.shop.service.PermissionService;
import com.demo6.shop.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PermissionController {
    private static final Logger logger = LoggerFactory.getLogger(PermissionController.class);

    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @GetMapping("/admin/listpermission")
    public String rolePermission(HttpServletRequest request) {
        request.setAttribute("roles", roleService.findAll());
        return "admin/permission/listPermission";
    }

    @PostMapping("/admin/listpermission")
    public String PRolePermission(HttpServletRequest request) {
        String[] ids = request.getParameterValues("roleIds");
        for (String id : ids) {
            roleService.delete(Long.valueOf(id));
        }
        request.setAttribute("roles", roleService.findAll());
        return "/admin/permission/listPermission";
    }

    @GetMapping("/admin/editpermission")
    public String URolePermission(HttpServletRequest request, @RequestParam(value = "roleId") Long roleId) {
        List<PermissionDTO> permissionDTOS = permissionService.findAll();
        request.setAttribute("permissions", permissionDTOS);
        request.setAttribute("roleId", roleId);
        return "/admin/permission/editPermission";
    }

    @PostMapping("/admin/editpermission")
    public String editRolePermission(HttpServletRequest request, @RequestParam(value = "roleId") Long roleId) {
        String[] ids = request.getParameterValues("ids");
        permissionService.editPermission(ids, roleId);
        return "redirect:/admin/listpermission";
    }
}
