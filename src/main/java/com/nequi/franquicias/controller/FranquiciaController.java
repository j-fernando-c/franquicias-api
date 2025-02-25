package com.nequi.franquicias.controller;

import com.nequi.franquicias.models.Franquicia;
import com.nequi.franquicias.models.Producto;
import com.nequi.franquicias.models.Sucursal;
import com.nequi.franquicias.service.FranquiciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/franquicias")
public class FranquiciaController {

    @Autowired
    private FranquiciaService franquiciaService;

    @GetMapping
    public List<Franquicia> findAll() {
        return franquiciaService.findAll();
    }

    @GetMapping("/{id}")
    public Franquicia findById(@PathVariable Long id) {
        return franquiciaService.findById(id);
    }

    @PostMapping
    public Franquicia save(@RequestBody Franquicia franquicia) {
        return franquiciaService.save(franquicia);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        franquiciaService.deleteById(id);
    }

    @PostMapping("/{id}/sucursales")
    public Franquicia addSucursal(@PathVariable Long id, @RequestBody Sucursal sucursal) {
        return franquiciaService.addSucursal(id, sucursal);
    }

    @PostMapping("/{franquiciaId}/sucursales/{sucursalId}/productos")
    public Sucursal addProducto(@PathVariable Long franquiciaId, @PathVariable Long sucursalId, @RequestBody Producto producto) {
        return franquiciaService.addProducto(franquiciaId, sucursalId, producto);
    }

    @DeleteMapping("/{franquiciaId}/sucursales/{sucursalId}/productos/{productoId}")
    public void deleteProducto(@PathVariable Long franquiciaId, @PathVariable Long sucursalId, @PathVariable Long productoId) {
        franquiciaService.deleteProducto(franquiciaId, sucursalId, productoId);
    }

    @PutMapping("/{franquiciaId}/sucursales/{sucursalId}/productos/{productoId}/stock")
    public Producto updateStock(@PathVariable Long franquiciaId, @PathVariable Long sucursalId, @PathVariable Long productoId, @RequestParam int stock) {
        return franquiciaService.updateStock(franquiciaId, sucursalId, productoId, stock);
    }

    @GetMapping("/{id}/productos-con-mas-stock")
    public List<Producto> getProductosConMasStock(@PathVariable Long id) {
        return franquiciaService.getProductosConMasStock(id);
    }
}
