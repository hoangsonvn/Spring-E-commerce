package com.demo6.shop.factory;

public class To70 implements Operator {
    @Override
    public void apply(Integer priceFrom, Integer priceTo) {
        priceFrom = 50;
        priceTo = 70;
    }
}
