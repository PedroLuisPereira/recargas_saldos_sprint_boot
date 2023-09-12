package com.example.recargas.domain.service;

import com.example.recargas.domain.dto.RecargaSolicitudDto;
import com.example.recargas.domain.exception.NoExisteOperadorException;
import com.example.recargas.domain.exception.NoSaldoException;
import com.example.recargas.domain.exception.RegistroDuplicadoException;
import com.example.recargas.domain.model.Recarga;
import com.example.recargas.domain.ports.RecargaRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;


import static org.mockito.ArgumentMatchers.*;

@SpringBootTest
class RecargaServiceTest {

    @Mock
    RecargaRepositorio recargaRepositorio;

    @InjectMocks
    RecargaService recargaService; //clase que va a recibir todos los mock


    @Test
    void debeListarTodasLasRecargas() {

        // 1. Preparación
        List<Recarga> recargas = Arrays.asList(
          Recarga.getInstance(1L, 50000, "TIGO"),
          Recarga.getInstance(2L, 55000, "CLARO")
        );

        Mockito.when(recargaRepositorio.list()).thenReturn(recargas);

        // 2. Ejecución
        List<Recarga> listar = recargaService.listar();

        // 3. Comparacion
        Assertions.assertEquals(2, listar.size());

    }

    @Test
    void debeCrearUnaNuevaRecarga() {

        // 1. Preparación
        RecargaSolicitudDto recargaSolicitudDto = new RecargaSolicitudDto(
          50000,
          "TIGO"
        );

        Recarga recarga = Recarga.getInstance(
          50000,
          "TIGO"
        );

        Mockito.when(recargaRepositorio.save(any())).thenReturn(recarga);

        // 2. Ejecución
        Recarga respuesta = recargaService.crear(recargaSolicitudDto);

        // 3. Comparacion
        Assertions.assertEquals(50000, respuesta.getSaldo());

    }

    @Test
    void debeLanzarErrorAlCrearRecargaOperadorYaExite() {

        // 1. Preparación
        RecargaSolicitudDto recargaSolicitudDto = new RecargaSolicitudDto(
          50000,
          "TIGO"
        );


        Recarga recarga = Recarga.getInstance(
          1L,
          50000,
          "TIGO"
        );

        Mockito.when(recargaRepositorio.listarByOperador(anyString())).thenReturn(Arrays.asList(recarga));
        Mockito.when(recargaRepositorio.save(any())).thenReturn(recarga);

        // 2. Ejecución
        RegistroDuplicadoException thrown = Assertions.assertThrows(RegistroDuplicadoException.class, () -> {
            recargaService.crear(recargaSolicitudDto);
        });

        // 3. Comparacion
        Assertions.assertEquals("Ya existe operador", thrown.getMessage());

    }

    @Test
    void debePoderComprarUnaRecarga() {

        // 1. Preparación
        RecargaSolicitudDto recargaSolicitudDto = new RecargaSolicitudDto(
          50000,
          "TIGO"
        );

        Recarga recarga = Recarga.getInstance(
          1L,
          50000,
          "TIGO"
        );

        Mockito.when(recargaRepositorio.listarByOperador(anyString())).thenReturn(Arrays.asList(recarga));
        Mockito.when(recargaRepositorio.save(any())).thenReturn(recarga);

        // 2. Ejecución
        Recarga respuesta = recargaService.comprar(recargaSolicitudDto);

        // 3. Comparacion
        Assertions.assertEquals(50000, respuesta.getSaldo());

    }

    @Test
    void noDebePoderComprarUnaRecargaOperadorNoExiste() {

        // 1. Preparación
        RecargaSolicitudDto recargaSolicitudDto = new RecargaSolicitudDto(
          50000,
          "TIGO"
        );

        Recarga recarga = Recarga.getInstance(
          50000,
          "TIGO"
        );

        Mockito.when(recargaRepositorio.listarByOperador(anyString())).thenReturn(Arrays.asList());
        Mockito.when(recargaRepositorio.save(any())).thenReturn(recarga);

        // 2. Ejecución
        NoExisteOperadorException thrown = Assertions.assertThrows(NoExisteOperadorException.class, () -> {
            recargaService.comprar(recargaSolicitudDto);
        });

        // 3. Comparacion
        Assertions.assertEquals("Operador no existe", thrown.getMessage());

    }

    @Test
    void debePoderVenderUnaRecarga() {

        // 1. Preparación
        RecargaSolicitudDto recargaSolicitudDto = new RecargaSolicitudDto(
          50000,
          "TIGO"
        );

        Recarga recarga = Recarga.getInstance(
          1L,
          500000,
          "TIGO"
        );

        Mockito.when(recargaRepositorio.listarByOperador(anyString())).thenReturn(Arrays.asList(recarga));
        Mockito.when(recargaRepositorio.save(any())).thenReturn(recarga);

        // 2. Ejecución
        Recarga respuesta = recargaService.vender(recargaSolicitudDto);

        // 3. Comparacion
        Assertions.assertEquals(50000, respuesta.getSaldo());

    }

    @Test
    void noDebePoderVenderUnaRecargaOperadorNoExiste() {

        // 1. Preparación
        RecargaSolicitudDto recargaSolicitudDto = new RecargaSolicitudDto(
          50000,
          "TIGO"
        );

        Recarga recarga = Recarga.getInstance(
          50000,
          "TIGO"
        );

        Mockito.when(recargaRepositorio.listarByOperador(anyString())).thenReturn(Arrays.asList());
        Mockito.when(recargaRepositorio.save(any())).thenReturn(recarga);

        // 2. Ejecución
        NoExisteOperadorException thrown = Assertions.assertThrows(NoExisteOperadorException.class, () -> {
            recargaService.vender(recargaSolicitudDto);
        });

        // 3. Comparacion
        Assertions.assertEquals("Operador no existe", thrown.getMessage());

    }

    @Test
    void noDebePoderVenderUnaRecargaSaldoInsuficiente() {

        // 1. Preparación
        RecargaSolicitudDto recargaSolicitudDto = new RecargaSolicitudDto(
          50000,
          "TIGO"
        );

        Recarga recarga = Recarga.getInstance(
          5000,
          "TIGO"
        );

        Mockito.when(recargaRepositorio.listarByOperador(anyString())).thenReturn(Arrays.asList(recarga));
        Mockito.when(recargaRepositorio.save(any())).thenReturn(recarga);

        // 2. Ejecución
        NoSaldoException thrown = Assertions.assertThrows(NoSaldoException.class, () -> {
            recargaService.vender(recargaSolicitudDto);
        });

        // 3. Comparacion
        Assertions.assertEquals("Saldo insufuciente", thrown.getMessage());

    }


}
