package br.com.fiap.model;

public class Beneficiario {

    private int idBeneficiario;
    private int idConta;
    private int idSaldo;
    private String nomeBeneficiario;
    private int contaBeneficiario;


    public Beneficiario(int idBeneficiario, int idConta, int idSaldo, String nomeBeneficiario, int contaBeneficiario) {
        this.idBeneficiario = idBeneficiario;
        this.idConta = idConta;
        this.idSaldo = idSaldo;
        this.nomeBeneficiario = nomeBeneficiario;
        this.contaBeneficiario = contaBeneficiario;
    }

    public Beneficiario(int idConta, int idSaldo, String nomeBeneficiario, int contaBeneficiario) {
        this.idConta = idConta;
        this.idSaldo = idSaldo;
        this.nomeBeneficiario = nomeBeneficiario;
        this.contaBeneficiario = contaBeneficiario;
    }

    public Beneficiario(){}

    public int getIdBeneficiario() {
        return idBeneficiario;
    }

    public void setIdBeneficiario(int idBeneficiario) {
        this.idBeneficiario = idBeneficiario;
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

    public String getNomeBeneficiario() {
        return nomeBeneficiario;
    }

    public void setNomeBeneficiario(String nomeBeneficiario) {
        this.nomeBeneficiario = nomeBeneficiario;
    }

    public int getContaBeneficiario() {
        return contaBeneficiario;
    }

    public void setContaBeneficiario(int contaBeneficiario) {
        this.contaBeneficiario = contaBeneficiario;
    }
}
