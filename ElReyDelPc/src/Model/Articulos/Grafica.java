
package Model.Articulos;

/**
 * Objeto gráfica.
 * 
 */
public class Grafica extends Articulo{
    
    private int Generacion;

    public Grafica() {
        super();
    }

    /**
     * Obtiene la generacion de la grafica
     *
     * @return Devuelve un Integer que representa la generacion a 
     * la que pertenece la grafica
     */
    public int getGeneracion() {
        return Generacion;
    }

    /**
     * Establece una nueva generacion de grafica
     *
     * @param Generacion int
     */
    public void setGeneracion(int Generacion) {
        this.Generacion = Generacion;
    }
    
    
    
}
