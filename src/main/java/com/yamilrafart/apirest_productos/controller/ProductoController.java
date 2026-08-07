package com.yamilrafart.apirest_productos.controller;

import com.yamilrafart.apirest_productos.dto.ProductoDTO;
import com.yamilrafart.apirest_productos.service.IProducto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final IProducto iProducto;

    public ProductoController(IProducto iProducto) {
        this.iProducto = iProducto;
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> save(@Valid @RequestBody ProductoDTO productoDTO) {
        ProductoDTO productoGuardado = iProducto.save(productoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoGuardado);
    }

    @GetMapping
    public ResponseEntity<Page<ProductoDTO>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(value = "keyword", required = false) String keyword) {

        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(iProducto.findAllPaginated(keyword, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(iProducto.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        iProducto.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductoDTO productoDTO){
        productoDTO.setId(id);
        return ResponseEntity.ok(iProducto.update(productoDTO));
    }
}