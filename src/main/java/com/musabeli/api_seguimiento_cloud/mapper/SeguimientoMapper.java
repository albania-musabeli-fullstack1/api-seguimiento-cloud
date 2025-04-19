package com.musabeli.api_seguimiento_cloud.mapper;

import com.musabeli.api_seguimiento_cloud.dto.CreateSeguimientoDto;
import com.musabeli.api_seguimiento_cloud.entities.Seguimiento;

public class SeguimientoMapper {

    public static Seguimiento fromCreateSeguimiento(CreateSeguimientoDto dto){
        return Seguimiento.builder()
                .codSeguimiento(dto.getCodSeguimiento())
                .remitente(dto.getRemitente())
                .destinatario(dto.getDestinatario())
                .paisOrigen(dto.getPaisOrigen())
                .paisDestino(dto.getPaisDestino())
                .fechaEnvio(dto.getFechaEnvio())
                .estado(dto.getEstado())
                .ubicacionActual(dto.getUbicacionActual())
                .build();
    }
}
