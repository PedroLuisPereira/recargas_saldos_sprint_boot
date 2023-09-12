package com.example.recargas.application.recarga.comando;

import com.example.recargas.application.recarga.transformador.RecargaTransformador;
import com.example.recargas.application.recarga.dto.RecargaCrearDto;
import com.example.recargas.application.recarga.dto.RecargaRespuestaDto;
import com.example.recargas.domain.dto.RecargaSolicitudDto;
import com.example.recargas.domain.model.Recarga;
import com.example.recargas.domain.service.RecargaService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class RecargaVender {

    private final RecargaService recargaService;

    public RecargaVender(RecargaService recargaService) {
        this.recargaService = recargaService;
    }

    @Transactional
    public RecargaRespuestaDto ejecutar(RecargaCrearDto recargaCrearDto) {
        RecargaSolicitudDto recargaSolicitudDto = RecargaTransformador.trasnformar(recargaCrearDto);
        Recarga recarga = recargaService.vender(recargaSolicitudDto);
        return RecargaTransformador.trasnformar(recarga);
    }
}
