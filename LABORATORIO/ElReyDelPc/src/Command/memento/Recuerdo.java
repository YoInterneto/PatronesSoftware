package Command.memento;

import StatePedido.Pedido;

/**
 * Almacena el estado interno de un objeto Originador.
 * @author Salvador Oton
 */
public class Recuerdo {

    // Reunión que se va a recordar.
    private Pedido pedido = null;

    /**
     * Constructor.
     * @param pedido Reunión que se va a recordar.
     */
    public Recuerdo(Pedido pedido) {
        this.pedido = pedido;
    }

    /**
     * Devuelve la reunion que se va a recordar.
     * @return Reunión que se va a recordar.
     */
    public Pedido getPedido() {
        return pedido;
    }

    /**
     * Establece la reunion que se va a recordar.
     * @param pedido Reunión que se va a recordar.
     */
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
