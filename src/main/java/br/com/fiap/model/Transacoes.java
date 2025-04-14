package br.com.fiap.model;

import java.time.LocalDate;

public class Transacoes {

    private int idTransacao;
    private int idConta;
    private int idSaldo;
    private String tipoTransacao;
    private double valorTransacao;
    private LocalDate dataTransacao;


    public Transacoes(int idTransacao, int idConta, int idSaldo, String tipoTransacao, double valorTransacao, LocalDate dataTransacao) {
        this.idTransacao = idTransacao;
        this.idConta = idConta;
        this.idSaldo = idSaldo;
        this.tipoTransacao = tipoTransacao;
        this.valorTransacao = valorTransacao;
        this.dataTransacao = dataTransacao;
    }

    public Transacoes(int idConta, int idSaldo, String tipoTransacao, double valorTransacao, LocalDate dataTransacao) {
        this.idConta = idConta;
        this.idSaldo = idSaldo;
        this.tipoTransacao = tipoTransacao;
        this.valorTransacao = valorTransacao;
        this.dataTransacao = dataTransacao;
    }

    public Transacoes(){}

    public int getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(int idTransacao) {
        this.idTransacao = idTransacao;
    }

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public int getIdSaldo() {
        return idSaldo;
    }

    public void setIdSaldo(int idSaldo) {
        this.idSaldo = idSaldo;
    }

    public String getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(String tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public double getValorTransacao() {
        return valorTransacao;
    }

    public void setValorTransacao(double valorTransacao) {
        this.valorTransacao = valorTransacao;
    }

    public LocalDate getDataTransacao() {
        return dataTransacao;
    }

    public void setDataTransacao(LocalDate dataTransacao) {
        this.dataTransacao = dataTransacao;
    }
}

