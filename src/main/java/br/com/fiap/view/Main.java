package br.com.fiap.view;
import java.sql.Connection;
import br.com.fiap.dao.*;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.*;
import java.sql.SQLException;
import java.time.LocalDate;


//TESTE DE MÉTODOS
public class Main {
    public static void main(String[] args) {
        Connection conexao = null;
        try {
            conexao = ConnectionFactory.getConnection();
            ClienteDao clienteDao = new ClienteDao(conexao);
            ContaDao contaDao = new ContaDao(conexao);
            SaldoDao saldoDao = new SaldoDao(conexao);
            TransacoesDao transacoesDao = new TransacoesDao(conexao);
            BeneficiarioDao beneficiarioDao = new BeneficiarioDao(conexao);
            InvestimentosDao investimentoDao = new InvestimentosDao(conexao);

            //MÉTODOS INSERT

            //MUDE CONFORME CADA ADIÇÃO DE CLIENTE

            // Cadastrar cliente
            Cliente cliente = new Cliente();
            cliente.setNomeCliente("João"); //NOME CLIENTE
            cliente.setSobrenomeCliente("França"); //SOBRENOME CLIENTE
            cliente.setEndereco("Rua Natalino, 227"); //ENDEREÇO
            cliente.setNumeroCliente(9946879); //NUMERO DE TELEFONE
            cliente.setDataNascimento(LocalDate.of(2001, 6, 12)); //DATA DE NASCIMENTO DO CLIENTE
            clienteDao.cadastrarCliente(cliente); //MÉTODO PARA SALVAR E CADASTRAR INFORMAÇÕES

            // Cadastrar conta
            Conta conta = new Conta();
            conta.setIdCliente(cliente.getIdCliente());
            conta.setNumeroConta(8795432);//NUMERO DE CONTA DO CLIENTE
            conta.setTipoConta("Corrente");//TIPO DE CONTA
            conta.setDataAbertura(LocalDate.of(2022, 9, 11));//DATA DE ABERTURA
            contaDao.cadastrarConta(conta); //MÉTODO PARA SALVAR E CADASTRAR E INFORMAÇÕES

            // Depositar saldo
            Saldo saldo = new Saldo();
            saldo.setIdConta(conta.getIdConta());
            saldo.setSaldoConta(2500);//SALDO DA CONTA
            saldo.setExtrato("Deposito inicial");//INFORMAÇÕES DE DEPÓSITO EM CONTA
            saldo.setDataMovimentacoes(LocalDate.now());//DATA DE MOVIMENTAÇÕES
            saldoDao.depositar(saldo);//SALVAR E REGISTRAR INFORMAÇÕES


            //Registrar Transacao
            Transacoes transacoes = new Transacoes();
            transacoes.setIdConta(conta.getIdConta());
            transacoes.setIdSaldo(saldo.getIdSaldo());
            transacoes.setTipoTransacao("PIX");//TIPO DE TRANSAÇÃO FEITA
            transacoes.setValorTransacao(80);//VALOR DA TRANSAÇÃO
            transacoes.setDataTransacao(LocalDate.now());//DATA DA TRANSAÇÃO
            transacoesDao.registrarTransacoes(transacoes);//SALVAR E REGISTRAR INFORMAÇÕES

            //Cadastrar Beneficiario
            Beneficiario beneficiario = new Beneficiario();
            beneficiario.setIdConta(conta.getIdConta());
            beneficiario.setIdSaldo(saldo.getIdSaldo());
            beneficiario.setNomeBeneficiario("Bruna Elias");//NOME DO BENEFICIÁRIO
            beneficiario.setContaBeneficiario(8956354);//NUMERO DA CONTA DO BENEFICIARIO
            beneficiarioDao.cadastrarBeneficiario(beneficiario);

            //Inserir Investimento
            Investimentos investimento = new Investimentos();
            investimento.setIdConta(conta.getIdConta());
            investimento.setIdSaldo(saldo.getIdSaldo());
            investimento.setTipoAtivo("LCI");//TIPO DE ATIVO DO INVESTIMENTO
            investimento.setValorInvestido(500);//VALOR INVESTIDO
            investimento.setRendimentoBruto(50);// RENDIMENTO BRUTO DO INVESTIMENTO
            investimento.setRendimentoLiquido(50);// RENDIMENTO LIQUIDO DO INVESTIMENTO
            investimentoDao.inserirInvestimento(investimento);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}

