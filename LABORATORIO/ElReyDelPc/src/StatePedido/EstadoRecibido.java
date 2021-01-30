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
