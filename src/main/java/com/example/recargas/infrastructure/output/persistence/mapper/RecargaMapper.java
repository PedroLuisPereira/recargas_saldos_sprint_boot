package com.example.recargas.infrastructure.output.persistence.mapper;

import com.example.recargas.domain.model.Recarga;
import com.example.recargas.infrastructure.output.persistence.entity.RecargaEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class RecargaMapper {

    @Autowired
    private ModelMapper mapper;

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
