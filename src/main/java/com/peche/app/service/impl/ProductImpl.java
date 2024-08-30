package com.peche.app.service.impl;

import com.peche.app.dtos.response.ProductDto;
import com.peche.app.exception.IllegalBodyException;
import com.peche.app.exception.NotFoundException;
import com.peche.app.mapper.ProductMapper;
import com.peche.app.persistence.repository.ProductRepository;
import com.peche.app.service.ProductService;
import com.peche.app.utility.Utility;
import com.peche.app.utility.enums.MarketEnums;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

import static com.peche.app.constants.GenConstant.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final Utility utility;

    @Override
    public List<ProductDto> getAllProducts() {
        String nameMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info(String.format(LOGGER_SRV_INIT_NAME, nameMethod));

        var allProducts = productRepository.findAll()
                .stream()
                .map(productMapper::mapToProduct)
                .toList();

        log.info(String.format(LOGGER_SRV_COMPLETED_NAME, nameMethod));
        return allProducts;
    }

    @Override
    public List<ProductDto> getProductsByBrand(String brand) {
        String nameMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
        if (brand == null || brand.isBlank() || Pattern.matches(".*\\d.*", brand)) {
            log.error(String.format(LOGGER_APP_FAIL_NAME_ERROR, nameMethod, "brand is null or empty"));
            return List.of();
        }
        return List.of();
    }

    @Override
    public List<ProductDto> getProductsByCategory(String category) {
        String nameMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info(String.format(LOGGER_SRV_INIT_NAME, nameMethod));

        if (category == null || category.isBlank() || Pattern.matches(".*\\d.*", category)) {
            log.error(String.format(LOGGER_APP_FAIL_NAME_ERROR, nameMethod, EXCEPTION_ILLEGAL_BRAND));
            throw new IllegalBodyException(EXCEPTION_ILLEGAL_BRAND, category);
        }

        var regex = utility.searchRegex(category);

        var productsByCategory = productRepository.findByCategoriaIgnoreCase(regex)
                .stream()
                .map(productMapper::mapToProduct)
                .toList();

        log.info(String.format(LOGGER_SRV_COMPLETED_NAME, nameMethod));
        return productsByCategory;
    }

    @Override
    public List<ProductDto> getProductsBySupermarket(String supermarket) {
        log.info(String.format(LOGGER_SRV_INIT_NAME, Thread.currentThread().getStackTrace()[1].getMethodName()));
        if (supermarket == null || supermarket.isBlank() || Pattern.matches(".*\\d.*", supermarket)) {
            log.error(String.format(LOGGER_SRV_FAIL_NAME_ERROR, Thread.currentThread().getStackTrace()[1].getMethodName(), EXCEPTION_ILLEGAL_ARG));
            throw new IllegalBodyException(EXCEPTION_ILLEGAL_ARG, supermarket);
        }

        if (!MarketEnums.exists(supermarket)) {
            log.error(String.format(LOGGER_SRV_FAIL_NAME_ERROR, Thread.currentThread().getStackTrace()[1].getMethodName(), EXCEPTION_ILLEGAL_SUPERMARKET));
            throw new NotFoundException(EXCEPTION_ILLEGAL_SUPERMARKET, supermarket);
        }

        var regex = utility.searchRegex(supermarket);

        var productsBySupermarket = productRepository.findBySupermercadoIgnoreCase(regex)
                .stream()
                .map(productMapper::mapToProduct)
                .toList();

        log.info(String.format(LOGGER_SRV_COMPLETED_NAME, Thread.currentThread().getStackTrace()[1].getMethodName()));

        return productsBySupermarket;
    }
}
