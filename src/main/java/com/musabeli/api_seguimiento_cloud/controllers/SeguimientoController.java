package com.musabeli.api_seguimiento_cloud.controllers;

import com.musabeli.api_seguimiento_cloud.dto.CodigoRequestDto;
import com.musabeli.api_seguimiento_cloud.dto.CreateSeguimientoDto;
import com.musabeli.api_seguimiento_cloud.dto.UbicacionActualResponseDto;
import com.musabeli.api_seguimiento_cloud.dto.UpdateSeguimientoDto;
import com.musabeli.api_seguimiento_cloud.entities.Seguimiento;
import com.musabeli.api_seguimiento_cloud.services.SeguimientoService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/seguimiento")
@Slf4j
public class SeguimientoController {

    @Autowired
    private SeguimientoService seguimientoService;

    @PostMapping
    public ResponseEntity<Seguimiento> createSeguimiento(@Valid @RequestBody CreateSeguimientoDto dto){
        Seguimiento nuevoSeguimiento = this.seguimientoService.createSeguimiento(dto);
        log.info("MÉTODO POST: crear un seguimiento OK");

        // Uso de Hateoas
        nuevoSeguimiento.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(SeguimientoController.class).getSeguimientoById(nuevoSeguimiento.getId())
        ).withSelfRel());

        nuevoSeguimiento.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(SeguimientoController.class).getSeguimientos()).withRel(IanaLinkRelations.COLLECTION));

        return ResponseEntity.created(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(SeguimientoController.class).getSeguimientoById(nuevoSeguimiento.getId())
                ).toUri()
        ).body(nuevoSeguimiento);
    }

    @GetMapping("/getSeguimientos")
    public ResponseEntity<List<Seguimiento>> getSeguimientos(){
        List<Seguimiento> seguimientoList = this.seguimientoService.getAllSeguimientos();

        for (Seguimiento seguimiento : seguimientoList){
            seguimiento.add(WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(SeguimientoController.class).getSeguimientoById(seguimiento.getId())
            ).withSelfRel());

            seguimiento.add(WebMvcLinkBuilder.linkTo(
                    WebMvcLinkBuilder.methodOn(SeguimientoController.class).getSeguimientos()
            ).withRel(IanaLinkRelations.COLLECTION));
        }

        log.info("METODO GET: getSeguimientos OK");
        return ResponseEntity.status(HttpStatus.OK).body(seguimientoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seguimiento> getSeguimientoById(@PathVariable Long id){
        Seguimiento seguimiento = this.seguimientoService.getSeguimientoById(id);

        seguimiento.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(SeguimientoController.class).getSeguimientoById(seguimiento.getId())
        ).withSelfRel());

        seguimiento.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(SeguimientoController.class).getSeguimientos()
        ).withRel(IanaLinkRelations.COLLECTION));

        log.info("Seguimiento encontrado: id: {},", seguimiento.getId());
        return ResponseEntity.status(HttpStatus.OK).body(seguimiento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Seguimiento> updateSeguimiento(@PathVariable Long id, @Valid @RequestBody UpdateSeguimientoDto segumientoDto){
        Seguimiento seguimiento = this.seguimientoService.updateSeguimiento(id, segumientoDto);

        seguimiento.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(SeguimientoController.class).getSeguimientoById(seguimiento.getId())
        ).withSelfRel());

        seguimiento.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(SeguimientoController.class).getSeguimientos()
        ).withRel(IanaLinkRelations.COLLECTION));

        log.info("Seguimiento con id: {} actualizado", seguimiento.getId());
        return ResponseEntity.status(HttpStatus.OK).body(seguimiento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Seguimiento> deleteSeguimiento(@PathVariable Long id){
        Seguimiento seguimiento = this.seguimientoService.deleteSeguimiento(id);
        log.info("Seguimiento con id: {} eliminado", seguimiento.getId());
        return ResponseEntity.status(HttpStatus.OK).body(seguimiento);
    }


    @PostMapping("/getUbicacionActual")
    public ResponseEntity<UbicacionActualResponseDto> getUbicacionActual(@Valid @RequestBody CodigoRequestDto codigoDto){
        UbicacionActualResponseDto ubicacionActual = this.seguimientoService.getUbicacionActual(codigoDto);
        log.info("Consultar ubicación actual de seguimiento OK");
        return ResponseEntity.status(HttpStatus.OK).body(ubicacionActual);
    }
}