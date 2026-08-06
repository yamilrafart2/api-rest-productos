package com.yamilrafart.apirest_productos.repository;

import com.yamilrafart.apirest_productos.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
