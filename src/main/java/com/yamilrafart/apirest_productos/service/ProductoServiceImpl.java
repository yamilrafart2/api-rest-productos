package com.yamilrafart.apirest_productos.service;

import com.yamilrafart.apirest_productos.dto.ProductoDTO;
import com.yamilrafart.apirest_productos.entity.Producto;
import com.yamilrafart.apirest_productos.exception.ResourceNotFoundException;
import com.yamilrafart.apirest_productos.mapper.ProductoMapper;
import com.yamilrafart.apirest_productos.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements IProducto {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    public ProductoServiceImpl(ProductoRepository productoRepository, ProductoMapper productoMapper) {
        this.productoRepository = productoRepository;
        this.productoMapper = productoMapper;
    }

    @Override
    @Transactional
    public ProductoDTO save(ProductoDTO productoDTO) {
        Producto producto = productoMapper.toEntity(productoDTO);
        Producto productoGuardado = productoRepository.save(producto);
        return productoMapper.toDTO(productoGuardado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDTO> findAll() {
        return productoRepository.findAll()
                .stream()
                .map(productoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoDTO findById(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con el ID: " + id));
        return productoMapper.toDTO(producto);
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
    public ProductoDTO update(ProductoDTO productoDTO) {
        Producto productoBD = productoRepository.findById(productoDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con el ID: " + productoDTO.getId()));

        productoBD.setNombre(productoDTO.getNombre());
        productoBD.setDescripcion(productoDTO.getDescripcion());
        productoBD.setPrecio(productoDTO.getPrecio());

        Producto productoActualizado = productoRepository.save(productoBD);
        return productoMapper.toDTO(productoActualizado);
    }
}