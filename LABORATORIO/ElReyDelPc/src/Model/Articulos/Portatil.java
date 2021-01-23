
package Model.Articulos;

public class Portatil extends Articulo{
    
    private int Peso;
    private String Nombre;

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
    public String getNombre() {
        return Nombre;
    }

    /**
     * Establece un nuevo Nombre del portatil
     *
     * @param Nombre String
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    
    
}
