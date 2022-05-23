package com.demo6.shop.service;

import com.demo6.shop.model.ProductDTO;
import com.demo6.shop.model.StatsDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    void merge(Float newPrice, MultipartFile imageFile, Long productId, Long categoryId, Float oldPrice, String productName, String description, Integer quantity, String image, String saleId);

    void persist(long categoryId, String productName, String description, float price, int quantity, String saleId, MultipartFile multipartFile);

    List<Integer> listYears();

    int countStats(Integer month, Integer year);

    List<StatsDTO> listStats(Integer month, Integer year, Integer pageIndex, Integer pageSize);

//   void insert(ProductDTO productDTO);

    void update(ProductDTO productDTO);

    void delete(long productId);

    ProductDTO findById(long productId);

    List<ProductDTO> findAll(int pageIndex, int pageSize);

    List<ProductDTO> findAllByCategoryId(long categoryId, int pageIndex, int pagesize);

    int count();

    int countByCategoryId(long categoryId);


    List<ProductDTO> search(Long categoryId, String pricing, float priceFrom, float priceTo, String sort, String text, int pageIndex, int pageSize);

    int countBySearch(Long categoryId, String pricing, float priceFrom, float priceTo, String text);
}
