package com.example.recargas.infrastructure.output.persistence;

import com.example.recargas.domain.model.Recarga;
import com.example.recargas.domain.ports.RecargaRepositorio;
import com.example.recargas.infrastructure.output.persistence.entity.RecargaEntity;
import com.example.recargas.infrastructure.output.persistence.mapper.RecargaMapper;
import com.example.recargas.infrastructure.output.persistence.repository.RecargaRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class RecargaPersistenceAdapter implements RecargaRepositorio {

    private final RecargaRepository recargaRepository;
    private final RecargaMapper recargaMapper;

    @Override
    public List<Recarga> list() {
        List<RecargaEntity> list = recargaRepository.findAll();
        return list.stream()
          .map(recargaMapper::toRecarga)
          .collect(Collectors.toList());
    }

    @Override
    public Optional<Recarga> listarByid(long id) {
        Optional<RecargaEntity> recargaEntity = recargaRepository.findById(id);
        return recargaEntity.map(recargaMapper::toRecarga);
    }

    @Override
    public List<Recarga> listarByOperador(String operador) {
        List<RecargaEntity> list = recargaRepository.findByOperador(operador);
        return list.stream()
          .map(recargaMapper::toRecarga)
          .collect(Collectors.toList());
    }

    @Override
    public Recarga save(Recarga recarga) {
        RecargaEntity recargaEntity = recargaMapper.toEntity(recarga);
        recargaEntity = recargaRepository.save(recargaEntity);
        return recargaMapper.toRecarga(recargaEntity);
    }

}
