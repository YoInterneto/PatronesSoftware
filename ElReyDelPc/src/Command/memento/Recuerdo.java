package Command.memento;

import StatePedido.Pedido;

/**
 * Almacena el estado interno de un objeto Originador.
 * 
 */
public class Recuerdo {

    // Pedido que se va a recordar.
    private Pedido pedido = null;

    
    public Recuerdo(Pedido pedido) {
        this.pedido = pedido;
    }

    /**
     * Devuelve el pedido que se va a recordar.
     * 
     * @return 
     */
    public Pedido getPedido() {
        return pedido;
    }

    /**
     * Establece el pedido que se va a recordar.
     * 
     * @param pedido 
     */
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
