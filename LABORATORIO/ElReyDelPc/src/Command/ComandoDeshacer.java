package Command;

/**
 * Amplía la interfaz Comando añadiendo el método de deshacer
 * 
 */
public interface ComandoDeshacer extends Comando {

    /**
     * Deshace comando.
     */
    public void deshacer();
}
