
package StatePedido;

import javax.swing.JOptionPane;

/**
 * Estado concreto: Implementa la interfaz Estado. 
 * Implementa el comportamiento para el estado enviado.
 *
 */
public class EstadoEnviado implements Estado{

    @Override
    public boolean cambiarEstado(Pedido pedido, String siguienteEstado) {
        // siguienteEstado - [enviado, recibido]
        boolean cambiado = false;
        
        if(siguienteEstado.equalsIgnoreCase("enviado")){
            JOptionPane.showMessageDialog(null, "ERROR: El pedido está actualmente enviado");
        }
        else if(siguienteEstado.equalsIgnoreCase("recibido")){
            cambiado = true;
            EstadoRecibido nuevoEstado = new EstadoRecibido();
            pedido.setEstado(nuevoEstado);
            JOptionPane.showMessageDialog(null, "Se ha confirmado la recepcion del pedido");
        }
        
        return cambiado;
    }

    @Override
    public boolean eliminar(Pedido pedido) {
        JOptionPane.showMessageDialog(null, "ERROR: El pedido ha sido enviado no se puede eliminar.");
        return false;
    }
    
}
