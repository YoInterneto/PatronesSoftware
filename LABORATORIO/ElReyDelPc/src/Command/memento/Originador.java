package Command.memento;

import StatePedido.Pedido;

/**
 * Clase que crea el objeto recuerdo y lo utiliza para recuperar su estado.
 * Crea un Recuerdo que contiene una instantánea de su estado interno actual. 
 * Usa el Recuerdo para restaurar su estado interno.
 * 
 */
public class Originador {

    // Referencia al pedido.
    private Pedido pedido;

    /**
     * Establece el pedido.
     * 
     * @param pedido 
     */
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    /**
     * Asigna un recuerdo al pedido.
     * 
     * @param recuerdo 
     */
    public void setRecuerdo(Recuerdo recuerdo) {
        pedido = recuerdo.getPedido();
    }

    /**
     * Devuelve un recuerdo creado a partir del pedido actual.
     * 
     * @return 
     */
    public Recuerdo crearRecuerdo() {
        return new Recuerdo(new Pedido(pedido));
    }
}
