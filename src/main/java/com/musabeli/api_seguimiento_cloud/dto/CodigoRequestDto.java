package com.musabeli.api_seguimiento_cloud.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CodigoRequestDto {

    @NotBlank(message = "El código de seguimiento no puede estar vacío")
    @Pattern(regexp = "^[A-Z0-9]{20}$", message = "El código de seguimiento debe tener 20 caracteres, solo letras mayúsculas y números")
    private String codSeguimiento;
}
