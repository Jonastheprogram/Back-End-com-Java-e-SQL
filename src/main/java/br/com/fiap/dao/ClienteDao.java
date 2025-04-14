package br.com.fiap.dao;
import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.Cliente;
import br.com.fiap.exception.EntidadeNaoEncontradaException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao {
    private Connection conexao;

    public ClienteDao(Connection conexao) throws SQLException {
        this.conexao = conexao;
    }


    public void cadastrarCliente(Cliente cliente) throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("INSERT INTO T_CLIENTES (id_cliente, nome, sobrenome, endereco, nr_telefone, dt_nascimento) VALUES (seq_cliente.nextval, ?, ?, ?, ?, ?)");
        stm.setString(1, cliente.getNomeCliente());
        stm.setString(2, cliente.getSobrenomeCliente());
        stm.setString(3, cliente.getEndereco());
        stm.setInt(4, cliente.getNumeroCliente());
        LocalDate dataNascimento = cliente.getDataNascimento();
        stm.setDate(5, Date.valueOf(dataNascimento));
        stm.executeUpdate();
        System.out.println("Cliente cadastrado com sucesso!");
    }


    private Cliente parseCliente(ResultSet result) throws SQLException {
        int id = result.getInt("id_cliente");
        String nome = result.getString("nome");
        String sobrenome = result.getString("sobrenome");
        String endereco = result.getString("endereco");
        int numero = result.getInt("nr_telefone");
        Date dataNascimentoSQL = result.getDate("dt_nascimento");
        LocalDate dataNascimento = (dataNascimentoSQL != null) ? dataNascimentoSQL.toLocalDate() : null;
        return new Cliente(id, nome, sobrenome, endereco, numero, dataNascimento);
    }

    public Cliente pesquisarCliente(int idCliente) throws SQLException, EntidadeNaoEncontradaException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM t_clientes WHERE id_cliente = ?");
        stm.setInt(1, idCliente);
        ResultSet result = stm.executeQuery();
        if (!result.next()) throw new EntidadeNaoEncontradaException("Cliente não encontrado");

        return parseCliente(result);
    }


    public List<Cliente> listarClientes() throws SQLException {
        PreparedStatement stm = conexao.prepareStatement("SELECT * FROM T_CLIENTES");
        ResultSet result = stm.executeQuery();
        List<Cliente> lista = new ArrayList<>();
        while (result.next()) {
            lista.add(parseCliente(result));
        }
        return lista;
    }


    public void removerCliente(int idCliente) throws SQLException, EntidadeNaoEncontradaException {
        PreparedStatement stm = conexao.prepareStatement("DELETE from t_clientes where id_cliente = ?");
        stm.setInt(1, idCliente);
        int linha = stm.executeUpdate();
        if (linha == 0)
            throw new EntidadeNaoEncontradaException("Cliente não encontrado para ser removido");
    }


    public void fecharConexao() throws SQLException {
        conexao.close();
    }


    //MÉTODO GETALL PARA CLIENTES
    public static void main(String[] args) {

            try {
                Connection conexao = ConnectionFactory.getConnection();
                ClienteDao clienteDao = new ClienteDao(conexao);
                List<Cliente> listaClientes = clienteDao.listarClientes();

                for (Cliente cliente : listaClientes) {
                    System.out.println("-----------------------------------------");
                    System.out.println("ID: " + cliente.getIdCliente());
                    System.out.println("Nome: " + cliente.getNomeCliente());
                    System.out.println("Sobrenome: " + cliente.getSobrenomeCliente());
                    System.out.println("Endereço: " + cliente.getEndereco());
                    System.out.println("Telefone: " + cliente.getNumeroCliente());
                    System.out.println("Data de Nascimento: " + cliente.getDataNascimento());
                }
            } catch (SQLException er) {
                er.printStackTrace();
            }
        }
    }








