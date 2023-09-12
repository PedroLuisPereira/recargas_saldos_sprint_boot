package com.example.recargas.domain.ports;


import com.example.recargas.domain.model.Recarga;

import java.util.List;
import java.util.Optional;

public interface RecargaRepositorio {

    List<Recarga> list();

    Optional<Recarga> listarByid(long id);

    List<Recarga> listarByOperador(String operador);

    Recarga save(Recarga recarga);


}
