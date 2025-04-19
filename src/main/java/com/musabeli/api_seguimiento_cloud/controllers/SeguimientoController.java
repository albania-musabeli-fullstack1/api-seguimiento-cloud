package com.musabeli.api_seguimiento_cloud.controllers;

import com.musabeli.api_seguimiento_cloud.dto.CreateSeguimientoDto;
import com.musabeli.api_seguimiento_cloud.entities.Seguimiento;
import com.musabeli.api_seguimiento_cloud.services.SeguimientoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seguimiento")
@Slf4j
public class SeguimientoController {

    @Autowired
    private SeguimientoService seguimientoService;

    @PostMapping
    public ResponseEntity<Seguimiento> createSeguimiento(@Valid @RequestBody CreateSeguimientoDto dto){
        Seguimiento nuevoSeguimiento = this.seguimientoService.createSeguimiento(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoSeguimiento);
    }
}
