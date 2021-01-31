package Command;

import StatePedido.Pedido;
import java.util.ArrayList;

/**
 * Crea la reunión, el comando y el invocador 
 * @author Salvador Oton
 */
public class Cliente {

    public static void main(String args[]) {
        try {
            // Fecha de la reunión
            FechaHora fh = new FechaHora(1, 6, 2010, 10, 30);
            // Reunión
            
            Pedido p = new Pedido();
            
            System.out.println("\n- Antes de ejecutar los comandos:" + p.toString());

            // Comando para cambiar la localización
            ComandoDeshacer comando = new ComandoCambiarAlgo();
            comando.setPedido(p);
            // Invocador
            Invocador inv = new Invocador();
            // Establece y ejecuta el comando
            inv.setComando(comando);
            inv.ejecutaComando("2,5,4");
            System.out.println("\n- Después de ejecutar el comando localización:" + p.toString());
            // Deshace el comando
            inv.deshacerComando();
            System.out.println("\n- Deshacer:" + p.toString());
            // Rehace el comando
            inv.rehacerComando();
            System.out.println("\n- Rehacer:" + p.toString());
            
            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
