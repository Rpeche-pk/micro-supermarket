package com.peche.app.persistence.repository;

import com.peche.app.persistence.entity.ProductVea;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlazaVeaRepository extends ProductRepository<ProductVea>, MongoRepository<ProductVea, String> {
}
