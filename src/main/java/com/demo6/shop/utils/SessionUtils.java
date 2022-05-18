package com.demo6.shop.utils;


import javax.servlet.http.HttpSession;

public class SessionUtils {
    public static float totalPriceSaleSession(HttpSession session) {
        float totalSale = (float) session.getAttribute("TotalPriceCartSale");
        return totalSale;
    }

}
