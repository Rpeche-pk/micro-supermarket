package com.peche.app.persistence.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import static com.peche.app.constants.GenConstant.COLLECTION_METRO;

@Document(collection = COLLECTION_METRO)
public class ProductMetro extends Product{
}
