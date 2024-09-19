package com.peche.app.persistence.repository;

import com.peche.app.persistence.entity.ProductMetro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetroRepository extends ProductRepository<ProductMetro>,MongoRepository<ProductMetro, String> {
}
