package com.yamilrafart.apirest_productos.controller;

import com.yamilrafart.apirest_productos.dto.ProductoDTO;
import com.yamilrafart.apirest_productos.service.IProducto;
import com.yamilrafart.apirest_productos.service.ProductoExportService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/web/productos")
public class ProductoWebController {

    private final IProducto iProducto;

    @Autowired
    private ProductoExportService productoExportService;

    public ProductoWebController(IProducto iProducto) {
        this.iProducto = iProducto;
    }

    @GetMapping
    public String listarProductos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(value = "keyword", required = false) String keyword,
            Model model) {

        Pageable pageable = PageRequest.of(page, size);
        Page<ProductoDTO> paginaProductos = iProducto.findAllPaginated(keyword, pageable);

        model.addAttribute("productos", paginaProductos.getContent()); // Solo la lista de productos
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", paginaProductos.getTotalPages());
        model.addAttribute("totalItems", paginaProductos.getTotalElements());
        model.addAttribute("keyword", keyword); // Para mantener la búsqueda en el input

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

    @GetMapping("/exportar/excel")
    public ResponseEntity<InputStreamResource> exportarExcel() throws IOException {
        List<ProductoDTO> productos = iProducto.findAll();
        ByteArrayInputStream in = productoExportService.exportarExcel(productos);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=productos.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(new InputStreamResource(in));
    }
}