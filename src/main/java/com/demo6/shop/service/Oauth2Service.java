package com.demo6.shop.service;

import com.demo6.shop.dto.UserPrincipal;

public interface Oauth2Service {
    UserPrincipal getInfo(String Name,String nameEmail);
}
