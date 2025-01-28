
package classes;

import java.util.ArrayList;
import java.util.List;


public class ListaClientes {
         // Declaração de variáveis 
      private static final List<Clientes> lista = new ArrayList<>();

      // Métodos para acessarmos a lista e adicionarmos novos itens
      public static List<Clientes> Listar() {
          return lista;
      }
      
      public static void Adicionar(Clientes clientes) {
          lista.add(clientes);
      }
}
