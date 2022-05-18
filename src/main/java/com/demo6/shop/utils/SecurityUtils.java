package com.demo6.shop.utils;

import com.demo6.shop.model.UserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static UserPrincipal getPrincipal() {
        UserPrincipal myUser = (UserPrincipal) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
        return myUser;
    }
}
