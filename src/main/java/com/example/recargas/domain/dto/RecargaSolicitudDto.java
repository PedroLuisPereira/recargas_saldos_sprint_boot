package com.example.recargas.domain.dto;


public class RecargaSolicitudDto {

    private double valor;

    private String operador;


    public RecargaSolicitudDto() {
    }

    public RecargaSolicitudDto(double valor, String operador) {
        this.valor = valor;
        this.operador = operador;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    @Override
    public String toString() {
        return "RecargaSolicitudCrear{" +
          "valor=" + valor +
          ", operador='" + operador + '\'' +
          '}';
    }
}
