package com.demo6.shop.dto;

import java.math.BigDecimal;
import java.util.Date;

public class ScheduleDTO {
    private String name;
    private BigDecimal quantity;
    private Integer remainingAmount;
    private Double price;
    private Date expirationDate;
    private Double totaPrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public Integer getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(Integer remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Double getTotaPrice() {
        return totaPrice;
    }

    public void setTotaPrice(Double totaPrice) {
        this.totaPrice = totaPrice;
    }
}
