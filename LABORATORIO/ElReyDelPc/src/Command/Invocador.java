package Command;

/**
 * El que llama al método ejecutar del objeto Comando.
 * 
 */
public class Invocador {
    
    //Comando al que le pediremos que ejecute la petición.
    private ComandoDeshacer comando;

    /**
     * Establece el comando.
     * 
     * @param comando 
     */
    public void setComando(ComandoDeshacer comando) {
        this.comando = comando;
    }

    /**
     * Ejecuta el comando. 
     * 
     * @param idPedido
     */
    public void ejecutaComando(int idPedido) {
        comando.ejecutar(idPedido);
    }

    /**
     * Deshace el comando.
     */
    public void deshacerComando() {
        comando.deshacer();
    }
}
