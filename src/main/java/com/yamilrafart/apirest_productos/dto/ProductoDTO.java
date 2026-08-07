package com.yamilrafart.apirest_productos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ProductoDTO {

    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String descripcion;

    @Positive(message = "El precio debe ser mayor a cero")
    @NotNull(message = "El precio es obligatorio")
    private BigDecimal precio;

    private LocalDateTime fechaCreacion;
}