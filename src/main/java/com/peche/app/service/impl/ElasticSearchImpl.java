package com.peche.app.service.impl;

import com.peche.app.dto.response.ProductDto;
import com.peche.app.persistence.repository.elastic.ElasticMetroRepository;
import com.peche.app.persistence.repository.elastic.ElasticTottusRepository;
import com.peche.app.persistence.repository.elastic.ElasticVeaRepository;
import com.peche.app.service.ElasticSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ElasticSearchImpl implements ElasticSearchService {
    
    private final ElasticTottusRepository elasticTottusRepository;
    private final ElasticMetroRepository elasticMetroRepository;
    private final ElasticVeaRepository elasticVeaRepository;

    @Override
    public List<ProductDto> searchProductsByPlazaVea(String nameProduct, String brand) {

        return List.of();
    }

    @Override
    public List<ProductDto> searchProductsByTottus(String nameProduct, String brand) {
        return List.of();
    }

    @Override
    public List<ProductDto> searchProductsByMetro(String nameProduct, String brand) {
        return List.of();
    }

    @Override
    public void deleteAll() {

    }
}
