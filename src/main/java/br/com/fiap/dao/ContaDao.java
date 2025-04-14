package br.com.fiap.dao;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Conta;
import br.com.fiap.exception.EntidadeNaoEncontradaException;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ContaDao {
    private Connection conexao;

    public ContaDao(Connection conexao) throws SQLException {
        this.conexao = conexao;

    }


    public void cadastrarConta(Conta conta) throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("INSERT INTO t_conta (id_conta, id_cliente, nr_conta, tp_conta, dt_abertura) VALUES (seq_conta.NEXTVAL, seq_cliente.CURRVAL, ?, ?, ?)");
        stm.setInt(1, conta.getNumeroConta());
        stm.setString(2, conta.getTipoConta());
        LocalDate dataAbertura = conta.getDataAbertura();
        stm.setDate(3, Date.valueOf(dataAbertura));
        stm.executeUpdate();
        System.out.println("Conta criada com sucesso!");
    }


    private Conta parseConta(ResultSet result) throws SQLException {
        int id = result.getInt("ID_CONTA");
        int idCliente = result.getInt("ID_CLIENTE");
        int numeroConta = result.getInt("NR_CONTA");
        String tipoConta = result.getString("TP_CONTA");
        Date dataAberturaSQL = result.getDate("DT_ABERTURA");
        LocalDate dataAbertura = (dataAberturaSQL != null) ? dataAberturaSQL.toLocalDate() : null;
        ClienteDao dao = new ClienteDao(conexao);
        dao.pesquisarCliente(idCliente);
        return new Conta(id, idCliente, numeroConta, tipoConta, dataAbertura);
    }


    public Conta pesquisarConta(long idConta) throws SQLException, EntidadeNaoEncontradaException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM t_conta WHERE id_conta = ?");
        stm.setLong(1, idConta);
        ResultSet result = stm.executeQuery();

        if (!result.next()) {
            throw new EntidadeNaoEncontradaException("Conta não encontrada");
        }
        return parseConta(result);

    }


    public List<Conta> listarContas() throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM T_CONTA");
        ResultSet result = stm.executeQuery();
        List<Conta> lista = new ArrayList<>();
        while (result.next()) {
            lista.add(parseConta(result));
        }
        return lista;
    }

    public void removerConta(int idConta) throws SQLException, EntidadeNaoEncontradaException {
        PreparedStatement stm = conexao.prepareStatement("DELETE from t_conta where id_conta = ?");
        stm.setLong(1, idConta);
        int linha = stm.executeUpdate();
        if (linha == 0)
            throw new EntidadeNaoEncontradaException("Conta não encontrada para ser removida");
    }


    public void fecharConexao() throws SQLException {
        conexao.close();

    }

    public static void main(String[] args) {

        //MÉTODO GETALL PARA CONTAS
        try {
            Connection conexao = ConnectionFactory.getConnection();
            ContaDao contaDao = new ContaDao(conexao);
            List<Conta> listaContas = contaDao.listarContas();

            for (Conta conta : listaContas) {
                System.out.println("-----------------------------------------");
                System.out.println("ID Conta: " + conta.getIdConta());
                System.out.println("ID Cliente: " + conta.getIdCliente());
                System.out.println("Numero de Conta: " + conta.getNumeroConta());
                System.out.println("Tipo de Conta: " + conta.getTipoConta());
                System.out.println("Data de Abertura Conta: " + conta.getDataAbertura());


            }
        } catch (SQLException er) {
            er.printStackTrace();
        }
    }


}






