
package classes;

import java.util.ArrayList;
import java.util.List;


public class ListaUsuarios {
         // Declaração de variáveis 
      private static final List<Usuarios> lista = new ArrayList<>();

      // Métodos para acessarmos a lista e adicionarmos novos itens
      public static List<Usuarios> Listar() {
          return lista;
      }
      
      public static void Adicionar(Usuarios funcionario) {
          lista.add(funcionario);
      }
}
