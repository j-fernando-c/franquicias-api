package com.nequi.franquicias.service;

import com.nequi.franquicias.models.Franquicia;
import com.nequi.franquicias.models.Producto;
import com.nequi.franquicias.models.Sucursal;
import com.nequi.franquicias.repositories.FranquiciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class FranquiciaService {

    @Autowired
    private FranquiciaRepository franquiciaRepository;

    public List<Franquicia> findAll() {
        return franquiciaRepository.findAll();
    }

    public Franquicia findById(Long id) {
        return franquiciaRepository.findById(id).orElse(null);
    }

    public Franquicia save(Franquicia franquicia) {
        return franquiciaRepository.save(franquicia);
    }

    public void deleteById(Long id) {
        franquiciaRepository.deleteById(id);
    }

    public Franquicia addSucursal(Long franquiciaId, Sucursal sucursal) {
        Franquicia franquicia = findById(franquiciaId);
        if (franquicia != null) {
            franquicia.getSucursales().add(sucursal);
            return franquiciaRepository.save(franquicia);
        }
        return null;
    }

    public Sucursal addProducto(Long franquiciaId, Long sucursalId, Producto producto) {
        Franquicia franquicia = findById(franquiciaId);
        if (franquicia != null) {
            Optional<Sucursal> sucursalOpt = franquicia.getSucursales().stream()
                    .filter(s -> s.getId().equals(sucursalId))
                    .findFirst();
            if (sucursalOpt.isPresent()) {
                Sucursal sucursal = sucursalOpt.get();
                sucursal.getProductos().add(producto);
                franquiciaRepository.save(franquicia);
                return sucursal;
            }
        }
        return null;
    }

    public void deleteProducto(Long franquiciaId, Long sucursalId, Long productoId) {
        Franquicia franquicia = findById(franquiciaId);
        if (franquicia != null) {
            Optional<Sucursal> sucursalOpt = franquicia.getSucursales().stream()
                    .filter(s -> s.getId().equals(sucursalId))
                    .findFirst();
            if (sucursalOpt.isPresent()) {
                Sucursal sucursal = sucursalOpt.get();
                sucursal.getProductos().removeIf(p -> p.getId().equals(productoId));
                franquiciaRepository.save(franquicia);
            }
        }
    }

    public Producto updateStock(Long franquiciaId, Long sucursalId, Long productoId, int stock) {
        Franquicia franquicia = findById(franquiciaId);
        if (franquicia != null) {
            Optional<Sucursal> sucursalOpt = franquicia.getSucursales().stream()
                    .filter(s -> s.getId().equals(sucursalId))
                    .findFirst();
            if (sucursalOpt.isPresent()) {
                Sucursal sucursal = sucursalOpt.get();
                Optional<Producto> productoOpt = sucursal.getProductos().stream()
                        .filter(p -> p.getId().equals(productoId))
                        .findFirst();
                if (productoOpt.isPresent()) {
                    Producto producto = productoOpt.get();
                    producto.setStock(stock);
                    franquiciaRepository.save(franquicia);
                    return producto;
                }
            }
        }
        return null;
    }

    public List<Producto> getProductosConMasStock(Long franquiciaId) {
        Franquicia franquicia = findById(franquiciaId);
        if (franquicia != null) {
            return franquicia.getSucursales().stream()
                    .flatMap(sucursal -> sucursal.getProductos().stream())
                    .collect(Collectors.groupingBy(Producto::getNombre))
                    .values().stream()
                    .map(productos -> productos.stream().max((p1, p2) -> Integer.compare(p1.getStock(), p2.getStock())).orElse(null))
                    .collect(Collectors.toList());
        }
        return null;
    }
}