
package Model.Articulos;

/**
 * Objeto portátil.
 * 
 */
public class Portatil extends Articulo{
    
    private int Peso;
    private String panel;

    public Portatil() {
        super();
    }

    /**
     * Obtiene el peso del portatil
     *
     * @return Devuelve un Integer que representa el peso del portatil
     */
    public int getPeso() {
        return Peso;
    }

    /**
     * Establece un nuevo peso del portatil
     *
     * @param Peso int
     */
    public void setPeso(int Peso) {
        this.Peso = Peso;
    }

    /**
     * Obtiene el nombre del portatil
     *
     * @return Devuelve un String que representa el nombre del portatil
     */
    public String getPanel() {
        return panel;
    }

    /**
     * Establece un nuevo panel del portatil
     *
     * @param panel String
     */
    public void setPanel(String panel) {
        this.panel = panel;
    }
    
    
    
}
