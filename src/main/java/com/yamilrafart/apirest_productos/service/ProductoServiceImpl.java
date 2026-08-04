package com.yamilrafart.apirest_productos.service;

import com.yamilrafart.apirest_productos.entity.Producto;
import com.yamilrafart.apirest_productos.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements IProducto{

    private ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public Producto findById(Integer id) {
        return productoRepository.findById(id).get();
    }

}
