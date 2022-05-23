package com.demo6.shop.controller.client;

import com.demo6.shop.entity.Comment;
import com.demo6.shop.entity.Product;
import com.demo6.shop.entity.User;
import com.demo6.shop.model.CommentDTO;
import com.demo6.shop.model.UserPrincipal;
import com.demo6.shop.service.CommentService;
import com.demo6.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("client/comment")
    public String comment(@RequestParam(value = "comment") String comment,
                          @RequestParam(value = "id") Long id, HttpSession session) {
        UserPrincipal userPrincipal = (UserPrincipal) session.getAttribute("user");
        if (userPrincipal == null) {
            return "redirect:/login";
        }
        User user = new User();
        user.setUserId(userPrincipal.getUserId());
        String fullname = userPrincipal.getFullname();
        Product product = new Product();
        product.setProductId(id);
        Comment comments = new Comment();
        comments.setCreateDate(LocalDateTime.now());
        comments.setShortComment(comment);
        comments.setCreateBy(fullname);
        comments.setProduct(product);
        comments.setUser(user);

        commentService.persist(comments);
        return "redirect:/client/product-details?productId=" + id;
    }

    @GetMapping("/admin/deletecomment")
    public String commentDelete(HttpServletRequest request, @RequestParam(value = "id") Long id) {
        commentService.delete(id);
        return "redirect:" + request.getHeader("Referer");
    }
}
