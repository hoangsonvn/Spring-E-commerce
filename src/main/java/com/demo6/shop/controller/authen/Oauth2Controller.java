package com.demo6.shop.controller.authen;

import com.demo6.shop.service.Oauth2Service;
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
        session.setAttribute("user", oauth2Service.getInfo(name, nameEmail));
        return "redirect:/client/home";
    }
}