package com.peche.app.mapper;

import com.peche.app.dto.response.ProductDto;
import com.peche.app.persistence.entity.Product;
import com.peche.app.persistence.entity.ProductMetro;
import com.peche.app.persistence.entity.ProductTottus;
import com.peche.app.persistence.entity.ProductVea;
import com.peche.app.persistence.entity.elastic.ProductMetroElastic;
import com.peche.app.persistence.entity.elastic.ProductTottusElastic;
import com.peche.app.persistence.entity.elastic.ProductVeaElastic;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        builder = @Builder(disableBuilder = true))
public interface ProductMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "nombre")
    @Mapping(target = "brand", source = "marca")
    @Mapping(target = "category", source = "categoria")
    @Mapping(target = "price", source = "precio")
    @Mapping(target = "supermarket", source = "supermercado")
    @Mapping(target = "image", source = "imagen")
    @Mapping(target = "url", source = "url")
    @Mapping(target = "processDate", source = "fecha_proceso")
    ProductDto mapToProduct(Product product);


    List<ProductDto> mapToProductDtoList(List<Product> products);

    ProductVeaElastic mapToProductVeaElastic(ProductVea productDto);
    ProductMetroElastic mapToProductMetroElastic(ProductMetro productDto);
    ProductTottusElastic mapToProductTottusElastic(ProductTottus productDto);

}
