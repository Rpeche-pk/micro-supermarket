package com.peche.app.persistence.repository.elastic;

import com.peche.app.persistence.entity.elastic.ProductMetroElastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElasticMetroRepository extends ElasticRepository<ProductMetroElastic>, ElasticsearchRepository<ProductMetroElastic, String> {
}
