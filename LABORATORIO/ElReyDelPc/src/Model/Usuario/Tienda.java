
package Model.Usuario;


public class Tienda {
    
    private int id_tienda;
    private String nombre;
    private String direccion;
    private int codigo_postal;
    private String cuidad;
    private String provincia;

    /**
     * Get the value of provincia
     *
     * @return the value of provincia
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Set the value of provincia
     *
     * @param provincia new value of provincia
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }


    /**
     * Get the value of cuidad
     *
     * @return the value of cuidad
     */
    public String getCuidad() {
        return cuidad;
    }

    /**
     * Set the value of cuidad
     *
     * @param cuidad new value of cuidad
     */
    public void setCuidad(String cuidad) {
        this.cuidad = cuidad;
    }


    /**
     * Get the value of codigo_postal
     *
     * @return the value of codigo_postal
     */
    public int getCodigo_postal() {
        return codigo_postal;
    }

    /**
     * Set the value of codigo_postal
     *
     * @param codigo_postal new value of codigo_postal
     */
    public void setCodigo_postal(int codigo_postal) {
        this.codigo_postal = codigo_postal;
    }


    /**
     * Get the value of direccion
     *
     * @return the value of direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Set the value of direccion
     *
     * @param direccion new value of direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    /**
     * Get the value of nombre
     *
     * @return the value of nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Set the value of nombre
     *
     * @param nombre new value of nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    /**
     * Get the value of id_tienda
     *
     * @return the value of id_tienda
     */
    public int getId_tienda() {
        return id_tienda;
    }

    /**
     * Set the value of id_tienda
     *
     * @param id_tienda new value of id_tienda
     */
    public void setId_tienda(int id_tienda) {
        this.id_tienda = id_tienda;
    }

}
