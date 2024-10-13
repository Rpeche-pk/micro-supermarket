package com.peche.app.persistence.repository.elastic;

import com.peche.app.persistence.entity.elastic.ProductVeaElastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElasticVeaRepository extends ElasticRepository<ProductVeaElastic>, ElasticsearchRepository<ProductVeaElastic, String> {
}
