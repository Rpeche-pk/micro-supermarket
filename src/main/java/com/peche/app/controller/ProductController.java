package com.peche.app.controller;

import com.peche.app.constants.GenConstant;
import com.peche.app.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.peche.app.constants.GenConstant.*;

@RequestMapping(VERSION_API_PRODUCTS)
@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;

    @GetMapping(ALL_PRODUCTS)
    public ResponseEntity<?> getAllProductsByMarket(@PathVariable String supermarket) {
        log.info(GenConstant.LOGGER_APP_INIT);
        var products = productService.getAllProductsByMarket(supermarket);
        log.info(GenConstant.LOGGER_APP_COMPLETED);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(PRODUCT_BY_BRAND)
    public ResponseEntity<?> getProductsByBrand(@PathVariable String brand) {
        log.info(GenConstant.LOGGER_APP_INIT);
        var products = productService.getProductsByBrand(brand);
        log.info(GenConstant.LOGGER_APP_COMPLETED);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(PRODUCT_BY_CATEGORY)
    public ResponseEntity<?> getProductsByCategory(@PathVariable String category) {
        log.info(GenConstant.LOGGER_APP_INIT);
        var products = productService.getProductsByCategory(category);
        log.info(GenConstant.LOGGER_APP_COMPLETED);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(ALL_PRODUCTS_SUPERMARKET)
    public ResponseEntity<?> getAllProducts() {
        log.info(GenConstant.LOGGER_APP_INIT);
        var products = productService.getAllProducts();
        log.info(GenConstant.LOGGER_APP_COMPLETED);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}

