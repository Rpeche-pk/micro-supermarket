package com.peche.app.persistence.entity.elastic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;

import static com.peche.app.constants.GenConstant.COLLECTION_ELASTIC_PLAZA_VEA;

@Document(indexName = COLLECTION_ELASTIC_PLAZA_VEA)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductVeaElastic {
    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String image;
    @Field(type = FieldType.Text, analyzer = "custom_analyzer")
    private String brand;
    @Field(type = FieldType.Text, analyzer = "custom_analyzer")
    private String name;
    @Field(type = FieldType.Keyword)
    private String price;
    @Field(type = FieldType.Keyword)
    private String supermarket;
    @Field(type = FieldType.Keyword)
    private String url;
    @Field(type = FieldType.Text, analyzer = "custom_analyzer")
    private String category;
    @Field(type = FieldType.Date)
    private LocalDate processDate;
}
