package com.demo6.shop.controller.client;

import com.demo6.shop.model.CartDTO;
import com.demo6.shop.model.UserPrincipal;
import com.demo6.shop.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Optional;

@Controller
public class CartController {

    @Autowired
    private ICartService cartService;

    @PostMapping(value = "client/addcart")
    public String addCart1(HttpServletRequest request, HttpSession session,
                           @RequestParam(value = "productId") Long id,
                           @RequestParam(value = "quantity", required = false) Integer quantity) {
        HashMap<Long, CartDTO> cart = Optional.ofNullable((HashMap<Long, CartDTO>) session.getAttribute("cart")).orElseGet(() -> new HashMap<>());
        quantity = Optional.ofNullable(quantity).orElse(1);
        cart = cartService.addCart(id, quantity, cart);
        session.setAttribute("cart", cart);
        session.setAttribute("TotalQuantyCart", cartService.totalQuanty(cart));
        session.setAttribute("TotalPriceCartSale", cartService.totalPrice(cart));
        return "redirect:" + request.getHeader("Referer");
    }

    @PostMapping(value = "client/editcart")
    public String editCart(
            HttpServletRequest request, HttpSession session, @RequestParam(name = "productId") long id,
            @RequestParam(name = "quantity") int quanty) {
        HashMap<Long, CartDTO> cart = Optional.ofNullable((HashMap<Long, CartDTO>) session.getAttribute("cart")).orElseGet(() -> new HashMap<>());
        cart = cartService.editCart(id, quanty, cart);
        session.setAttribute("cart", cart);
        session.setAttribute("TotalQuantyCart", cartService.totalQuanty(cart));
        session.setAttribute("TotalPriceCartSale", cartService.totalPrice(cart));
        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping(value = "client/deletecart/{id}")
    public String deleteCart(HttpServletRequest request, HttpSession session, @PathVariable long id) {
        HashMap<Long, CartDTO> cart = Optional.ofNullable((HashMap<Long, CartDTO>) session.getAttribute("cart")).orElseGet(() -> new HashMap<>());
        cart = cartService.Delete(id, cart);
        session.setAttribute("cart", cart);
        session.setAttribute("TotalQuantyCart", cartService.totalQuanty(cart));
        session.setAttribute("TotalPriceCart", cartService.totalPrice(cart));
        return "redirect:" + request.getHeader("Referer");
    }

    @GetMapping("client/listcart")
    public String cart1(HttpSession session) {
        try {
            UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            session.setAttribute("user", userPrincipal);
        } catch (Exception e) {
        }

        return "client/listcart";
    }
}
