/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StatePedido;

/**
 *
 * @author Alberto
 */
public interface Estado {
    
    public boolean cambiarEstado(Pedido pedido, String siguienteEstado);
    
    public boolean eliminar(Pedido pedido);
}
