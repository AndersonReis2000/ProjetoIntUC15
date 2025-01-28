
package classes;

import java.util.ArrayList;
import java.util.List;

public class ListaProdutos {
          // Declaração de variáveis 
      private static final List<Produtos> lista = new ArrayList<>();

      // Métodos para acessarmos a lista e adicionarmos novos itens
      public static List<Produtos> Listar() {
          return lista;
      }
      
      public static void Adicionar(Produtos produtos) {
          lista.add(produtos);
      }
}
