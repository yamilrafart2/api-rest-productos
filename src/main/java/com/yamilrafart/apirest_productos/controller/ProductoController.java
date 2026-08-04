package com.yamilrafart.apirest_productos.controller;

import com.yamilrafart.apirest_productos.entity.Producto;
import com.yamilrafart.apirest_productos.service.IProducto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductoController {

    private IProducto iProducto;

    public ProductoController(IProducto iProducto) {
        this.iProducto = iProducto;
    }

    /**
     * POST
     * http://localhost:8080
     * @param producto
     * @return
     */
    @PostMapping
    public Producto save(@RequestBody Producto producto) {
        return iProducto.save(producto);
    }

    /**
     * GET
     * http://localhost:8080
     * @return
     */
    @GetMapping
    public List<Producto> findAll(){
        return iProducto.findAll();
    }

    /**
     * GET
     * http://localhost:8080/1
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Producto findById(@PathVariable Integer id){
        return iProducto.findById(id);
    }

    /**
     * DELETE
     * http://localhost:8080/2
     * @param id
     */
    @DeleteMapping("/{idProducto}")
    public void deleteById(@PathVariable("idProducto") Integer id){
        iProducto.deleteById(id);
    }

    /**
     * PUT
     * http://localhost:8080
     * @param producto
     * @return
     */
    @PutMapping
    public Producto update(@RequestBody Producto producto){
        return iProducto.update(producto);
    }

}
