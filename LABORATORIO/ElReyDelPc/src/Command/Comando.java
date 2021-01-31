package Command;

import StatePedido.Pedido;

/**
 * Interfaz comando. Modela el comportamiento genérico de los comandos.
 * @author Salvador Oton
 */
public interface Comando {

    /**
     * Establece reunión.
     * @param pedido 
     */
    public void setPedido(Pedido pedido);

    /**
     * Devuelve reunión.
     * @return 
     */
    public Pedido getPedido();

    /**
     * Método con las acciones a realizar.
     * @param param 
     */
    public void ejecutar(String param);
}
