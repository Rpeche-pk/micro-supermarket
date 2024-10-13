package com.peche.app.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate processDate;
}
