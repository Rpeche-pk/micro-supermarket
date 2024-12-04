package com.peche.app.persistence.repository.elastic;

import org.springframework.data.elasticsearch.annotations.Query;

import java.util.List;


public interface ElasticRepository <T> {

    //OPCION 1 MENOS EFICIENTE
//    @Query("{\"bool\": {\"should\": [" +
//            "{\"match\": {\"name\": {\"query\": \"?0\", \"operator\": \"and\",\"analyzer\": \"custom_analyzer\"}}}," +
//            "{\"match\": {\"brand\": {\"query\": \"?1\", \"operator\": \"and\", \"analyzer\": \"custom_analyzer\"}}}" +
//            "]}}")

    //OPCION 2 MAS EFICIENTE
    @Query("""
            {
              "bool": {
                "must": [
                  {
                    "multi_match": {
                      "query": "?0",
                      "fields": ["name^2", "category^1.5"],
                      "type": "best_fields",
                      "operator": "and",
                      "fuzziness": "AUTO",
                      "analyzer": "custom_analyzer"
                    }
                  },
                  {
                    "match": {
                      "brand": {
                        "query": "?1",
                        "operator": "and",
                        "analyzer": "custom_analyzer",
                        "boost": 2.0,
                        "fuzziness": "AUTO"
                      }
                    }
                  }
                ]
              }
            }""")

    //OPCION 3 MAS EFICIENTE
//    @Query("{ \"bool\": { \"must\": [ " +
//            "  { \"bool\": { \"should\": [ " +
//            "    { \"match\": { \"name\": { \"query\": \"?0\", \"operator\": \"and\", \"analyzer\": \"custom_analyzer\", \"boost\": 2.0 } } }, " +
//            "    { \"match_phrase\": { \"name\": { \"query\": \"?0\", \"analyzer\": \"custom_analyzer\", \"boost\": 3.0 } } } " +
//            "  ] } }, " +
//            "  { \"bool\": { \"should\": [ " +
//            "    { \"match\": { \"brand\": { \"query\": \"?1\", \"operator\": \"and\", \"analyzer\": \"custom_analyzer\" } } }, " +
//            "    { \"match_phrase\": { \"brand\": { \"query\": \"?1\", \"analyzer\": \"custom_analyzer\", \"boost\": 1.5 } } } " +
//            "  ] } } " +
//            "] } }")
    List<T> findByCustomQuery(String nameProduct, String brand);


}
