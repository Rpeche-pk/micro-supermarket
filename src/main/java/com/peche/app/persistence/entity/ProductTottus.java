package com.peche.app.persistence.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import static com.peche.app.constants.GenConstant.COLLECTION_TOTTUS;

@Document(collection = COLLECTION_TOTTUS)
public class ProductTottus extends Product{
}
