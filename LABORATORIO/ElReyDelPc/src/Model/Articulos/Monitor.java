package Model.Articulos;

/**
 * Objeto monitor.
 * 
 */
public class Monitor extends Articulo{
    
    private int Pulgadas;
    private String panel;
    private int hz;

    public Monitor() {
        super();
    }
    
    /**
     * Obtiene las pulgadas del monitor
     *
     * @return Devuelve un Integer que representa las pulgadas del monitor
     */
    public int getPulgadas() {
        return Pulgadas;
    }

    /**
     * Establece un nuevo valor de pulgadas del monitor
     *
     * @param Pulgadas int
     */
    public void setPulgadas(int Pulgadas) {
        this.Pulgadas = Pulgadas;
    }

    /**
     * Obtiene el tipo de panel del monitor
     *
     * @return Devuelve un String que representa el tipo de panel del monitor
     */
    public String getPanel() {
        return panel;
    }

    /**
     * Establece un nuevo tipo de panel del monitor
     *
     * @param panel String
     */
    public void setPanel(String panel) {
        this.panel = panel;
    }

    /**
     * Obtiene los hercios del monitor
     *
     * @return Devuelve un Integer que representa los hercios del monitor
     */
    public int getHz() {
        return hz;
    }

    /**
     * Establece un nuevo valor de Hertz al monitor 
     *
     * @param hz int 
     */
    public void setHz(int hz) {
        this.hz = hz;
    }
    
    
    
    
}
