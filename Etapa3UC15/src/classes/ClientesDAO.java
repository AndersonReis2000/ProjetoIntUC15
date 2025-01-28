
package classes;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClientesDAO {
    public static int cadastrar(Clientes clientes) {
        int status;
        try {
            //Estabelecendo conexao
            ConexaoUC15 conn = new ConexaoUC15();
            conn.conectar();

            //Para acessar o getConexao atraves do 'conn' e chamar o preparedStatement
            PreparedStatement st = conn.getConexao().prepareStatement("INSERT INTO clientes (nome, cpf, telefone, endereco) VALUES(?,?,?,?)");
            st.setString(1, clientes.getNome());
            st.setString(2, clientes.getCpf());
            st.setString(3, clientes.getTelefone());
            st.setString(4, clientes.getEndereco());

            status = st.executeUpdate();

            conn.desconectar();
            return status; //retornar 1
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }
    }
    
     public static void atualizar(Clientes clientes) {

        try {
            ConexaoUC15 conn = new ConexaoUC15();
            conn.conectar();
            String sql = "UPDATE clientes SET nome=?, cpf=?, telefone=?, endereco=? WHERE id=?";
            //esse trecho é igual ao método inserir
            PreparedStatement stmt = conn.getConexao().prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            //Setando os parâmetros
            stmt.setString(1, clientes.getNome());
            stmt.setString(2, clientes.getCpf());
            stmt.setString(3, clientes.getTelefone());
            stmt.setString(4, clientes.getEndereco());
            stmt.setInt(5, clientes.getId());
            //Executando a query
            stmt.execute();
            //tratando o erro, caso ele ocorra
        } catch (Exception e) {
            System.out.println("Erro ao editar cadastro de cliente: " + e.getMessage());
        }

    }
     
     
     
     
         //Metodo para retornar todos os clientes cadastrados  
    public static List<Clientes> listarTodos() {

        List<Clientes> lista = new ArrayList<Clientes>();

        try {
            //Estabelecendo conexao
            ConexaoUC15 conn = new ConexaoUC15();
            conn.conectar();

            //Para acessar o getConexao atraves do 'conn' e chamar o preparedStatement
            PreparedStatement st = conn.getConexao().prepareStatement("SELECT * FROM clientes");

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Clientes clientes = new Clientes();

                clientes.setId(rs.getInt("id"));
                clientes.setNome(rs.getString("nome"));
                clientes.setCpf(rs.getString("cpf"));
                clientes.setTelefone(rs.getString("telefone"));
                clientes.setEndereco(rs.getString("endereco"));
                //Adicionando os elementos na lista criada
                lista.add(clientes);
            }

            conn.desconectar();
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());

        }
        return lista;
    }
}
