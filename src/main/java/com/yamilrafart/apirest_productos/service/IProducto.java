package com.yamilrafart.apirest_productos.service;

import com.yamilrafart.apirest_productos.dto.ProductoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProducto {

    ProductoDTO save(ProductoDTO productoDTO);

    Page<ProductoDTO> findAllPaginated(String keyword, Pageable pageable);

    ProductoDTO findById(Long id);

    void deleteById(Long id);

    ProductoDTO update(ProductoDTO productoDTO);

}