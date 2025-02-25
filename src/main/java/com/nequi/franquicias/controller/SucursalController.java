package com.nequi.franquicias.controller;

import com.nequi.franquicias.models.Sucursal;
import com.nequi.franquicias.service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sucursales")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    public List<Sucursal> findAll() {
        return sucursalService.findAll();
    }

    @GetMapping("/{id}")
    public Sucursal findById(@PathVariable Long id) {
        return sucursalService.findById(id);
    }

    @PostMapping
    public Sucursal save(@RequestBody Sucursal sucursal) {
        return sucursalService.save(sucursal);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        sucursalService.deleteById(id);
    }
}
