package com.peche.app.controller;

import com.peche.app.constants.GenConstant;
import com.peche.app.service.DataSyncService;
import com.peche.app.service.ElasticSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.peche.app.constants.GenConstant.*;

@RequestMapping(API_ELASTIC_SEARCH)
@RestController
@RequiredArgsConstructor
@Slf4j
public class ElasticController {

    private final DataSyncService dataSyncService;
    private final ElasticSearchService elasticSearchService;

    @GetMapping(SEARCH_PRODUCT_VEA)
    public ResponseEntity<?> getProductsVea(@RequestParam String nameProduct, @RequestParam String brand) {
        log.info(GenConstant.LOGGER_APP_INIT);
        var products = elasticSearchService.searchProductsByPlazaVea(nameProduct, brand);
        if (products.isEmpty()) {
            log.warn(GenConstant.LOGGER_APP_FAIL);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.info(GenConstant.LOGGER_APP_COMPLETED);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(SEARCH_PRODUCT_TOTTUS)
    public ResponseEntity<?> getProductsTottus(@RequestParam String nameProduct, @RequestParam String brand) {
        log.info(GenConstant.LOGGER_APP_INIT);
        var products = elasticSearchService.searchProductsByTottus(nameProduct, brand);
        if (products.isEmpty()) {
            log.warn(GenConstant.LOGGER_APP_FAIL);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.info(GenConstant.LOGGER_APP_COMPLETED);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(SEARCH_PRODUCT_METRO)
    public ResponseEntity<?> getProductsMetro(@RequestParam String nameProduct, @RequestParam String brand) {
        log.info(GenConstant.LOGGER_APP_INIT_NAME,"getProductsMetro");
        var products = elasticSearchService.searchProductsByMetro(nameProduct, brand);
        if (products.isEmpty()) {
            log.warn(GenConstant.LOGGER_APP_FAIL);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.info(GenConstant.LOGGER_APP_COMPLETED);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping(API_URI_SYNC)
    public ResponseEntity<?> getProductsByBrand() {
        log.info(GenConstant.LOGGER_APP_INIT);
        dataSyncService.syncMongoToElastic();
        log.info(GenConstant.LOGGER_APP_COMPLETED);
        return ResponseEntity.ok().body("Sync completed");
    }
}
