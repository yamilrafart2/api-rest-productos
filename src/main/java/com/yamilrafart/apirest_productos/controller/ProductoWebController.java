package com.yamilrafart.apirest_productos.controller;

import com.yamilrafart.apirest_productos.entity.Producto;
import com.yamilrafart.apirest_productos.service.IProducto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/web/productos")
public class ProductoWebController {

    private final IProducto iProducto;

    public ProductoWebController(IProducto iProducto) {
        this.iProducto = iProducto;
    }

    @GetMapping
    public String listarProductos(Model model) {
        // model.addAttribute inyecta datos desde el backend hacia el HTML
        model.addAttribute("productos", iProducto.findAll());

        // Retorna el nombre del archivo HTML sin la extensión (productos.html)
        return "productos";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioDeCreacion(Model model) {
        // Envía un objeto Producto vacío a la vista.
        // Thymeleaf usa este objeto para "atar" los datos del formulario a sus atributos.
        model.addAttribute("producto", new Producto());
        return "producto-form"; // Retorna la vista producto-form.html
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable Long id, Model model) {
        // Busca el producto en la BD. Si existe, lo envía al formulario
        // para que los campos aparezcan pre-llenados.
        Producto producto = iProducto.findById(id);
        model.addAttribute("producto", producto);
        return "producto-form";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@Valid @ModelAttribute("producto") Producto producto, BindingResult result) {
        // Si no cumple las reglas de la entidad (ej. precio negativo), vuelve al formulario
        if (result.hasErrors()) {
            return "producto-form";
        }

        if (producto.getId() != null) {
            iProducto.update(producto);
        } else {
            iProducto.save(producto);
        }
        return "redirect:/web/productos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        // Elimina por ID y recarga la lista
        iProducto.deleteById(id);
        return "redirect:/web/productos";
    }
}