package Command;

import Command.memento.Conserje;
import Command.memento.Originador;
import Command.memento.Recuerdo;
import StatePedido.Pedido;

/**
 * Implementa ComandoDeshacer. 
 * Proporciona el comportamiento necesario para cambiar el lugar de una reunión
 * @author Salvador Oton
 */
public class ComandoCambiarAlgo implements ComandoDeshacer {

    // Referencia al receptor.
    private Pedido pedido; 		
    // Gestor de recuerdos.
    private Conserje conserje = new Conserje();   	

    @Override
    public Pedido getPedido() {
        return this.pedido;
    }

    @Override
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    /**
     * Cambia el lugar de la reunión por otro introducida por el usuario y
     * almacena el valor anterior de la reunión por si luego se quiere recuperar.
     * @param param Nueva localización de la reunión.
     */    
    @Override
    public void ejecutar(String param) {
        
        Originador originador = new Originador();
        originador.setPedido(pedido);
        conserje.pushRecuerdo(originador.crearRecuerdo());
        pedido.setListaArticulos(param);
    }

    /**
     * Recupera el valor de la Pedido anterior al introducido por el usuario
     */
    @Override
    public void deshacer() {
        Recuerdo recuerdo = conserje.popRecuerdo();
        if (recuerdo != null){
            Originador originador = new Originador();
            originador.setPedido(pedido);
            conserje.pushRecuerdo(originador.crearRecuerdo());
            pedido.setListaArticulos(recuerdo.getPedido().getListaArticulos().toString()); 
        }
    }

    /**
     * Recupera el valor introducido por el usuario después de que se hubiera deshecho
     */
    @Override
    public void rehacer() {
        Recuerdo recuerdo = conserje.popRecuerdo();
        if (recuerdo != null){
            Originador originador = new Originador();
            originador.setPedido(pedido);
            conserje.pushRecuerdo(originador.crearRecuerdo());
            pedido.setListaArticulos(recuerdo.getPedido().getListaArticulos().toString()); 
        }
    }
}
