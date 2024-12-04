package com.peche.app.controller;

import com.peche.app.constants.GenConstant;
import com.peche.app.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

        if (products.isEmpty()) {
            log.warn(GenConstant.LOGGER_APP_FAIL);
            return new ResponseEntity<>(Map.of("data", "Not found data"),HttpStatus.NO_CONTENT);
        }
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

