package com.demo6.shop.dao;

import com.demo6.shop.entity.Product;
import com.demo6.shop.model.StatsByYearDTO;
import com.demo6.shop.model.StatsDTO;

import java.util.List;

public interface ProductDao {
    List<Integer> listYears();

    Double totalOrderPricebyMonthAndYear(Integer month, Integer year);

    int coutStats(Integer month, Integer year);

    List<StatsByYearDTO> totalEachYear();

    List<StatsDTO> listStats(Integer month, Integer year, Integer pageIndex, Integer pageSize);

    void insert(Product product);

    void update(Product product);

    void delete(long productId);

    Product findById(long productId);

    List<Product> findAll(int pageIndex, int pageSize);

    List<Product> findAllByCategoryId(long categoryId, int pageIndex, int pageSize);

    int count();

    int countByCategoryId(long categoryId);

    List<Product> search(Long categoryId, String pricing, float priceFrom, float priceTo, String sort, String text, int pageIndex, int pageSize);

    int countBySearch(Long categoryId, String pricing, float priceFrom, float priceTo, String text);
}
