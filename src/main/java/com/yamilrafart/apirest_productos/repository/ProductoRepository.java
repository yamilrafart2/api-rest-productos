package com.yamilrafart.apirest_productos.repository;

import com.yamilrafart.apirest_productos.entity.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // Busca productos por nombre (ignorando mayúsculas) y paginar el resultado
    Page<Producto> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}