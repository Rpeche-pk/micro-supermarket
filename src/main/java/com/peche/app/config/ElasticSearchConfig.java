package com.peche.app.config;

import com.peche.app.persistence.entity.elastic.ProductMetroElastic;
import com.peche.app.persistence.entity.elastic.ProductTottusElastic;
import com.peche.app.persistence.entity.elastic.ProductVeaElastic;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;

import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class ElasticSearchConfig {

    private final ElasticsearchOperations elasticsearchOperations;

    @PostConstruct
    public void createIndices() {
        createIndex(ProductMetroElastic.class);
        createIndex(ProductTottusElastic.class);
        createIndex(ProductVeaElastic.class);
    }

    private void createIndex(Class<?> entityClass) {
        IndexOperations indexOperations = elasticsearchOperations.indexOps(entityClass);

        if (!indexOperations.exists()) {
            indexOperations.create(this.getIndexSettings());
            indexOperations.putMapping(indexOperations.createMapping());
        }
    }

    private Map<String, Object> getIndexSettings() {
        /*return Map.of(
                "analysis", Map.of(
                        "analyzer", Map.of(
                                "custom_analyzer", Map.of(
                                        "type", "custom",
                                        "tokenizer", "standard",
                                        "filter", List.of("lowercase", "asciifolding", "spanish_stop", "spanish_stemmer")
                                )
                        ),
                        "filter", Map.of(
                                "spanish_stop", Map.of(
                                        "type", "stop",
                                        "stopwords", "_spanish_"
                                ),
                                "spanish_stemmer", Map.of(
                                        "type", "stemmer",
                                        "language", "light_spanish"
                                )
                        )
                )
        );*/
        return Map.of(
                "analysis", Map.of(
                        "tokenizer", Map.of(
                                "edge_ngram_tokenizer", Map.of(
                                        "type", "edge_ngram",
                                        "min_gram", 1,
                                        "max_gram", 25,
                                        "token_chars", List.of("letter", "digit")
                                )
                        ),
                        "analyzer", Map.of(
                                "custom_analyzer", Map.of(
                                        "type", "custom",
                                        "tokenizer", "edge_ngram_tokenizer",
                                        "filter", List.of("lowercase", "asciifolding", "spanish_stop", "spanish_stemmer")
                                )
                        ),
                        "filter", Map.of(
                                "spanish_stop", Map.of(
                                        "type", "stop",
                                        "stopwords", "_spanish_"
                                ),
                                "spanish_stemmer", Map.of(
                                        "type", "stemmer",
                                        "language", "light_spanish"
                                )
                        )
                )
        );
    }
}
