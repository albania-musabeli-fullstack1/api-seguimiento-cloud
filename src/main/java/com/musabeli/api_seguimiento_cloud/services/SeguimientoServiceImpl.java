package com.musabeli.api_seguimiento_cloud.services;


import com.musabeli.api_seguimiento_cloud.dto.CreateSeguimientoDto;
import com.musabeli.api_seguimiento_cloud.dto.UpdateSeguimientoDto;
import com.musabeli.api_seguimiento_cloud.entities.Seguimiento;
import com.musabeli.api_seguimiento_cloud.exceptions.CodSeguimientoExistsException;
import com.musabeli.api_seguimiento_cloud.exceptions.ResourceNotFoundException;
import com.musabeli.api_seguimiento_cloud.mapper.SeguimientoMapper;
import com.musabeli.api_seguimiento_cloud.repositories.SeguimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeguimientoServiceImpl implements SeguimientoService {

    @Autowired
    private SeguimientoRepository seguimientoRepository;

    private Seguimiento findSeguimientoById(Long id){
        Optional<Seguimiento> seguimiento = this.seguimientoRepository.findById(id);

        if (seguimiento.isPresent()){
            return seguimiento.get();
        }
        else {
            throw new ResourceNotFoundException("No existen registros de seguimientos con id " + id);
        }
    }

    @Override
    public Seguimiento createSeguimiento(CreateSeguimientoDto seguimientoDto) {
        // Validar si existe un codigo de seguimiento
        boolean seguimientoExiste = this.seguimientoRepository.
                findByCodSeguimiento(seguimientoDto.getCodSeguimiento()).isPresent();

        if (seguimientoExiste) throw new CodSeguimientoExistsException(
                "Ya existe un seguimiento con el código: " + seguimientoDto.getCodSeguimiento()
        );

        Seguimiento seguimiento = SeguimientoMapper.fromCreateSeguimiento(seguimientoDto);
        // guardar en la bbdd
        return this.seguimientoRepository.save(seguimiento);
    }

    @Override
    public List<Seguimiento> getAllSeguimientos() {
        return this.seguimientoRepository.findAll();
    }

    @Override
    public Seguimiento getSeguimientoById(Long id) {
        return this.findSeguimientoById(id);
    }

    @Override
    public Seguimiento updateSeguimiento(Long id, UpdateSeguimientoDto updateSeguimientoDto) {

        Seguimiento updateSeguimiento = this.findSeguimientoById(id);
        if (updateSeguimientoDto.getCodSeguimiento() != null){
            // Validar si existe un codigo de seguimiento
            boolean seguimientoExiste = this.seguimientoRepository.
                    findByCodSeguimiento(updateSeguimientoDto.getCodSeguimiento()).isPresent();

            if (seguimientoExiste) throw new CodSeguimientoExistsException(
                    "Ya existe un seguimiento con el código: " + updateSeguimientoDto.getCodSeguimiento()
            );

            updateSeguimiento.setCodSeguimiento(updateSeguimientoDto.getCodSeguimiento());
        }
        if (updateSeguimientoDto.getRemitente() != null){
            updateSeguimiento.setRemitente(updateSeguimientoDto.getRemitente());
        }
        if (updateSeguimientoDto.getDestinatario() != null){
            updateSeguimiento.setDestinatario(updateSeguimiento.getDestinatario());
        }
        if (updateSeguimientoDto.getPaisOrigen() != null){
            updateSeguimiento.setPaisOrigen(updateSeguimiento.getPaisOrigen());
        }
        if (updateSeguimientoDto.getPaisDestino() != null){
            updateSeguimiento.setPaisDestino(updateSeguimiento.getPaisDestino());
        }
        if (updateSeguimientoDto.getFechaEnvio() != null){
            updateSeguimiento.setFechaEnvio(updateSeguimiento.getFechaEnvio());
        }
        if (updateSeguimientoDto.getEstado() != null){
            updateSeguimiento.setEstado(updateSeguimiento.getEstado());
        }
        if (updateSeguimientoDto.getUbicacionActual() != null){
            updateSeguimiento.setUbicacionActual(updateSeguimiento.getUbicacionActual());
        }

        // actualizar en bbdd
        return this.seguimientoRepository.save(updateSeguimiento);
    }
}
