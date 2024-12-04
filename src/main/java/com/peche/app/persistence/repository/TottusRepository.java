package com.peche.app.persistence.repository;

import com.peche.app.persistence.entity.MarketCategory;
import com.peche.app.persistence.entity.ProductTottus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TottusRepository extends ProductRepository<ProductTottus, MarketCategory>, MongoRepository<ProductTottus, String> {
}
