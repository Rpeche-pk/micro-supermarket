package com.peche.app.persistence.entity;

import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import static com.peche.app.constants.GenConstant.COLLECTION_PLAZA_VEA;

@Document(collection = COLLECTION_PLAZA_VEA)
@ToString
public class ProductVea extends Product{
}
