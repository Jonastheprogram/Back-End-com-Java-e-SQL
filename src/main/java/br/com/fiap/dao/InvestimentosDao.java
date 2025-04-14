package br.com.fiap.dao;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Investimentos;
import br.com.fiap.exception.EntidadeNaoEncontradaException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class InvestimentosDao {
    private Connection conexao;
    private ContaDao contaDao;
    private SaldoDao saldoDao;


    public InvestimentosDao(Connection conexao) throws SQLException {
        this.conexao = conexao;
        this.contaDao = new ContaDao(conexao);
        this.saldoDao = new SaldoDao(conexao);
    }


    public void inserirInvestimento(Investimentos investimentos) throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("INSERT INTO t_investimentos (id_investimento, id_conta, id_saldo, tipo_ativo, valor_investido, rendimento_bruto, rendimento_liquido) VALUES (seq_investimento.nextval, seq_conta.CURRVAL, seq_saldo.CURRVAL, ?,?,?,?)");
        stm.setString(1, investimentos.getTipoAtivo());
        stm.setDouble(2, investimentos.getValorInvestido());
        stm.setDouble(3, investimentos.getRendimentoBruto());
        stm.setDouble(4, investimentos.getRendimentoLiquido());
        stm.executeUpdate();
        System.out.println("Investimento feito com sucesso!");
    }


    public Investimentos localizarInvestimento(int idInvestimento) throws SQLException, EntidadeNaoEncontradaException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM t_investimentos WHERE id_investimento = ?");
        stm.setLong(1, idInvestimento);
        ResultSet result = stm.executeQuery();

        if (!result.next()) {
            throw new EntidadeNaoEncontradaException("Saldo não encontrado");
        }return parseInvestimentos(result);

    }


    public Investimentos parseInvestimentos(ResultSet result) throws SQLException{
        int id = result.getInt("ID_INVESTIMENTO");
        int idConta = result.getInt("ID_CONTA");
        int idSaldo = result.getInt("ID_SALDO");
        String tipoAtivo = result.getString("TIPO_ATIVO");
        double valorInvestido = result.getDouble("VALOR_INVESTIDO");
        double rendimentoBruto = result.getDouble("RENDIMENTO_BRUTO");
        double rendimentoLiquido = result.getDouble("RENDIMENTO_LIQUIDO");
        return new Investimentos(id, idConta, idSaldo, tipoAtivo, valorInvestido, rendimentoBruto, rendimentoLiquido);
    }

    public List<Investimentos> listarInvestimentos() throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM T_INVESTIMENTOS");
        ResultSet result = stm.executeQuery();
        List<Investimentos> lista = new ArrayList<>();
        while (result.next()) {
            lista.add(parseInvestimentos(result));
        }
        return lista;
    }


    public void removerInvestimento(int idInvestimento) throws SQLException, EntidadeNaoEncontradaException {
        PreparedStatement stm = conexao.prepareStatement("DELETE from t_investimentos where id_investimento = ?");
        stm.setLong(1, idInvestimento);
        int linha = stm.executeUpdate();
        if (linha == 0)
            throw new EntidadeNaoEncontradaException("Investimento  não encontrado!");
    }



    public void fecharConexao() throws SQLException {
        conexao.close();
    }

    public static void main(String[] args) {

        //MÉTODO GETALL PARA INVESTIMENTOS
        try {
            Connection conexao = ConnectionFactory.getConnection();
            InvestimentosDao investimentosDao = new InvestimentosDao(conexao);
            List<Investimentos> listaInvestimentos = investimentosDao.listarInvestimentos();

            for (Investimentos investimentos : listaInvestimentos) {
                System.out.println("-----------------------------------------");
                System.out.println("ID Investimento: " + investimentos.getIdInvestimento());
                System.out.println("ID Conta: " + investimentos.getIdConta());
                System.out.println("ID Saldo: " + investimentos.getIdSaldo());
                System.out.println("Tipo de Ativo: " + investimentos.getTipoAtivo());
                System.out.println("Valor Investido: " + investimentos.getValorInvestido());
                System.out.println("Rendimento Bruto: " + investimentos.getRendimentoBruto());
                System.out.println("Rendimento Líquido: " + investimentos.getRendimentoLiquido());
            }
        } catch (SQLException er) {
            er.printStackTrace();
        }

    }

}