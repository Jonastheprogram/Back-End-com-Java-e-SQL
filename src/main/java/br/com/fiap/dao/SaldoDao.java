package br.com.fiap.dao;
import br.com.fiap.model.Saldo;
import br.com.fiap.exception.EntidadeNaoEncontradaException;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SaldoDao {
    private Connection conexao;
    private ContaDao contaDao; // Instância da ContaDao


    public SaldoDao(Connection conexao) throws SQLException {
        this.conexao = conexao;
        this.contaDao = new ContaDao(conexao); // Inicializa ContaDao com a conexão
    }


    public void depositar(Saldo saldo) throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("INSERT INTO t_saldo (id_saldo, id_conta, saldo_conta, extrato, dt_movimentacoes) VALUES (seq_saldo.nextval, seq_conta.currval, ?, ?, ?)");
        stm.setDouble(1, saldo.getSaldoConta());
        stm.setString(2, saldo.getExtrato());
        LocalDate dataMovimentacao = saldo.getDataMovimentacoes();
        stm.setDate(3, Date.valueOf(dataMovimentacao));
        stm.executeUpdate();
        System.out.println("Saldo depositado com sucesso!");
    }


    private Saldo parseSaldo(ResultSet result) throws SQLException {
        int id = result.getInt("ID_SALDO");
        int idConta = result.getInt("ID_CONTA");
        double saldoConta = result.getDouble("SALDO_CONTA");
        String extrato = result.getString("EXTRATO");
        Date dataMovimentacoesSQL = result.getDate("DT_MOVIMENTACOES");
        LocalDate dataMovimentacoes = (dataMovimentacoesSQL != null) ? dataMovimentacoesSQL.toLocalDate() : null;
        return new Saldo(id, idConta, saldoConta, extrato, dataMovimentacoes);
    }

    public Saldo localizarSaldo(int idSaldo) throws SQLException, EntidadeNaoEncontradaException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM t_saldo WHERE id_saldo = ?");
        stm.setLong(1, idSaldo);
        ResultSet result = stm.executeQuery();

        if (!result.next()) {
            throw new EntidadeNaoEncontradaException("Saldo não encontrado");
        }

        return parseSaldo(result);

    }


    public void retirarSaldo(int idSaldo) throws SQLException, EntidadeNaoEncontradaException {
        PreparedStatement stm = conexao.prepareStatement("DELETE from t_saldo where id_saldo = ?");
        stm.setLong(1, idSaldo);
        int linha = stm.executeUpdate();
        if (linha == 0)
            throw new EntidadeNaoEncontradaException("Saldo em conta não encontrado para ser retirado");
    }


    public void fecharConexao() throws SQLException {
        conexao.close();
    }

}