package com.demo6.shop.controller.client;

import com.demo6.shop.model.CommentDTO;
import com.demo6.shop.model.UserPrincipal;
import com.demo6.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Controller
public class CommentController {
    @Autowired
    private UserService userService;

    @PostMapping("client/comment")
    public String comment(@RequestParam(value = "comment") String comment,
                          @RequestParam(value = "id") Long id, HttpSession session) {
        Set<CommentDTO> commentDTOS = (HashSet<CommentDTO>) session.getAttribute("comments");
        if (commentDTOS == null) {
            commentDTOS = new HashSet<>();
        }
        UserPrincipal userPrincipal = (UserPrincipal) session.getAttribute("user");
        if (userPrincipal == null) {
            return "redirect:/login";
        }
        String fullname = userPrincipal.getFullname();

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setTimecomment(LocalDateTime.now());
        commentDTO.setComment(comment);
        commentDTO.setFullname(fullname);
        commentDTO.setId(id);
        commentDTOS.add(commentDTO);

        session.setAttribute("comments", commentDTOS);
        return "redirect:/client/product-details?productId=" + id;

    }
}
