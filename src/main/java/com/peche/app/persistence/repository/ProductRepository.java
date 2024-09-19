package com.peche.app.persistence.repository;

import com.peche.app.persistence.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository<T> {
    List<T> findByMarcaIsLikeIgnoreCase(String brand);

    @Query("{ 'categoria': { $regex: ?0, $options: 'i' } }")
    List<T> findByCategoriaIgnoreCase(String category);
    @Query("{ 'supermercado': { $regex: ?0, $options: 'i' } }")
    List<T> findBySupermercadoIgnoreCase(String supermarket);
}
