package com.peche.app.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private String id;
    private String image;
    private String brand;
    private String name;
    private String price;
    private String supermarket;
    private String url;
    private String category;
}
