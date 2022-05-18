package com.demo6.shop.controller.authen;

import com.demo6.shop.model.UserDTO;
import com.demo6.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class ForgotController {
    @Autowired
    private RegisterController registerController;
    @Autowired
    private UserService userService;
    @Autowired
    private MailSender mailSender;

    @GetMapping("/forgotpassword")
    public String forgot() {
        return "/authen/forgotpassword";
    }

    @PostMapping(value = "/email")
    public String forgotPost(@RequestParam(value = "email") String email, HttpServletRequest request, HttpSession session) {
        String code = registerController.randomString(8);
        session.setAttribute("email", email);
        Optional<UserDTO> userDTO = Optional.ofNullable(userService.findByEmail(email));
        if (userDTO.isPresent()) {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setFrom("myanhm02@gmail.com");
            mail.setTo(email);
            mail.setSubject("reset password");
            mail.setText("Hello, " + email.split("@")[0] + " code cua ban la: " + code);
            mailSender.send(mail);
            String mess = "  please check email:  ";
            session.setAttribute("mess", mess);
            session.setAttribute("code", code);
            return "redirect:/code";
        } else {
            request.setAttribute("wrong", "khong ton tai gmail");
            return "authen/forgotpassword";
        }
    }

    @GetMapping("/code")
    public String code() {
        return "authen/verifyForgot";
    }

    @PostMapping(value = "codecheck")

    public String codePost(HttpServletRequest request, HttpSession session) {
        String verifyCode = request.getParameter("code");
        String code = (String) session.getAttribute("code");
        if (code.equals(verifyCode)) {
            return "redirect:/newpassword";
        } else {
            request.setAttribute("invalid", "invalid code");
            return "authen/verifyForgot";
        }
    }



    @GetMapping(value = "/forgotpassword/resend-code")
    public String resendCode(HttpSession session, HttpServletRequest request) {
        String code = registerController.randomString(8);
        String email = (String) session.getAttribute("email");
        sendEmail("myanhm02@gmail.com", email, "PiFood!",
                "Hello, " + email.split("@")[0] + "! Please confirm that you can login in PiFood!" + " Your confirmation code is: " + code);
        session.setAttribute("code", code);
        return "redirect:/code";
    }



    @GetMapping("newpassword")
    public String newPasswordGet() {
        return "authen/newpassword";
    }

    @PostMapping(value = "newpasswordget")
    public String newPassword(@RequestParam(value = "password") String password,
                              @RequestParam(value = "repassword") String repassword,
                              HttpSession session,
                              HttpServletRequest request) {
        String email = (String) session.getAttribute("email");
        UserDTO userDTO = userService.findByEmail(email);
        if (password.equals(repassword)) {
            userDTO.setPassword(new BCryptPasswordEncoder().encode(password));
            userService.update(userDTO);
            return "redirect:/login";
        } else {
            request.setAttribute("error", "password not match");
            return "authen/newpassword";
        }
    }
    public void sendEmail(String from, String to, String subject, String content) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(content);

        mailSender.send(mailMessage);
    }

}
