package com.nequi.franquicias.service;

import com.nequi.franquicias.models.Sucursal;
import com.nequi.franquicias.repositories.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalService {
    @Autowired
    private SucursalRepository sucursalRepository;

    public List<Sucursal> findAll() {
        return sucursalRepository.findAll();
    }

    public Sucursal findById(Long id) {
        return sucursalRepository.findById(id).orElse(null);
    }

    public Sucursal save(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }

    public void deleteById(Long id) {
        sucursalRepository.deleteById(id);
    }
}
