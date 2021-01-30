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
public class MainPedido {
    
    public static void main(String[] args) {
        Pedido pedidoP = new Pedido();
        Pedido pedidoE = new Pedido();
        Pedido pedidoR = new Pedido();
        
        EstadoPreparacion estadoPreparacion = new EstadoPreparacion();
        EstadoEnviado estadoEnviado = new EstadoEnviado();
        EstadoRecibido estadoRecibido = new EstadoRecibido();
        
        pedidoP.setEstado(estadoPreparacion);
        pedidoE.setEstado(estadoEnviado);
        pedidoR.setEstado(estadoRecibido);
        
        System.out.println(pedidoP.getEstado().getClass().getName());
        /*pedidoP.getEstado().cambiarEstado(pedidoP, "enviado");
        pedidoP.getEstado().cambiarEstado(pedidoP, "recibido");
        
        pedidoE.getEstado().cambiarEstado(pedidoP, "enviado");
        pedidoE.getEstado().cambiarEstado(pedidoP, "recibido");
        
        pedidoR.getEstado().cambiarEstado(pedidoP, "enviado");
        pedidoR.getEstado().cambiarEstado(pedidoP, "recibido");
        
        pedidoP.getEstado().eliminar(pedidoP);
        pedidoE.getEstado().eliminar(pedidoE);
        pedidoR.getEstado().eliminar(pedidoR);
        
        pedidoP.getEstado().eliminar(pedidoP);
        pedidoP.getEstado().cambiarEstado(pedidoP, "recibido");
        pedidoP.getEstado().cambiarEstado(pedidoP, "enviado");
        pedidoP.getEstado().eliminar(pedidoP);
        pedidoP.getEstado().cambiarEstado(pedidoP, "enviado");
        pedidoP.getEstado().cambiarEstado(pedidoP, "recibido");
        pedidoP.getEstado().cambiarEstado(pedidoP, "recibido");*/
    }
}
