package com.musabeli.api_seguimiento_cloud.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSeguimientoDto {

    @Pattern(regexp = "^[A-Z0-9]{20}$", message = "El código de seguimiento debe tener 20 caracteres, solo letras mayúsculas y números")
    private String codSeguimiento;

    @Size(min = 4, max = 60, message = "El nombre del remitente debe tener entre 4 y 60 caracteres")
    private String remitente;

    @Size(min = 4, max = 60, message = "El nombre del destinatario debe tener entre 4 y 60 caracteres")
    private String destinatario;

    private String paisOrigen;

    private String paisDestino;

    private LocalDateTime fechaEnvio;

    @Pattern(
            regexp = "^(EN BODEGA|EN ADUANAS|EN CAMINO A DESTINO|ENTREGADO|RETRASADO)$",
            message = "El estado debe ser uno de los siguientes: EN BODEGA, EN ADUANAS, EN CAMINO A DESTINO, ENTREGADO o RETRASADO"
    )
    private String estado;

    @Size(min = 3, max = 40, message = "La ubicación actual debe tener entre 3 y 40 caracteres")
    private String ubicacionActual;
}
