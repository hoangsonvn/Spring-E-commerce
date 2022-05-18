package com.demo6.shop.convert;

import com.demo6.shop.entity.Category;
import com.demo6.shop.entity.Product;
import com.demo6.shop.entity.Sale;
import com.demo6.shop.model.CategoryDTO;
import com.demo6.shop.model.ProductDTO;
import com.demo6.shop.model.SaleDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public ProductDTO toDto(Product productEntity) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(productEntity.getProductId());
        productDTO.setProductName(productEntity.getProductName());
        productDTO.setPrice(productEntity.getPrice());
        // productDTO.setSaleDTO(productEntity.getSale());
        productDTO.setImage(productEntity.getImage());
        productDTO.setDescription(productEntity.getDescription());
        productDTO.setQuantity(productEntity.getQuantity());

        SaleDTO sale = new SaleDTO();
        sale.setSaleId(productEntity.getSale().getSaleId());
        sale.setSalePercent(productEntity.getSale().getSalePercent());
        productDTO.setSaleDTO(sale);
        // productDTO.setCategoryDTO(productEntity.getCategory());
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(productEntity.getCategory().getCategoryId());
        categoryDTO.setCategoryName(productEntity.getCategory().getCategoryName());
        productDTO.setCategoryDTO(categoryDTO);
        return productDTO;
    }

    public Product toEntity(ProductDTO dto) {
        Product result = new Product();
        result.setProductId(dto.getProductId());
        result.setProductName(dto.getProductName());
        result.setDescription(dto.getDescription());
        result.setImage(dto.getImage());
        result.setPrice(dto.getPrice());
        result.setQuantity(dto.getQuantity());
        Sale sale = new Sale();
        sale.setSaleId(dto.getSaleDTO().getSaleId());
        sale.setSalePercent(dto.getSaleDTO().getSalePercent());
        result.setSale(sale);
        Category category = new Category();
        category.setCategoryId(dto.getCategoryDTO().getCategoryId());
        category.setCategoryName(dto.getCategoryDTO().getCategoryName());
        result.setCategory(category);
        return result;
    }

    public Product toEntity(Product result, ProductDTO dto) {
        result.setQuantity(dto.getQuantity());
        result.setPrice(dto.getPrice());
        result.setProductId(dto.getProductId());
        result.setProductName(dto.getProductName());
        result.setDescription(dto.getDescription());

        return result;
    }
}
