package br.com.fiap.model;
import java.time.LocalDate;


public class Cliente {

    private int idCliente;
    private String nomeCliente;
    private String sobrenomeCliente;
    private String endereco;
    private Integer numeroCliente;
    private LocalDate dataNascimento;

    public Cliente(int idCliente, String nomeCliente, String sobrenomeCliente, String endereco, Integer numeroCliente, LocalDate dataNascimento){
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.sobrenomeCliente = sobrenomeCliente;
        this.endereco = endereco;
        this.numeroCliente = numeroCliente;
        this.dataNascimento = dataNascimento;

    }

    public Cliente(String nomeCliente, String sobrenomeCliente, String endereco, Integer numeroCliente, LocalDate dataNascimento){
        this.nomeCliente = nomeCliente;
        this.sobrenomeCliente = sobrenomeCliente;
        this.endereco = endereco;
        this.numeroCliente = numeroCliente;
        this.dataNascimento = dataNascimento;

    }

    public Cliente(){}

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getSobrenomeCliente() {
        return sobrenomeCliente;
    }

    public void setSobrenomeCliente(String sobrenomeCliente) {
        this.sobrenomeCliente = sobrenomeCliente;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getNumeroCliente() {
        return numeroCliente;
    }

    public void setNumeroCliente(Integer numeroCliente) {
        this.numeroCliente = numeroCliente;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }


}
