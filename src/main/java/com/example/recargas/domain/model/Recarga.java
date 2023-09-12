package com.example.recargas.domain.model;

import com.example.recargas.domain.dto.Operador;
import com.example.recargas.domain.exception.CampoConException;
import com.example.recargas.domain.validation.Validacion;

import java.io.Serializable;
import java.util.Arrays;

public class Recarga implements Serializable {

    private final Long id;

    private final double saldo;

    private final String operador;


    private Recarga(Long id, double saldo,  String operador ) {
        this.id = id;
        this.saldo = saldo;
        this.operador = operador;
    }

    public static Recarga getInstance(long id, double saldo, String operador ) {

        Validacion.validarValorPositivo(saldo, "El campo valor es obligatorio");
        Validacion.validarObligatorio(operador, "El campo operador es obligatorio");

        validarOperador(operador);

        return new Recarga(id, saldo, operador);
    }

    public static Recarga getInstance(double saldo, String operador) {

        Validacion.validarValorPositivo(saldo, "El campo saldo debe se positivo");
        Validacion.validarObligatorio(operador, "El campo operador es obligatorio");

        validarOperador(operador);

        return new Recarga(null, saldo, operador);
    }


    private static void validarOperador(String operador) {
        long total = Arrays.stream(Operador.values())
          .filter(valor -> valor.name().equals(operador.toUpperCase()))
          .count();

        if (total == 0) {
            throw new CampoConException("Operador incorrecto");
        }
    }


    public Long getId() {
        return id;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getOperador() {
        return operador;
    }
}
