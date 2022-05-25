package com.demo6.shop.controller.client;

import com.demo6.shop.dao.ItemDao;
import com.demo6.shop.entity.Item;
import com.demo6.shop.entity.Order;
import com.demo6.shop.entity.Product;
import com.demo6.shop.dto.CartDTO;
import com.demo6.shop.dto.OrderDTO;
import com.demo6.shop.dto.UserPrincipal;
import com.demo6.shop.service.OrderService;
import com.demo6.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping(value = "/client")
public class CheckoutController {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private MailSender mailSender;

    @GetMapping(value = "/checkout")
    public String checkout(HttpSession session, HttpServletRequest request) {
        UserPrincipal userPrincipal = (UserPrincipal) session.getAttribute("user");
        Map<Long, CartDTO> cart = (Map<Long, CartDTO>) session.getAttribute("cart");
        String home = request.getParameter("home");

        for (Map.Entry<Long, CartDTO> entry : cart.entrySet()) {
            if (entry.getValue().getProductDTO().getQuantity() - entry.getValue().getQuantity() < 0) {
                String mess = "   Product name named   " + entry.getValue().getProductDTO().getProductName() + " is not enough #! ";
                return "redirect:/client/listcart?limit="+mess;
            }
            Integer quanityInStock = entry.getValue().getProductDTO().getQuantity() - entry.getValue().getQuantity();
            entry.getValue().getProductDTO().setQuantity(quanityInStock);
            productService.update(entry.getValue().getProductDTO());
            // compare quantity in stock
        }

        OrderDTO order1 = new OrderDTO();
        Order order = orderService.insert(session, order1);
        for (Map.Entry<Long, CartDTO> entry : cart.entrySet()) {
            Product product = new Product();
            product.setProductId(entry.getValue().getProductDTO().getProductId());
            Item item = new Item();
            item.setProduct(product);
            item.setQuantity(entry.getValue().getQuantity());
            item.setUnitPrice((float) entry.getValue().getTotalPriceSale());
            item.setOrder(order);
            item.setProductName(entry.getValue().getProductDTO().getProductName());
            itemDao.insert(item);
        }
        mailSender.send();
        sendEmail("myanhm55@gmail.com", userPrincipal.getEmail(), "PiFood!",
                "Hello, " + userPrincipal.getFullname() + "! We will deliver it to you soon" +
                        "  Order:  " +
                        "  \n  #OrderId: " + order.getOrderId() +
                        "  \n----PriceTotal:  " + order.getPriceTotal() +
                        "  \n----BuyDate: " + order.getBuyDate() +
                        "  \n----Customer: " + order.getBuyer().getFullname() +
                        "  \n----Adress: " + order.getBuyer().getAddress() +
                        "  \n----Phone: " + order.getBuyer().getPhone() +
                        " \n ===== Have a nice day <3! =====");

        cart.clear();
        session.removeAttribute("TotalQuantyCart");
        session.removeAttribute("TotalPriceCartSale");
        if (home != null) {
            return "redirect:/client/my-order";
        } else {
            return "redirect:/client/pay?price=" + order.getPriceTotal();
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