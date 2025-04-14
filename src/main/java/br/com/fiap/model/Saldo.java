package br.com.fiap.model;

import java.time.LocalDate;

public class Saldo {
    private int idSaldo;
    private int idConta;
    private double saldoConta;
    private String extrato;
    private LocalDate dataMovimentacoes;

    public Saldo(int idSaldo, int idConta, double saldoConta, String extrato, LocalDate dataMovimentacoes) {
        this.idSaldo = idSaldo;
        this.idConta = idConta;
        this.saldoConta = saldoConta;
        this.extrato = extrato;
        this.dataMovimentacoes = dataMovimentacoes;
    }

    public Saldo(double saldoConta, String extrato, LocalDate dataMovimentacoes) {
        this.saldoConta = saldoConta;
        this.extrato = extrato;
        this.dataMovimentacoes = dataMovimentacoes;
    }

    public Saldo(){}

    public int getIdSaldo() {
        return idSaldo;
    }

    public void setIdSaldo(int idSaldo) {
        this.idSaldo = idSaldo;
    }

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public double getSaldoConta() {
        return saldoConta;
    }

    public void setSaldoConta(double saldoConta) {
        this.saldoConta = saldoConta;
    }

    public String getExtrato() {
        return extrato;
    }

    public void setExtrato(String extrato) {
        this.extrato = extrato;
    }

    public LocalDate getDataMovimentacoes() {
        return dataMovimentacoes;
    }

    public void setDataMovimentacoes(LocalDate dataMovimentacoes) {
        this.dataMovimentacoes = dataMovimentacoes;
    }







}
