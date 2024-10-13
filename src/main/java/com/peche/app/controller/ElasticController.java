package com.peche.app.controller;

import com.peche.app.constants.GenConstant;
import com.peche.app.service.DataSyncService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.peche.app.constants.GenConstant.API_ELASTIC_SEARCH;
import static com.peche.app.constants.GenConstant.API_URI_SYNC;

@RequestMapping(API_ELASTIC_SEARCH)
@RestController
@RequiredArgsConstructor
@Slf4j
public class ElasticController {

    private final DataSyncService dataSyncService;
//    @GetMapping(PRODUCT_BY_QUERY)
//    public ResponseEntity<?> getProductsByBrand(@RequestParam String nameProduct, @RequestParam String brand) {
//        log.info(GenConstant.LOGGER_APP_INIT);
//        var products = productService.searchProductsByName(nameProduct, brand);
//        if (products.isEmpty()) {
//            log.error(GenConstant.LOGGER_APP_FAIL);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        log.info(GenConstant.LOGGER_APP_COMPLETED);
//        return new ResponseEntity<>(products, HttpStatus.OK);
//    }
    @GetMapping(API_URI_SYNC)
    public ResponseEntity<?> getProductsByBrand() {
        log.info(GenConstant.LOGGER_APP_INIT);
        dataSyncService.syncMongoToElastic();
        log.info(GenConstant.LOGGER_APP_COMPLETED);
        return ResponseEntity.ok().body("Sync completed");
    }
}
