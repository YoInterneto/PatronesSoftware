
package Model.Articulos;

public class PcTorre extends Articulo{
    
    private String Nombre;
    private boolean creado;

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

    /**
     * Get the value of creadoo
     *
     * @return the value of creadoo
     */
    public boolean isCreado() {
        return creado;
    }

    /**
     * Set the value of creadoo
     *
     * @param creado new value of creado
     */
    public void setCreado(boolean creado) {
        this.creado = creado;
    }

    
    @Override
    public String toString() {
        return "PcTorre{" + "Nombre=" + Nombre + "Descripcion="+super.getDescripcion()+'}';
    }
    
    
    
}
