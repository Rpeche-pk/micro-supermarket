package com.peche.app.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MarketCategoryDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private String supermarket;
    private List<String> category;
}
