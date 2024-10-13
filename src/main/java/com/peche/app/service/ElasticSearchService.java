package com.peche.app.service;

import com.peche.app.dto.response.ProductDto;

import java.util.List;

public interface ElasticSearchService {

    List<ProductDto> searchProductsByPlazaVea(String nameProduct, String brand);
    List<ProductDto> searchProductsByTottus(String nameProduct, String brand);
    List<ProductDto> searchProductsByMetro(String nameProduct, String brand);
    void deleteAll();
}
