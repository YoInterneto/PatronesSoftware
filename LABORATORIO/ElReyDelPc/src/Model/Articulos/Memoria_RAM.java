
package Model.Articulos;

/**
 * Objeto RAM.
 * 
 */
public class Memoria_RAM extends Articulo{
    
    private String PN;

    public Memoria_RAM() {
        super();
    }

    /**
     * Obtiene el P/N de la RAM
     *
     * @return Devuelve un String que representa el P/N de la RAM
     */
    public String getPN() {
        return PN;
    }

    /**
     * Establece un nuevo P/N de la RAM
     *
     * @param PN String
     */
    public void setPN(String PN) {
        this.PN = PN;
    }
    
    
    
}
