
package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UsuariosDAO {
    public static int cadastrar(Usuarios usuarios) {
        int status;
        try {
            //Estabelecendo conexao
            ConexaoUC15 conn = new ConexaoUC15();
            conn.conectar();

            //Para acessar o getConexao atraves do 'conn' e chamar o preparedStatement
            PreparedStatement st = conn.getConexao().prepareStatement("INSERT INTO usuarios (nome, cpf, cargo) VALUES(?,?,?)");
            st.setString(1, usuarios.getNome());
            st.setString(2, usuarios.getCpf());
            st.setString(3, usuarios.getTipoFuncionario());
            

            status = st.executeUpdate();

            conn.desconectar();
            return status; //retornar 1
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }
    }
    
     public static void atualizar(Usuarios usuarios) {

        try {
            ConexaoUC15 conn = new ConexaoUC15();
            conn.conectar();
            String sql = "UPDATE usuarios SET nome=?, cpf=?, cargo=? WHERE id=?";
            //esse trecho é igual ao método inserir
            PreparedStatement stmt = conn.getConexao().prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            //Setando os parâmetros
            stmt.setString(1, usuarios.getNome());
            stmt.setString(2, usuarios.getCpf());
            stmt.setString(3, usuarios.getTipoFuncionario());
            stmt.setInt(5, usuarios.getId());
            //Executando a query
            stmt.execute();
            //tratando o erro, caso ele ocorra
        } catch (Exception e) {
            System.out.println("Erro ao editar cadastro de cliente: " + e.getMessage());
        }

    }
     
     
     
     
         //Metodo para retornar todos os clientes cadastrados  
    public static List<Usuarios> listarTodos() {

        List<Usuarios> lista = new ArrayList<Usuarios>();

        try {
            //Estabelecendo conexao
            ConexaoUC15 conn = new ConexaoUC15();
            conn.conectar();

            //Para acessar o getConexao atraves do 'conn' e chamar o preparedStatement
            PreparedStatement st = conn.getConexao().prepareStatement("SELECT * FROM usuarios");

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Usuarios usuarios = new Usuarios();

                usuarios.setId(rs.getInt("id"));
                usuarios.setNome(rs.getString("nome"));
                usuarios.setCpf(rs.getString("cpf"));
                usuarios.setTipoFuncionario(rs.getString("cargo"));
                //Adicionando os elementos na lista criada
                lista.add(usuarios);
            }

            conn.desconectar();
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());

        }
        return lista;
    }
}
