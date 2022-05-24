package com.demo6.shop.dto;

public class StatsDTO {
    private String name;
    private Long quantity;
    private Double totalprice;
    private Double avgprice;

    private Float minprice;
    private Float maxprice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

    public Double getAvgprice() {
        return avgprice;
    }

    public void setAvgprice(Double avgprice) {
        this.avgprice = avgprice;
    }

    public Float getMinprice() {
        return minprice;
    }

    public void setMinprice(Float minprice) {
        this.minprice = minprice;
    }

    public Float getMaxprice() {
        return maxprice;
    }

    public void setMaxprice(Float maxprice) {
        this.maxprice = maxprice;
    }
}
