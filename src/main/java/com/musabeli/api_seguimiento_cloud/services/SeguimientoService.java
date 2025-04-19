package com.musabeli.api_seguimiento_cloud.services;


import com.musabeli.api_seguimiento_cloud.dto.CreateSeguimientoDto;
import com.musabeli.api_seguimiento_cloud.dto.UpdateSeguimientoDto;
import com.musabeli.api_seguimiento_cloud.entities.Seguimiento;

import java.util.List;

public interface SeguimientoService {

    Seguimiento createSeguimiento(CreateSeguimientoDto seguimientoDto);
    List<Seguimiento> getAllSeguimientos();
    Seguimiento getSeguimientoById(Long id);
    Seguimiento updateSeguimiento(Long id, UpdateSeguimientoDto updateSeguimientoDto);
}
