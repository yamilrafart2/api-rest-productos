package com.yamilrafart.apirest_productos.service;

import com.yamilrafart.apirest_productos.entity.Producto;
import com.yamilrafart.apirest_productos.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements IProducto {

    private final ProductoRepository productoRepository;

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
    public Producto findById(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }


    @Override
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }


    @Override
    public Producto update(Producto producto) {

        Producto productoBD = productoRepository.findById(producto.getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        productoBD.setNombre(producto.getNombre());
        productoBD.setDescripcion(producto.getDescripcion());
        productoBD.setPrecio(producto.getPrecio());

        return productoRepository.save(productoBD);
    }
}
