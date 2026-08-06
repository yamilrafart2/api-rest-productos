package com.yamilrafart.apirest_productos.controller;

import com.yamilrafart.apirest_productos.entity.Producto;
import com.yamilrafart.apirest_productos.service.IProducto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute("producto") Producto producto) {
        // Recibe el objeto lleno desde el formulario web y lo guarda usando el servicio
        iProducto.save(producto);

        // "redirect:" le dice a Spring que recargue la página de la lista para ver el nuevo registro
        return "redirect:/web/productos";
    }
}