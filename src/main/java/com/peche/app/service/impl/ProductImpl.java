package com.peche.app.service.impl;

import com.peche.app.dto.response.ProductDto;
import com.peche.app.exception.IllegalParameterException;
import com.peche.app.exception.NotFoundException;
import com.peche.app.mapper.ProductMapper;
import com.peche.app.persistence.entity.Product;
import com.peche.app.persistence.repository.MetroRepository;
import com.peche.app.persistence.repository.PlazaVeaRepository;
import com.peche.app.persistence.repository.TottusRepository;
import com.peche.app.service.ProductService;
import com.peche.app.utility.Utility;
import com.peche.app.utility.enums.CollectionEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static com.peche.app.constants.GenConstant.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductImpl implements ProductService {

    private final TottusRepository tottusRepository;
    private final MetroRepository metroRepository;
    private final PlazaVeaRepository plazaVeaRepository;
    private final ProductMapper productMapper;
    private final Utility utility;
    private final MongoTemplate mongoTemplate;


    @Override
    public List<ProductDto> getAllProductsByMarket(String supermarket) {
        String nameMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("{} - {}", LOGGER_SRV_INIT_NAME, nameMethod);

        if (supermarket == null || supermarket.isBlank() || Pattern.matches(".*\\d.*", supermarket)) {
            log.error("{} - {} - {}", LOGGER_SRV_FAIL_NAME_ERROR, Thread.currentThread().getStackTrace()[1].getMethodName(), EXCEPTION_ILLEGAL_ARG);
            throw new IllegalParameterException(EXCEPTION_ILLEGAL_ARG, supermarket);
        }

        if (!CollectionEnum.exists(supermarket.strip().toUpperCase())) {
            log.error("{} - {} -{}", LOGGER_SRV_FAIL_NAME_ERROR, nameMethod, EXCEPTION_ILLEGAL_SUPERMARKET);
            throw new NotFoundException(EXCEPTION_ILLEGAL_SUPERMARKET, supermarket);
        }

        var allProducts = mongoTemplate.findAll(Product.class, supermarket.toUpperCase())
                .stream()
                .map(productMapper::mapToProduct)
                .toList();

        log.info("{} - {}", LOGGER_SRV_COMPLETED_NAME, nameMethod);
        return allProducts;
    }

//    @Override
//    public List<ProductDto> searchProductsByName(String nameProduct, String brand) {
//        String nameMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
//
//        if ((nameProduct == null || nameProduct.isBlank()) && (brand == null || brand.isBlank())) {
//            log.warn("{} - {} - {}", LOGGER_SRV_FAIL_NAME_ERROR, nameMethod, "name or brand is null or empty");
//            return List.of();
//        }
//
//        List<ProductElastic> byCustomQuery = elasticRepository.findByCustomQuery(nameProduct, brand);
//
//        return byCustomQuery.stream().map(productMapper::mapToProductDto).toList();
//    }

    @Override
    public List<ProductDto> getProductsByCategory(String category) {
        String nameMethod = Thread.currentThread().getStackTrace()[1].getMethodName();
        log.info("{} - {}", LOGGER_SRV_INIT_NAME, nameMethod);

        if (category == null || category.isBlank() || Pattern.matches(".*\\d.*", category)) {
            log.warn("{} - {} -{}", LOGGER_APP_FAIL_NAME_ERROR, nameMethod, EXCEPTION_ILLEGAL_BRAND);
            throw new IllegalParameterException(EXCEPTION_ILLEGAL_BRAND, category);
        }

        var regex = utility.searchRegex(category);

        List<ProductDto> allProductsByCategory = new ArrayList<>();
        allProductsByCategory.addAll(plazaVeaRepository.findByCategoriaIgnoreCase(regex)
                .parallelStream()
                .map(productMapper::mapToProduct)
                .toList());

        allProductsByCategory.addAll(tottusRepository.findByCategoriaIgnoreCase(regex)
                .parallelStream()
                .map(productMapper::mapToProduct)
                .toList());

        allProductsByCategory.addAll(metroRepository.findByCategoriaIgnoreCase(regex)
                .parallelStream()
                .map(productMapper::mapToProduct)
                .toList());
        log.info("{} - {}", LOGGER_SRV_COMPLETED_NAME, nameMethod);
        return allProductsByCategory;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        log.info("{} - {}", LOGGER_SRV_INIT_NAME, Thread.currentThread().getStackTrace()[1].getMethodName());

        List<ProductDto> products = new ArrayList<>();

        products.addAll(plazaVeaRepository.findAll()
                .parallelStream()
                .map(productMapper::mapToProduct)
                .toList());
        log.info("[DETAILS] TOTAL PRODUCTS FOR THE PLAZA VEA SUPERMARKET {}", plazaVeaRepository.count());

        products.addAll(metroRepository.findAll()
                .parallelStream()
                .map(productMapper::mapToProduct)
                .toList());
        log.info("[DETAILS] TOTAL PRODUCTS FOR METRO SUPERMARKET {}", metroRepository.count());

        products.addAll(tottusRepository.findAll()
                .parallelStream()
                .map(productMapper::mapToProduct)
                .toList());
        log.info("[DETAILS] TOTAL PRODUCTS FOR THE TOTTUS SUPERMARKET {}", tottusRepository.count());
        log.info("{} - {}", LOGGER_SRV_COMPLETED_NAME, Thread.currentThread().getStackTrace()[1].getMethodName());
        return products;
    }
}
