package com.musabeli.api_seguimiento_cloud.services;


import com.musabeli.api_seguimiento_cloud.dto.CreateSeguimientoDto;
import com.musabeli.api_seguimiento_cloud.entities.Seguimiento;

public interface SeguimientoService {

    Seguimiento createSeguimiento(CreateSeguimientoDto seguimientoDto);
}
