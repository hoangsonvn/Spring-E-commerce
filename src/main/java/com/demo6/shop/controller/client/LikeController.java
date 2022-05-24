package com.demo6.shop.controller.client;

import com.demo6.shop.dto.ProductDTO;
import com.demo6.shop.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class LikeController {
    @Autowired
    private LikeService likeService;


    @GetMapping("client/like/{productId}")
    public String insert(@PathVariable(value = "productId") Long id, HttpSession session, HttpServletRequest request) {
        HashMap<Long, ProductDTO> likeMap = (HashMap<Long, ProductDTO>) session.getAttribute("likeMap");
        if (likeMap == null) {
            likeMap = new HashMap<>();
        }
        likeMap = likeService.insert(id, likeMap);
        session.setAttribute("likeMap", likeMap);
        session.setAttribute("size", likeMap.size());
        return "redirect:" + request.getHeader("Referer");
    }

    @GetMapping("client/deletelike/{id}")
    public String deleteLike(HttpSession session, @PathVariable(value = "id") Long id, HttpServletRequest request) {
        HashMap<Long, ProductDTO> likeMap = (HashMap<Long, ProductDTO>) session.getAttribute("likeMap");
        likeService.delete(id, likeMap);
        session.setAttribute("size",likeMap.size());
        return "redirect:" + request.getHeader("Referer");
    }

    @GetMapping("client/listlike")
    public String listLike() {
        return "client/like";
    }
}