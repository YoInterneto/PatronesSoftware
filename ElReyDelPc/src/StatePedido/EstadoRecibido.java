
package StatePedido;

import javax.swing.JOptionPane;

/**
 * Estado concreto: Implementa la interfaz Estado. 
 * Implementa el comportamiento para el estado recibido.
 *
 */
public class EstadoRecibido implements Estado{

    @Override
    public boolean cambiarEstado(Pedido pedido, String siguienteEstado) {
        JOptionPane.showMessageDialog(null, "ERROR: El pedido ya ha sido recibido.\nNo se puede cambiar su estado");
        return false;
    }

    @Override
    public boolean eliminar(Pedido pedido) {
        JOptionPane.showMessageDialog(null, "ERROR: El pedido no se puede eliminar ya se ha confirmado recepción");
        return false;
    }
    
    
}
