package Command;

import StatePedido.Pedido;

/**
 * Interfaz comando. 
 * Modela el comportamiento genérico de los comandos.
 * 
 */
public interface Comando {

    /**
     * Establece el pedido.
     * 
     * @param pedido 
     */
    public void setPedido(Pedido pedido);

    /**
     * Devuelve el pedido.
     * 
     * @return 
     */
    public Pedido getPedido();

    /**
     * Método con las acciones a realizar. 
     * 
     * @param idPedido
     */
    public void ejecutar(int idPedido);
}
