package com.musabeli.api_seguimiento_cloud.repositories;

import com.musabeli.api_seguimiento_cloud.entities.Seguimiento;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SeguimientoRepositoryTest {

    @Autowired
    private SeguimientoRepository seguimientoRepository;

    @Test
    public void saveSeguimientoTest(){
        //Arrange
        Seguimiento seguimiento = Seguimiento.builder()
                .codSeguimiento("asd123")
                .remitente("Albania")
                .destinatario("Sofia")
                .paisOrigen("Chile")
                .paisDestino("Argentina")
                .fechaEnvio(LocalDateTime.parse("2025-04-20T10:15:00"))
                .estado("EN CAMINO A DESTINO")
                .ubicacionActual("CENTRO DISTRIBUCION FEDEX")
                .build();

        //Act
        Seguimiento resultado = seguimientoRepository.save(seguimiento);

        //Assert
        assertNotNull(resultado.getId());
        assertEquals("CENTRO DISTRIBUCION FEDEX", resultado.getUbicacionActual());
    }


    @Test
    public void findSeguimientoByIdTest(){
        //Arrange
        Seguimiento seguimiento = Seguimiento.builder()
                .codSeguimiento("asd123")
                .remitente("Albania")
                .destinatario("Sofia")
                .paisOrigen("Chile")
                .paisDestino("Argentina")
                .fechaEnvio(LocalDateTime.parse("2025-04-20T10:15:00"))
                .estado("EN CAMINO A DESTINO")
                .ubicacionActual("CENTRO DISTRIBUCION FEDEX")
                .build();

        Seguimiento newSeguimiento = seguimientoRepository.save(seguimiento);

        //Act
        Seguimiento segumientoById = seguimientoRepository.findById(newSeguimiento.getId()).orElse(null);

        //Assert
        assertNotNull(segumientoById);
        assertEquals(newSeguimiento.getId(), segumientoById.getId());
    }


    @Test
    public void findAllSeguimientosTest(){
        // Arrange
        seguimientoRepository.save(
                Seguimiento.builder()
                        .codSeguimiento("asd123")
                        .remitente("Albania")
                        .destinatario("Sofia")
                        .paisOrigen("Chile")
                        .paisDestino("Argentina")
                        .fechaEnvio(LocalDateTime.parse("2025-04-20T10:15:00"))
                        .estado("EN CAMINO A DESTINO")
                        .ubicacionActual("CENTRO DISTRIBUCION FEDEX")
                        .build()
        );
        seguimientoRepository.save(
                Seguimiento.builder()
                        .codSeguimiento("qwe123")
                        .remitente("Esteban")
                        .destinatario("Antonio")
                        .paisOrigen("Chile")
                        .paisDestino("Francia")
                        .fechaEnvio(LocalDateTime.parse("2025-04-20T10:15:00"))
                        .estado("EN CAMINO A DESTINO")
                        .ubicacionActual("CENTRO DISTRIBUCION FEDEX")
                        .build()
        );

        //Act
        List<Seguimiento> seguimientoList = seguimientoRepository.findAll();

        //Assert
        assertTrue(seguimientoList.size() >= 2);
    }


    @Test
    public void deleteSeguimientoTest(){
        //Arrange
        Seguimiento seguimiento = Seguimiento.builder()
                .codSeguimiento("asd123")
                .remitente("Albania")
                .destinatario("Sofia")
                .paisOrigen("Chile")
                .paisDestino("Argentina")
                .fechaEnvio(LocalDateTime.parse("2025-04-20T10:15:00"))
                .estado("EN CAMINO A DESTINO")
                .ubicacionActual("CENTRO DISTRIBUCION FEDEX")
                .build();

        Seguimiento saved = seguimientoRepository.save(seguimiento);
        Long id = saved.getId();

        //Act
        seguimientoRepository.deleteById(id);

        //Assert
        boolean exists = seguimientoRepository.findById(id).isPresent();
        assertFalse(exists);
    }


    @Test
    public void findByCodSeguimientoTest(){
        //Arrange
        Seguimiento seguimiento = Seguimiento.builder()
                .codSeguimiento("asd123")
                .remitente("Albania")
                .destinatario("Sofia")
                .paisOrigen("Chile")
                .paisDestino("Argentina")
                .fechaEnvio(LocalDateTime.parse("2025-04-20T10:15:00"))
                .estado("EN CAMINO A DESTINO")
                .ubicacionActual("CENTRO DISTRIBUCION FEDEX")
                .build();
        seguimientoRepository.save(seguimiento);

        //Act
        Seguimiento encontrado = seguimientoRepository.findByCodSeguimiento("asd123").orElse(null);

        //Assert
        assertNotNull(encontrado);
        assertEquals("asd123", encontrado.getCodSeguimiento());
    }
}