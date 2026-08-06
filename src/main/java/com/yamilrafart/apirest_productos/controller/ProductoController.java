package com.yamilrafart.apirest_productos.controller;

import com.yamilrafart.apirest_productos.entity.Producto;
import com.yamilrafart.apirest_productos.service.IProducto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final IProducto iProducto;

    public ProductoController(IProducto iProducto) {
        this.iProducto = iProducto;
    }


    @PostMapping
    public ResponseEntity<Producto> save(@Valid @RequestBody Producto producto) {
        Producto productoGuardado = iProducto.save(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoGuardado);
    }


    @GetMapping
    public ResponseEntity<List<Producto>> findAll(){

        return ResponseEntity.ok(iProducto.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Producto> findById(
            @PathVariable Long id){

        return ResponseEntity.ok(iProducto.findById(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(
            @PathVariable Long id){

        iProducto.deleteById(id);

        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Producto> update(
            @PathVariable Long id,
            @Valid @RequestBody Producto producto){
        producto.setId(id);
        return ResponseEntity.ok(iProducto.update(producto));
    }
}