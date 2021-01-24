
package Model.Articulos;

public class Articulo {
    
    private String Modelo;
    private int Codigo_ref;
    private int Precio;
    private String Descripcion;
    private int Stock;
    private String rutaImagen;
    private int ID_Tienda;
    private int Id_Pedido;
    private String Fecha;

    public Articulo() {
    }

    /**
     * Obtiene el modelo del articulo
     *
     * @return Devuelve un String que representa al modelo del articulo
     */
    public String getModelo() {
        return Modelo;
    }

    /**
     * Establece un nuevo modelo de articulo
     *
     * @param modelo
     */
    public void setModelo(String Modelo) {
        this.Modelo = Modelo;
    }

    /**
     * Obtiene el codigo de referencia del articulo
     *
     * @return Devuelve un Integer del codigo de referencia del articulo
     */
    public int getCodigo_ref() {
        return Codigo_ref;
    }

    /**
     * Establece un nuevo codigo de referencia del articulo
     *
     * @param Codigo_ref int
     */
    public void setCodigo_ref(int Codigo_ref) {
        this.Codigo_ref = Codigo_ref;
    }

    /**
     * Obtiene el precio del articulo
     *
     * @return Devuelve un Integer con el precio del articulo
     */
    public int getPrecio() {
        return Precio;
    }

    /**
     * Establece un nuevo precio del articulo
     *
     * @param Precio int
     */
    public void setPrecio(int Precio) {
        this.Precio = Precio;
    }

    /**
     * Obtiene la descripcion del articulo
     *
     * @return Devuelve un String que representa la descripcion del articulo
     */
    public String getDescripcion() {
        return Descripcion;
    }

    /**
     * Establece una nueva descripcion del articulo
     *
     * @param Descripcion String
     */
    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    /**
     * Obtiene el stock del articulo
     *
     * @return Devuelve un Integer que representa el stock del articulo
     */
    public int getStock() {
        return Stock;
    }

    /**
     * Establece un nuevo stock del articulo
     *
     * @param Stock int
     */
    public void setStock(int Stock) {
        this.Stock = Stock;
    }

    /**
     * Get the value of rutaImagen
     *
     * @return the value of rutaImagen
     */
    public String getRutaImagen() {
        return rutaImagen;
    }

    /**
     * Set the value of rutaImagen
     *
     * @param rutaImagen new value of rutaImagen
     */
    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    /**
     * Obtiene el ID de la tienda del articulo
     *
     * @return Devuelve un Integer que representa el ID de la tienda del articulo
     */
    public int getID_Tienda() {
        return ID_Tienda;
    }

    /**
     * Establece un nuevo ID de la tienda del articulo
     *
     * @param ID_Tienda int
     */
    public void setID_Tienda(int ID_Tienda) {
        this.ID_Tienda = ID_Tienda;
    }

    /**
     * Obtiene el Id del pedido del articulo
     *
     * @return Devuelve un Integer que representa el ID del pedido del articulo
     */
    public int getId_Pedido() {
        return Id_Pedido;
    }

    /**
     * Establece un nuevo ID de pedido del articulo
     *
     * @param Id_Pedido int
     */
    public void setId_Pedido(int Id_Pedido) {
        this.Id_Pedido = Id_Pedido;
    }

    /**
     * Obtiene la fecha del pedido del articulo
     *
     * @return Devuelve un String que representa la fecha del pedido del articulo
     */
    public String getFecha() {
        return Fecha;
    }

    /**
     * Establece una nueva fecha de pedido del articulo
     *
     * @param Fecha String
     */
    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }
    
    
    
}
