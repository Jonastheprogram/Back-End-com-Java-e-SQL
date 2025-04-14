package br.com.fiap.dao;
import br.com.fiap.exception.EntidadeNaoEncontradaException;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Investimentos;
import br.com.fiap.model.Transacoes;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransacoesDao {
    private Connection conexao;
    private ContaDao contaDao;
    private SaldoDao saldoDao;


    public TransacoesDao(Connection conexao) throws SQLException {
        this.conexao = conexao;
        this.contaDao = new ContaDao(conexao);
        this.saldoDao = new SaldoDao(conexao);
    }


    public void registrarTransacoes(Transacoes transacoes) throws SQLException {

        PreparedStatement stm = conexao.prepareStatement("INSERT INTO t_transacoes (id_transacao, id_conta, id_saldo, tp_transacao, vl_transacao, dt_hora_transacao) VALUES (seq_transacao.nextval, seq_conta.currval, seq_saldo.currval ,?,?,?)");
        stm.setString(1, transacoes.getTipoTransacao());
        stm.setDouble(2, transacoes.getValorTransacao());
        LocalDate dataTransacao = transacoes.getDataTransacao();
        stm.setDate(3, Date.valueOf(dataTransacao));
        stm.executeUpdate();
        System.out.println("Transação feita com sucesso!");

    }

    public Transacoes parseTransacoes(ResultSet result) throws SQLException{
        int id = result.getInt("ID_TRANSACAO");
        int idConta = result.getInt("ID_CONTA");
        int idSaldo = result.getInt("ID_SALDO");
        String tipoTransacao = result.getString("TP_TRANSACAO");
        double valorTransacao = result.getDouble("VL_TRANSACAO");
        Date dataTransacaoSQL = result.getDate("DT_HORA_TRANSACAO");
        LocalDate dataTransacao = (dataTransacaoSQL != null) ? dataTransacaoSQL.toLocalDate() : null;
        return new Transacoes(id, idConta, idSaldo, tipoTransacao, valorTransacao, dataTransacao);

    }

    public List<Transacoes> listarTransacoes() throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM T_TRANSACOES");
        ResultSet result = stm.executeQuery();
        List<Transacoes> lista = new ArrayList<>();
        while (result.next()) {
            lista.add(parseTransacoes(result));
        }
        return lista;
    }

    public Transacoes localizarTransacoes(int idTransacao) throws SQLException, EntidadeNaoEncontradaException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM t_transacoes WHERE id_transacao = ?");
        stm.setLong(1, idTransacao);
        ResultSet result = stm.executeQuery();
        if (!result.next()) {
            throw new EntidadeNaoEncontradaException("Transação não encontrada");
        }return parseTransacoes(result);
    }



    public void removerTransacao(int idTransacao) throws SQLException, EntidadeNaoEncontradaException {
        PreparedStatement stm = conexao.prepareStatement("DELETE from t_transacoes where id_transacao = ?");
        stm.setLong(1, idTransacao);
        int linha = stm.executeUpdate();
        if (linha == 0)
            throw new EntidadeNaoEncontradaException("Transação  não encontrada!");
    }


    public void fecharConexao() throws SQLException {
        conexao.close();
    }

    public static void main(String[] args) {


        //MÉTODO GETALL PARA TRANSAÇÕES
        try {
            Connection conexao = ConnectionFactory.getConnection();
            TransacoesDao transacoesDao = new TransacoesDao(conexao);
            List<Transacoes> listaTransacoes = transacoesDao.listarTransacoes();

            for (Transacoes transacoes : listaTransacoes) {
                System.out.println("-----------------------------------------");
                System.out.println("ID Transação: " + transacoes.getIdTransacao());
                System.out.println("ID Conta: " + transacoes.getIdConta());
                System.out.println("ID Saldo: " + transacoes.getIdSaldo());
                System.out.println("Tipo de Transação: " + transacoes.getTipoTransacao());
                System.out.println("Valor da Transação: " + transacoes.getValorTransacao());
                System.out.println("Data da Transação: " + transacoes.getDataTransacao());

            }
        } catch (SQLException er) {
            er.printStackTrace();
        }

    }

}