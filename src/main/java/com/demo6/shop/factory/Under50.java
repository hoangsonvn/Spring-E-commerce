package com.demo6.shop.factory;

public class Under50 implements  Operator{
    @Override
    public void apply(Integer priceFrom, Integer priceTo) {
        priceFrom=0;
        priceTo=50;
    }
}
