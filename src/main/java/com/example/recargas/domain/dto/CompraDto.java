package com.example.recargas.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CompraDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String Operador;
    private double valor;

    public CompraDto() {
    }

    public CompraDto(String operador, double valor) {
        Operador = operador;
        this.valor = valor;
    }

}