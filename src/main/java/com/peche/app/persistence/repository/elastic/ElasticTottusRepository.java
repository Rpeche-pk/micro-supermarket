package com.peche.app.persistence.repository.elastic;

import com.peche.app.persistence.entity.elastic.ProductTottusElastic;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElasticTottusRepository extends ElasticRepository<ProductTottusElastic>, ElasticsearchRepository<ProductTottusElastic, String> {
}
