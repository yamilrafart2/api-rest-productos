package com.yamilrafart.apirest_productos.controller;

import com.yamilrafart.apirest_productos.entity.Producto;
import com.yamilrafart.apirest_productos.service.IProducto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/web/productos")
public class ProductoWebController {

    private final IProducto iProducto;

    public ProductoWebController(IProducto iProducto) {
        this.iProducto = iProducto;
    }

    @GetMapping
    public String listarProductos(Model model) {
        model.addAttribute("productos", iProducto.findAll());
        return "productos";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioDeCreacion(Model model) {
        model.addAttribute("producto", new Producto());
        return "producto-form";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable Long id, Model model) {
        Producto producto = iProducto.findById(id);
        model.addAttribute("producto", producto);
        return "producto-form";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@Valid @ModelAttribute("producto") Producto producto, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "producto-form";
        }

        if (producto.getId() != null) {
            iProducto.update(producto);
            redirectAttributes.addFlashAttribute("msgExito", "Producto actualizado correctamente.");
        } else {
            iProducto.save(producto);
            redirectAttributes.addFlashAttribute("msgExito", "Producto creado exitosamente.");
        }
        return "redirect:/web/productos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        iProducto.deleteById(id);
        redirectAttributes.addFlashAttribute("msgExito", "Producto eliminado correctamente.");
        return "redirect:/web/productos";
    }
}