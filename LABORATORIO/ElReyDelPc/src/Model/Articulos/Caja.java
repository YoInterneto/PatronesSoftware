
package Model.Articulos;

/**
 * Objeto caja.
 * 
 */
public class Caja extends Articulo{
    
    private boolean Cristal;

    public Caja() {
        super();
    }

    /**
     * Obtiene si la caja tiene cristal (true) o no (false)
     *
     * @return boolean
     */
    public boolean isCristal() {
        return Cristal;
    }

    /**
     * Establece si la caja tiene cristal o no
     *
     * @param Cristal
     */
    public void setCristal(boolean Cristal) {
        this.Cristal = Cristal;
    }
    
    
    
}
