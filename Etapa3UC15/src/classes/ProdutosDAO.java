
package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class ProdutosDAO {
        public static int cadastrar(Produtos produtos) {
        int status;
        try {
            //Estabelecendo conexao
            ConexaoUC15 conn = new ConexaoUC15();
            conn.conectar();

            //Para acessar o getConexao atraves do 'conn' e chamar o preparedStatement
            PreparedStatement st = conn.getConexao().prepareStatement("INSERT INTO produtos (nome, valor, validade) VALUES(?,?,?)");
            st.setString(1, produtos.getProduto());
            st.setString(2, produtos.getValor());
            st.setString(3, produtos.getData());
            

            status = st.executeUpdate();

            conn.desconectar();
            return status; //retornar 1
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }
    }
    
     public static void atualizar(Produtos produtos) {

        try {
            ConexaoUC15 conn = new ConexaoUC15();
            conn.conectar();
            String sql = "UPDATE produtos SET nome=?, valor=?, data=? WHERE id=?";
            //esse trecho é igual ao método inserir
            PreparedStatement stmt = conn.getConexao().prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            //Setando os parâmetros
            stmt.setString(1, produtos.getProduto());
            stmt.setString(2, produtos.getValor());
            stmt.setString(3, produtos.getData());
            stmt.setInt(4, produtos.getId());
            //Executando a query
            stmt.execute();
            //tratando o erro, caso ele ocorra
        } catch (Exception e) {
            System.out.println("Erro ao editar cadastro de cliente: " + e.getMessage());
        }

    }
     
     
     
     
         //Metodo para retornar todos os clientes cadastrados  
    public static List<Produtos> listarTodos() {

        List<Produtos> lista = new ArrayList<Produtos>();

        try {
            //Estabelecendo conexao
            ConexaoUC15 conn = new ConexaoUC15();
            conn.conectar();

            //Para acessar o getConexao atraves do 'conn' e chamar o preparedStatement
            PreparedStatement st = conn.getConexao().prepareStatement("SELECT * FROM produtos");

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Produtos produtos = new Produtos();

                produtos.setId(rs.getInt("id"));
                produtos.setProduto(rs.getString("nome"));
                produtos.setValor(rs.getString("valor"));
                produtos.setData(rs.getString("validade"));
                //Adicionando os elementos na lista criada
                lista.add(produtos);
            }

            conn.desconectar();
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());

        }
        return lista;
    }
}
