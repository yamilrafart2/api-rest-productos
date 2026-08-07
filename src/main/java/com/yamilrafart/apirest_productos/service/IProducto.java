package com.yamilrafart.apirest_productos.service;

import com.yamilrafart.apirest_productos.dto.ProductoDTO;

import java.util.List;

public interface IProducto {

    ProductoDTO save(ProductoDTO productoDTO);

    List<ProductoDTO> findAll();

    ProductoDTO findById(Long id);

    void deleteById(Long id);

    ProductoDTO update(ProductoDTO productoDTO);

}