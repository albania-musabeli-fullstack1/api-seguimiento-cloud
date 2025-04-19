package com.musabeli.api_seguimiento_cloud.repositories;

import com.musabeli.api_seguimiento_cloud.entities.Seguimiento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeguimientoRepository extends JpaRepository<Seguimiento, Long> {

    Optional<Seguimiento> findByCodSeguimiento(String codSeguimiento);

}
