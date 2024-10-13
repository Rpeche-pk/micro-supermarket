package com.peche.app.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private String id;
    private String imagen;
    private String marca;
    private String nombre;
    private String precio;
    private String supermercado;
    private String url;
    private String categoria;
    private LocalDate fecha_proceso;
}
