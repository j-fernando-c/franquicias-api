package com.nequi.franquicias.repositories;

import com.nequi.franquicias.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
