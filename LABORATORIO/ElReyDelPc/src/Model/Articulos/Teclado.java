
package Model.Articulos;

/**
 * Objeto teclado.
 * 
 */
public class Teclado extends Articulo{
    
    private String Tipo;

    public Teclado() {
        super();
    }

    /**
     * Obtiene el Tipo de teclado
     *
     * @return Devuelve un String que representa el tipo de teclado
     */
    public String getTipo() {
        return Tipo;
    }

    /**
     * Establece un nuevo tipo de teclado
     *
     * @param Tipo String
     */
    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }
    
}
