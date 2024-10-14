package com.peche.app.service.impl;

import com.peche.app.dto.response.ProductDto;
import com.peche.app.exception.IllegalParameterException;
import com.peche.app.mapper.ProductMapper;
import com.peche.app.persistence.entity.elastic.ProductMetroElastic;
import com.peche.app.persistence.entity.elastic.ProductTottusElastic;
import com.peche.app.persistence.entity.elastic.ProductVeaElastic;
import com.peche.app.persistence.repository.elastic.ElasticMetroRepository;
import com.peche.app.persistence.repository.elastic.ElasticTottusRepository;
import com.peche.app.persistence.repository.elastic.ElasticVeaRepository;
import com.peche.app.service.ElasticSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

import static com.peche.app.constants.GenConstant.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ElasticSearchImpl implements ElasticSearchService {

    private final ElasticTottusRepository elasticTottusRepository;
    private final ElasticMetroRepository elasticMetroRepository;
    private final ElasticVeaRepository elasticVeaRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDto> searchProductsByPlazaVea(String nameProduct, String brand) {
        String nameMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("{} - {}", LOGGER_SRV_INIT, nameMethod);

        validateSearchParams(nameProduct, brand);

        List<ProductVeaElastic> products = elasticVeaRepository.findByCustomQuery(nameProduct, brand);
        if (products.isEmpty()) {
            log.warn("{} - {}", LOGGER_SRV_FAIL, nameMethod);
            return Collections.emptyList();
        }

        log.info("{} - {} - Found {} products plaza vea", LOGGER_SRV_COMPLETED, nameMethod, products.size());
        return products.stream().map(productMapper::mapToProductVeaDto).toList();
    }

    @Override
    public List<ProductDto> searchProductsByTottus(String nameProduct, String brand) {
        String nameMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("{} - {}", LOGGER_SRV_INIT, nameMethod);

        validateSearchParams(nameProduct, brand);

        List<ProductTottusElastic> products = elasticTottusRepository.findByCustomQuery(nameProduct, brand);
        if (products.isEmpty()) {
            log.warn("{} - {}", LOGGER_SRV_FAIL, nameMethod);
            return Collections.emptyList();
        }

        log.info("{} - {} - Found {} products tottus", LOGGER_SRV_COMPLETED, nameMethod, products.size());
        return products.stream().map(productMapper::mapToProductTottusDto).toList();
    }

    @Override
    public List<ProductDto> searchProductsByMetro(String nameProduct, String brand) {
        String nameMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("{} - {}", LOGGER_SRV_INIT, nameMethod);

        validateSearchParams(nameProduct, brand);

        List<ProductMetroElastic> products = elasticMetroRepository.findByCustomQuery(nameProduct, brand);
        if (products.isEmpty()) {
            log.warn("{} - {}", LOGGER_SRV_FAIL, nameMethod);
            return Collections.emptyList();
        }

        log.info("{} - {} - Found {} products Metro", LOGGER_SRV_COMPLETED, nameMethod, products.size());
        return products.stream().map(productMapper::mapToProductMetroDto).toList();
    }

    private void validateSearchParams(String nameProduct, String brand) {
        if (StringUtils.containsWhitespace(nameProduct) && StringUtils.containsWhitespace(brand)) {
            log.warn("{} - {} - {}", LOGGER_SRV_FAIL, "validateSearchParams", EXCEPTION_ILLEGAL_PARAMETERS);
            throw new IllegalParameterException(EXCEPTION_ILLEGAL_PARAMETERS, nameProduct, brand);
        }
    }
}
