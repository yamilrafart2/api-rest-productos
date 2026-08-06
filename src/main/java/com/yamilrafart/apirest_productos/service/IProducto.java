package com.yamilrafart.apirest_productos.service;

import com.yamilrafart.apirest_productos.entity.Producto;

import java.util.List;

public interface IProducto {

    Producto save(Producto producto);

    List<Producto> findAll();

    Producto findById(Long id);

    void deleteById(Long id);

    Producto update(Producto producto);

}
