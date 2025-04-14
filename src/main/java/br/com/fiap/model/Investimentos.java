package br.com.fiap.model;

public class Investimentos {

    private int idInvestimento;
    private int idConta;
    private int idSaldo;
    private String tipoAtivo;
    private double valorInvestido;
    private double rendimentoBruto;
    private double rendimentoLiquido;



    public Investimentos(int idInvestimento, int idConta, int idSaldo, String tipoAtivo, double valorInvestido, double rendimentoBruto, double rendimentoLiquido) {
        this.idInvestimento = idInvestimento;
        this.idConta = idConta;
        this.idSaldo = idSaldo;
        this.tipoAtivo = tipoAtivo;
        this.valorInvestido = valorInvestido;
        this.rendimentoBruto = rendimentoBruto;
        this.rendimentoLiquido = rendimentoLiquido;
    }

    public Investimentos(int idConta, int idSaldo, String tipoAtivo, double valorInvestido, double rendimentoBruto, double rendimentoLiquido) {
        this.idConta = idConta;
        this.idSaldo = idSaldo;
        this.tipoAtivo = tipoAtivo;
        this.valorInvestido = valorInvestido;
        this.rendimentoBruto = rendimentoBruto;
        this.rendimentoLiquido = rendimentoLiquido;
    }

    public Investimentos(){}


    public int getIdInvestimento() {
        return idInvestimento;
    }

    public void setIdInvestimento(int idInvestimento) {
        this.idInvestimento = idInvestimento;
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

    public String getTipoAtivo() {
        return tipoAtivo;
    }

    public void setTipoAtivo(String tipoAtivo) {
        this.tipoAtivo = tipoAtivo;
    }

    public double getValorInvestido() {
        return valorInvestido;
    }

    public void setValorInvestido(double valorInvestido) {
        this.valorInvestido = valorInvestido;
    }

    public double getRendimentoBruto() {
        return rendimentoBruto;
    }

    public void setRendimentoBruto(double rendimentoBruto) {
        this.rendimentoBruto = rendimentoBruto;
    }

    public double getRendimentoLiquido() {
        return rendimentoLiquido;
    }

    public void setRendimentoLiquido(double rendimentoLiquido) {
        this.rendimentoLiquido = rendimentoLiquido;
    }




}
