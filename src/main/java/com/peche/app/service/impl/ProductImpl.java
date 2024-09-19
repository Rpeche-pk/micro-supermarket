package com.peche.app.service.impl;

import com.peche.app.dtos.response.ProductDto;
import com.peche.app.exception.IllegalBodyException;
import com.peche.app.exception.NotFoundException;
import com.peche.app.mapper.ProductMapper;
import com.peche.app.persistence.entity.Product;
import com.peche.app.persistence.entity.ProductVea;
import com.peche.app.persistence.repository.MetroRepository;
import com.peche.app.persistence.repository.PlazaVeaRepository;
import com.peche.app.persistence.repository.ProductRepository;
import com.peche.app.persistence.repository.TottusRepository;
import com.peche.app.service.ProductService;
import com.peche.app.utility.Utility;
import com.peche.app.utility.enums.CollectionEnum;
import com.peche.app.utility.enums.MarketEnum;
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
        log.info(String.format(LOGGER_SRV_INIT_NAME, nameMethod));

        if (supermarket == null || supermarket.isBlank() || Pattern.matches(".*\\d.*", supermarket)) {
            log.error(String.format(LOGGER_SRV_FAIL_NAME_ERROR, Thread.currentThread().getStackTrace()[1].getMethodName(), EXCEPTION_ILLEGAL_ARG));
            throw new IllegalBodyException(EXCEPTION_ILLEGAL_ARG, supermarket);
        }

        if (!CollectionEnum.exists(supermarket.strip().toUpperCase())) {
            log.error(String.format(LOGGER_SRV_FAIL_NAME_ERROR, nameMethod, EXCEPTION_ILLEGAL_SUPERMARKET));
            throw new NotFoundException(EXCEPTION_ILLEGAL_SUPERMARKET, supermarket);
        }

        var allProducts = mongoTemplate.findAll(Product.class, supermarket.toUpperCase())
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

        var productsByCategory = plazaVeaRepository.findByCategoriaIgnoreCase(regex)
                .stream()
                .map(productMapper::mapToProduct)
                .toList();

        log.info(String.format(LOGGER_SRV_COMPLETED_NAME, nameMethod));
        return productsByCategory;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        log.info(String.format(LOGGER_SRV_INIT_NAME, Thread.currentThread().getStackTrace()[1].getMethodName()));

        List<ProductDto> productos = new ArrayList<>();

        productos.addAll(plazaVeaRepository.findAll()
                .parallelStream()
                .map(productMapper::mapToProduct)
                .toList());
        log.info(String.format("TOTAL DE PRODUCTOS PARA EL SUPERMERCADO PLAZA VEA %d", (long) plazaVeaRepository.findAll().size()));

        productos.addAll(metroRepository.findAll()
                .parallelStream()
                .map(productMapper::mapToProduct)
                .toList());
        log.info(String.format("TOTAL DE PRODUCTOS PARA EL SUPERMERCADO METRO %d", (long) metroRepository.findAll().size()));

        productos.addAll(tottusRepository.findAll()
                .parallelStream()
                .map(productMapper::mapToProduct)
                .toList());
        log.info(String.format("TOTAL DE PRODUCTOS PARA EL SUPERMERCADO TOTTUS %d", (long) tottusRepository.findAll().size()));
        log.info(String.format(LOGGER_SRV_COMPLETED_NAME, Thread.currentThread().getStackTrace()[1].getMethodName()));

        return productos;
    }
}
