package com.peche.app.service;

import com.peche.app.dtos.response.ProductDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();
    List<ProductDto> getProductsByBrand(String brand);
    List<ProductDto> getProductsByCategory(String category);
    List<ProductDto> getProductsBySupermarket(String supermarket);
}
