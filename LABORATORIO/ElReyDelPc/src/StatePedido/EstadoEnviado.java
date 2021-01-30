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
            pedido.setEstado(this);
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
