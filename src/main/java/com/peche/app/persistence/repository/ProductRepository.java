package com.peche.app.persistence.repository;

import com.peche.app.persistence.entity.MarketCategory;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository<T,K> {
    @Query("{ 'categoria': { $regex: ?0, $options: 'i' } }")
    List<T> findByCategoriaIgnoreCase(String category);

    @Aggregation(pipeline = {
            "{ $group: { _id: { supermercado: '$supermercado', categoria: '$categoria' } } }",
            "{ $group: { _id: '$_id.supermercado', categoria: { $addToSet: '$_id.categoria' } } }",
            "{ $project: { supermercado: '$_id', categoria: 1, _id: 0 } }"
    })
    List<K> findCategoryBySupermarket();
}
