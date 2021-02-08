
package StatePedido;

/**
 * Interfaz que define los métodos que dependen del estado del objeto.
 * Controla que no se puedan realizar cambios de estado dependiendo del estado
 * en el que esté el pedido.
 *
 */
public interface Estado {
    
    /**
     * Cambia el estado de un pedido, al estado que indique el usuario.
     *
     * @param pedido
     * @param siguienteEstado
     * @return 
     */
    public boolean cambiarEstado(Pedido pedido, String siguienteEstado);
    
    /**
     * Elimina un pedido.
     *
     * @param pedido
     * @return 
     */
    public boolean eliminar(Pedido pedido);
}
