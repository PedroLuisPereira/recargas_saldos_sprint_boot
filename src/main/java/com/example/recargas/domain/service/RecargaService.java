package com.example.recargas.domain.service;

import com.example.recargas.domain.dto.RecargaSolicitudDto;
import com.example.recargas.domain.exception.NoExisteOperadorException;
import com.example.recargas.domain.exception.NoSaldoException;
import com.example.recargas.domain.exception.RegistroDuplicadoException;
import com.example.recargas.domain.exception.RegistroNotFoundException;
import com.example.recargas.domain.model.Recarga;
import com.example.recargas.domain.ports.RecargaRepositorio;

import java.util.List;


public class RecargaService {

    private final RecargaRepositorio recargaRepositorio;

    public RecargaService(RecargaRepositorio recargaRepositorio) {
        this.recargaRepositorio = recargaRepositorio;
    }

    public List<Recarga> listar() {
        return recargaRepositorio.list();
    }

    public Recarga crear(RecargaSolicitudDto recargaSolicitudDto) {

        List<Recarga> lista = recargaRepositorio.listarByOperador(recargaSolicitudDto.getOperador().toUpperCase());

        if (!lista.isEmpty()) {
            throw new RegistroDuplicadoException("Ya existe operador");
        }

        Recarga recarga = Recarga.getInstance(
          recargaSolicitudDto.getValor(),
          recargaSolicitudDto.getOperador().toUpperCase()
        );

        return recargaRepositorio.save(recarga);
    }

    public Recarga comprar(RecargaSolicitudDto recargaSolicitudDto) {

        List<Recarga> lista = recargaRepositorio.listarByOperador(recargaSolicitudDto.getOperador().toUpperCase());

        Recarga recarga = lista.stream().findFirst().orElseThrow(() ->
          new NoExisteOperadorException("Operador no existe")
        );

        Recarga recarga1 = Recarga.getInstance(
          recarga.getId(),
          recarga.getSaldo() + recargaSolicitudDto.getValor(),
          recargaSolicitudDto.getOperador()
        );

        recargaRepositorio.save(recarga1);

        return Recarga.getInstance(
          recarga.getId(),
          recargaSolicitudDto.getValor(),
          recarga.getOperador()
        );
    }

    public Recarga vender(RecargaSolicitudDto recargaSolicitudDto) {

        List<Recarga> lista = recargaRepositorio.listarByOperador(recargaSolicitudDto.getOperador().toUpperCase());

        Recarga recarga = lista.stream().findFirst().orElseThrow(() ->
          new NoExisteOperadorException("Operador no existe")
        );

        if (recarga.getSaldo() < recargaSolicitudDto.getValor()) {
            throw new NoSaldoException("Saldo insufuciente");
        }

        Recarga recarga1 = Recarga.getInstance(
          recarga.getId(),
          recarga.getSaldo() - recargaSolicitudDto.getValor(),
          recargaSolicitudDto.getOperador()
        );

        recargaRepositorio.save(recarga1);

        return Recarga.getInstance(
          recarga.getId(),
          recargaSolicitudDto.getValor(),
          recarga.getOperador()
        );
    }

}
