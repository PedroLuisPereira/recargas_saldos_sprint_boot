package com.example.recargas.infrastructure.output.persistence.mapper;

import com.example.recargas.domain.model.Recarga;
import com.example.recargas.infrastructure.output.persistence.entity.RecargaEntity;


public class RecargaMapper {

  

    public Recarga toRecarga(RecargaEntity entity) {

        return Recarga.getInstance(
          entity.getId(),
          entity.getSaldo(),
          entity.getOperador()
        );
    }

    public RecargaEntity toEntity(Recarga recarga) {

        return new RecargaEntity(
          recarga.getId(),
          recarga.getSaldo(),
          recarga.getOperador()
        );
    }

}
