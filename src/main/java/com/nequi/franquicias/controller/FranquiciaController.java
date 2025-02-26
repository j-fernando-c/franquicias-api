package com.nequi.franquicias.controller;

import com.nequi.franquicias.models.Franquicia;
import com.nequi.franquicias.models.Producto;
import com.nequi.franquicias.models.Sucursal;
import com.nequi.franquicias.service.FranquiciaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/franquicias")
public class FranquiciaController {

    @Autowired
    private FranquiciaService franquiciaService;

    @GetMapping
    public ResponseEntity<List<Franquicia>> findAll() {
        List<Franquicia> franquicias = franquiciaService.findAll();
        return new ResponseEntity<>(franquicias, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Franquicia> findById(@PathVariable @Min(1) Long id) {
        Franquicia franquicia = franquiciaService.findById(id);
        if (franquicia != null) {
            return new ResponseEntity<>(franquicia, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Franquicia> save(@RequestBody @Valid Franquicia franquicia) {
        Franquicia savedFranquicia = franquiciaService.save(franquicia);
        return new ResponseEntity<>(savedFranquicia, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable @Min(1) Long id) {
        franquiciaService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/sucursales")
    public ResponseEntity<Franquicia> addSucursal(@PathVariable @Min(1) Long id, @RequestBody @Valid Sucursal sucursal) {
        Franquicia updatedFranquicia = franquiciaService.addSucursal(id, sucursal);
        if (updatedFranquicia != null) {
            return new ResponseEntity<>(updatedFranquicia, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{franquiciaId}/sucursales/{sucursalId}/productos")
    public ResponseEntity<Sucursal> addProducto(@PathVariable @Min(1) Long franquiciaId, @PathVariable @Min(1) Long sucursalId, @RequestBody @Valid Producto producto) {
        Sucursal updatedSucursal = franquiciaService.addProducto(franquiciaId, sucursalId, producto);
        if (updatedSucursal != null) {
            return new ResponseEntity<>(updatedSucursal, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{franquiciaId}/sucursales/{sucursalId}/productos/{productoId}")
    public ResponseEntity<Void> deleteProducto(@PathVariable @Min(1) Long franquiciaId, @PathVariable @Min(1) Long sucursalId, @PathVariable @Min(1) Long productoId) {
        franquiciaService.deleteProducto(franquiciaId, sucursalId, productoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{franquiciaId}/sucursales/{sucursalId}/productos/{productoId}/stock")
    public ResponseEntity<Producto> updateStock(@PathVariable @Min(1) Long franquiciaId, @PathVariable @Min(1) Long sucursalId, @PathVariable @Min(1) Long productoId, @RequestParam @Min(0) int stock) {
        Producto updatedProducto = franquiciaService.updateStock(franquiciaId, sucursalId, productoId, stock);
        if (updatedProducto != null) {
            return new ResponseEntity<>(updatedProducto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/productos-con-mas-stock")
    public ResponseEntity<List<Producto>> getProductosConMasStock(@PathVariable @Min(1) Long id) {
        List<Producto> productos = franquiciaService.getProductosConMasStock(id);
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }
}
