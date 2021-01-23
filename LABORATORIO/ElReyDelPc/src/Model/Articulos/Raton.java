
package Model.Articulos;

public class Raton extends Articulo{
    
    private int Peso;

    public Raton() {
        super();
    }

    /**
     * Obtiene el peso del raton
     *
     * @return Devuelve un integer que representa el peso del raton
     */
    public int getPeso() {
        return Peso;
    }

    /**
     * Establece un nuevo Peso del raton
     *
     * @param Peso int
     */
    public void setPeso(int Peso) {
        this.Peso = Peso;
    }
    
    
    
}
