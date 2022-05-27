package com.demo6.shop.controller.authen;

import com.demo6.shop.dto.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Set;

@Controller
public class LoginController {

    @GetMapping(value = "login")
    public String login(HttpServletRequest request, @RequestParam(name = "err", required = false) String err) {
        request.setAttribute("err", err);
        Set<String> roles = AuthorityUtils.authorityListToSet((SecurityContextHolder.getContext()).getAuthentication().getAuthorities());
        if (roles.contains("ROLE_ADMIN")) {
            return "redirect:/admin/home";
        }
        if (roles.contains("ROLE_USER")) {
            return "redirect:/client/home";
        }
        return "authen/login";
        // xu li da dang nhap nhung truy cap login
    }

    @GetMapping(value = "/403")
    public String acessDenied() {
        return "403";
    }

    @GetMapping(value = "/home") // chuyen den home khi dang nhap thanh cong
    public String loginSuccess(HttpServletRequest request, HttpSession session) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        session.setAttribute("user", userPrincipal);
        String username = userPrincipal.getFullname();
        session.setAttribute("username", username);
        if (request.isUserInRole("USER")) { // neu role la admin thi chuyen den trang admin
            return "redirect:/client/home";
        } else if (session.getAttribute("TotalQuantyCart") != null) {
            return "redirect:/client/listcart";
        } else {
            return "redirect:/admin/home"; // khong phai admin chuyen den trang client
        }
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();// tương đương isAuthenticated
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);// thằng logout này  nó tự remove value thông tin cho mình rồi
        }
        return "redirect:/login";
    }

}
