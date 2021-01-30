/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StatePedido;

import javax.swing.JOptionPane;

/**
 *
 * @author Alberto
 */
public class EstadoPreparacion implements Estado{

    @Override
    public boolean cambiarEstado(Pedido pedido, String siguienteEstado) {
        // siguienteEstado - [enviado, recibido]
        boolean cambiado = false;
        
        if(siguienteEstado.equalsIgnoreCase("enviado")){
            EstadoEnviado nuevoEstado = new EstadoEnviado();
            pedido.setEstado(nuevoEstado);
            cambiado = true;
            JOptionPane.showMessageDialog(null, "El estado del pedido ha cambiado a enviado");
        }
        else if(siguienteEstado.equalsIgnoreCase("recibido")){
            JOptionPane.showMessageDialog(null, "ERROR: El pedido está aún en preparación");
        }
        
        return cambiado;
    }

    @Override
    public boolean eliminar(Pedido pedido) {
        JOptionPane.showMessageDialog(null, "Pedido eliminado con éxito");
        return true;
    }
    
}
