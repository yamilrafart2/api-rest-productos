package com.yamilrafart.apirest_productos.controller;

import com.yamilrafart.apirest_productos.dto.ProductoDTO;
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
        model.addAttribute("producto", new ProductoDTO());
        return "producto-form";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioDeEditar(@PathVariable Long id, Model model) {
        ProductoDTO productoDTO = iProducto.findById(id);
        model.addAttribute("producto", productoDTO);
        return "producto-form";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@Valid @ModelAttribute("producto") ProductoDTO productoDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "producto-form";
        }

        if (productoDTO.getId() != null) {
            iProducto.update(productoDTO);
            redirectAttributes.addFlashAttribute("msgExito", "Producto actualizado correctamente.");
        } else {
            iProducto.save(productoDTO);
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