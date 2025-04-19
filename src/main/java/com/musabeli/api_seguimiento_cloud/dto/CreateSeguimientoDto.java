package com.musabeli.api_seguimiento_cloud.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateSeguimientoDto {

    @NotBlank(message = "El código de seguimiento no puede estar vacío")
    @Pattern(regexp = "^[A-Z0-9]{20}$", message = "El código de seguimiento debe tener 20 caracteres, solo letras mayúsculas y números")
    private String codSeguimiento;

    @NotBlank(message = "El remitente no puede estar vacío")
    @Size(min = 4, max = 60, message = "El nombre del remitente debe tener entre 4 y 60 caracteres")
    private String remitente;

    @NotBlank(message = "El destinatario no puede estar vacío")
    @Size(min = 4, max = 60, message = "El nombre del destinatario debe tener entre 4 y 60 caracteres")
    private String destinatario;

    @NotBlank(message = "El país de origen no puede estar vacío")
    private String paisOrigen;

    @NotBlank(message = "El país de destino no puede estar vacío")
    private String paisDestino;

    @NotNull(message = "La fecha de envío no puede ser nula")
    private LocalDateTime fechaEnvio;

    @Pattern(
            regexp = "^(EN BODEGA|EN ADUANAS|EN CAMINO A DESTINO|ENTREGADO|RETRASADO)$",
            message = "El estado debe ser uno de los siguientes: EN BODEGA, EN ADUANAS, EN CAMINO A DESTINO, ENTREGADO o RETRASADO"
    )
    @NotBlank(message = "El estado no puede estar vacío")
    private String estado;

    @NotBlank(message = "La ubicación actual no puede estar vacía")
    @Size(min = 3, max = 40, message = "La ubicación actual debe tener entre 3 y 40 caracteres")
    private String ubicacionActual;
}