package com.nequi.franquicias.repositories;

import com.nequi.franquicias.models.Franquicia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FranquiciaRepository extends JpaRepository<Franquicia, Long> {
}
