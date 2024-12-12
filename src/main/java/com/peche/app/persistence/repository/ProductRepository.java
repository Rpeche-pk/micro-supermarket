package com.peche.app.persistence.repository;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository<T, K> {
    @Query("{ 'categoria': { $regex: ?0, $options: 'i' } }")
    List<T> findByCategoriaIgnoreCase(String category);

    @Aggregation(pipeline = {
            "{ $group: { _id: { supermercado: '$supermercado', categoria: '$categoria' } } }",
            "{ $group: { _id: '$_id.supermercado', categoria: { $addToSet: '$_id.categoria' } } }",
            "{ $project: { supermercado: '$_id', categoria: 1, _id: 0 } }"
    })
    List<K> findCategoryBySupermarket();


    @Aggregation(pipeline = {
            "{ $match: { $expr: { $eq: [ { $toLower: '$categoria' }, { $toLower: ?0 } ] } } }",
            "{ $group: { _id: '$marca', count: { $sum: 1 } } }",
            "{ $project: { marca: '$_id', count: 1, _id: 0} }",
            "{ $sort: { count: -1 } }",
            "{ $group: { _id: null, uniqueBrands: { $addToSet: '$$ROOT' }} }",
            "{ $unwind: '$uniqueBrands' }",
            "{ $replaceRoot: { newRoot: '$uniqueBrands' } }"
    })
    List<T> findBrandByCategory(String category);
}
