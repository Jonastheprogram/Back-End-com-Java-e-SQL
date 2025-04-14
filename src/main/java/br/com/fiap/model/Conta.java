package br.com.fiap.model;

import java.time.LocalDate;

public class Conta {

    private  int idConta;
    private int idCliente;
    private int numeroConta;
    private String tipoConta;
    private LocalDate dataAbertura;

    public Conta(int idConta, int idCliente, int numeroConta, String tipoConta, LocalDate dataAbertura) {
        this.idConta = idConta;
        this.idCliente = idCliente;
        this.numeroConta = numeroConta;
        this.tipoConta = tipoConta;
        this.dataAbertura = dataAbertura;
    }

    public Conta(int numeroConta, String tipoConta, LocalDate dataAbertura) {
        this.numeroConta = numeroConta;
        this.tipoConta = tipoConta;
        this.dataAbertura = dataAbertura;
    }

    public Conta(){}



    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }





}
