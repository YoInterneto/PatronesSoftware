
package Model.Articulos;

public class Disco_duro extends Articulo{
    
    private String Tipo;

    public Disco_duro() {
        super();
    }

    /**
     * Obtiene el tipo de disco duro
     *
     * @return Devuelve un String que representa el tipo de disco duro
     */
    public String getTipo() {
        return Tipo;
    }

    /**
     * Establece un nuevo tipo de disco duro
     *
     * @param Tipo String
     */
    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }    
    
}
