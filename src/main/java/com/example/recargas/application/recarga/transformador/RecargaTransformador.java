package com.example.recargas.application.recarga.transformador;

import com.example.recargas.application.recarga.dto.RecargaCrearDto;
import com.example.recargas.application.recarga.dto.RecargaRespuestaDto;
import com.example.recargas.domain.dto.RecargaSolicitudDto;

import com.example.recargas.domain.model.Recarga;

public class RecargaTransformador {

    private RecargaTransformador() {
    }

    public static RecargaSolicitudDto trasnformar(RecargaCrearDto recargaCrearDto) {
        return new RecargaSolicitudDto(
          recargaCrearDto.getValor(),
          recargaCrearDto.getOperador()
        );

    }

    public static RecargaRespuestaDto trasnformar(Recarga recarga) {
        return new RecargaRespuestaDto(
          recarga.getId(),
          recarga.getSaldo(),
          recarga.getOperador());

    }
}
