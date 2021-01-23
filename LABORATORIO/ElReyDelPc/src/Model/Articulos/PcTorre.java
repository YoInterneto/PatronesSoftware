
package Model.Articulos;

public class PcTorre extends Articulo{
    
    private String Nombre;

    public PcTorre() {
        super();
    }

    /**
     * Obtiene el nombre del ordenador sobremesa
     *
     * @return Devuelve un String que representa el nombre de la torre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * Establece un nuevo Nombre del ordenador sobremesa
     *
     * @param Nombre String
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
}
