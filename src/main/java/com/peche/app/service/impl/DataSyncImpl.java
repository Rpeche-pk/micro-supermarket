package com.peche.app.service.impl;

import com.peche.app.exception.DataSyncException;
import com.peche.app.mapper.ProductMapper;
import com.peche.app.persistence.repository.MetroRepository;
import com.peche.app.persistence.repository.PlazaVeaRepository;
import com.peche.app.persistence.repository.TottusRepository;
import com.peche.app.persistence.repository.elastic.ElasticMetroRepository;
import com.peche.app.persistence.repository.elastic.ElasticTottusRepository;
import com.peche.app.persistence.repository.elastic.ElasticVeaRepository;
import com.peche.app.service.DataSyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DataSyncImpl implements DataSyncService {

    private final ElasticTottusRepository elasticTottusRepository;
    private final ElasticMetroRepository elasticMetroRepository;
    private final ElasticVeaRepository elasticVeaRepository;
    private final PlazaVeaRepository veaRepository;
    private final MetroRepository metroRepository;
    private final TottusRepository tottusRepository;
    private final ProductMapper productMapper;

    @Scheduled(cron = "0 0 23 */4 * ?")
    @Override
    public void syncMongoToElastic() {

        try {
            if (elasticVeaRepository.count() > 0) elasticVeaRepository.deleteAll();

            if (elasticMetroRepository.count() > 0) elasticMetroRepository.deleteAll();

            if (elasticTottusRepository.count() > 0) elasticTottusRepository.deleteAll();

            var productsVea = veaRepository.findAll();
            var productsMetro = metroRepository.findAll();
            var productsTottus = tottusRepository.findAll();

            if (!productsVea.isEmpty()) {
                var productVeaElastics = productsVea.stream()
                        .filter(Objects::nonNull)
                        .map(productMapper::mapToProductVeaElastic)
                        .toList();
                elasticVeaRepository.saveAll(productVeaElastics);
            }

            if (!productsMetro.isEmpty()) {
                var productMetroElastics = productsMetro.stream()
                        .filter(Objects::nonNull)
                        .map(productMapper::mapToProductMetroElastic)
                        .toList();
                elasticMetroRepository.saveAll(productMetroElastics);
            }

            if (!productsTottus.isEmpty()) {
                var productTottusElastics = productsTottus.stream()
                        .filter(Objects::nonNull)
                        .map(productMapper::mapToProductTottusElastic)
                        .toList();
                elasticTottusRepository.saveAll(productTottusElastics);
            }
        } catch (Exception e) {
            throw new DataSyncException("Error al sincronizar los datos", e);
        }

    }
}
