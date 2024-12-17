package com.peche.app.service;

import com.peche.app.dto.response.MarketCategoryDto;
import com.peche.app.dto.response.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProductsByMarket(String supermarket);
    List<ProductDto> getProductsByCategory(String category);
    List<ProductDto> getAllProducts();
    List<MarketCategoryDto> getCategoriesBySupermarket();
    List<ProductDto> getBrandByCategory(String category, String supermarket);

    List<ProductDto> getProductsByBrand(String brand, String category, String market);
}
