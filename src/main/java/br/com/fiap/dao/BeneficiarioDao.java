package br.com.fiap.dao;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Beneficiario;
import br.com.fiap.exception.EntidadeNaoEncontradaException;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BeneficiarioDao {
    private Connection conexao;
    private ContaDao contaDao;
    private SaldoDao saldoDao;


    public BeneficiarioDao(Connection conexao) throws SQLException {
        this.conexao = conexao;
        this.contaDao = new ContaDao(conexao);
        this.saldoDao = new SaldoDao(conexao);
    }


    public void cadastrarBeneficiario(Beneficiario beneficiario) throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("INSERT INTO t_beneficiario (id_beneficiario, id_conta, id_saldo, nm_beneficiario, conta_benef) VALUES (seq_investimento.nextval, seq_conta.CURRVAL, seq_saldo.CURRVAL, ?,?)");
        stm.setString(1, beneficiario.getNomeBeneficiario());
        stm.setInt(2, beneficiario.getContaBeneficiario());
        stm.executeUpdate();
        System.out.println("Beneficiario cadastrado com sucesso!");

    }

    public List<Beneficiario> listarBeneficiarios() throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM T_BENEFICIARIO");
        ResultSet result = stm.executeQuery();
        List<Beneficiario> lista = new ArrayList<>();
        while (result.next()) {
            lista.add(parseBeneficiario(result));
        }
        return lista;
    }


    public Beneficiario pesquisarBeficiario(int idBeneficiario) throws SQLException, EntidadeNaoEncontradaException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM t_beneficiario WHERE id_beneficiario = ?");
        stm.setInt(1, idBeneficiario);
        ResultSet result = stm.executeQuery();
        return parseBeneficiario(result);
    }



    public Beneficiario parseBeneficiario(ResultSet result) throws SQLException{
        int id = result.getInt("ID_BENEFICIARIO");
        int idConta = result.getInt("ID_CONTA");
        int idSaldo = result.getInt("ID_SALDO");
        String nmBeneficiario = result.getString("NM_BENEFICIARIO");
        int contaBenef = result.getInt("CONTA_BENEF");
        return new Beneficiario(id, idConta, idSaldo, nmBeneficiario, contaBenef);
    }


    public void removerBeneficiario(int idBeneficiario) throws SQLException, EntidadeNaoEncontradaException {
        PreparedStatement stm = conexao.prepareStatement("DELETE from t_beneficiario where id_beneficiario = ?");
        stm.setLong(1, idBeneficiario);
        int linha = stm.executeUpdate();
        if (linha == 0)
            throw new EntidadeNaoEncontradaException("Beneficiario não encontrado!");
    }


    public void fecharConexao() throws SQLException {
        conexao.close();
    }

    //MÉTODO GETALL PARA BENEFICIARIOS
    public static void main(String[] args) {

        try {
            Connection conexao = ConnectionFactory.getConnection();
            BeneficiarioDao beneficiarioDao = new BeneficiarioDao(conexao);
            List<Beneficiario> listaBeneficiarios = beneficiarioDao.listarBeneficiarios();

            for (Beneficiario beneficiario : listaBeneficiarios) {
                System.out.println("-----------------------------------------");
                System.out.println("ID Beneficiário: " + beneficiario.getIdBeneficiario());
                System.out.println("ID Conta: " + beneficiario.getIdConta());
                System.out.println("ID Saldo: " + beneficiario.getIdSaldo());
                System.out.println("Nome do Beneficiário: " + beneficiario.getNomeBeneficiario());
                System.out.println("Conta do Beneficiário: " + beneficiario.getContaBeneficiario());

            }
        } catch (SQLException er) {
            er.printStackTrace();
        }

    }

}