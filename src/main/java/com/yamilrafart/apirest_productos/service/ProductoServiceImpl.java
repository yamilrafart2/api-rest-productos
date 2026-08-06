package com.yamilrafart.apirest_productos.service;

import com.yamilrafart.apirest_productos.entity.Producto;
import com.yamilrafart.apirest_productos.exception.ResourceNotFoundException;
import com.yamilrafart.apirest_productos.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductoServiceImpl implements IProducto {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    @Transactional
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    @Transactional(readOnly = true) // readOnly = true optimiza las consultas de solo lectura
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true) // readOnly = true optimiza las consultas de solo lectura
    public Producto findById(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con el ID: " + id));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar. Producto no encontrado con el ID: " + id);
        }
        productoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Producto update(Producto producto) {
        //findById para buscar y lanzar la excepción si no existe
        Producto productoBD = this.findById(producto.getId());

        productoBD.setNombre(producto.getNombre());
        productoBD.setDescripcion(producto.getDescripcion());
        productoBD.setPrecio(producto.getPrecio());

        return productoRepository.save(productoBD);
    }
}