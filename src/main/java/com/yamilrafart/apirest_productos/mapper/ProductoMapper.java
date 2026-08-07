package com.yamilrafart.apirest_productos.mapper;

import com.yamilrafart.apirest_productos.dto.ProductoDTO;
import com.yamilrafart.apirest_productos.entity.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    // Convierte de Entidad a DTO (Para enviar a la vista/cliente)
    public ProductoDTO toDTO(Producto producto) {
        if (producto == null) {
            return null;
        }

        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setFechaCreacion(producto.getFechaCreacion());
        return dto;
    }

    // Convierte de DTO a Entidad (Para guardar en la base de datos)
    public Producto toEntity(ProductoDTO dto) {
        if (dto == null) {
            return null;
        }

        Producto producto = new Producto();
        producto.setId(dto.getId());
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        // La fecha de creación y actualización las maneja JPA automáticamente con @CreationTimestamp
        return producto;
    }
}