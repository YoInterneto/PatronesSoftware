
package Model.Usuario;

/**
 * Objeto usuario.
 * Clase padre de los demás usuarios de la aplicación.
 * 
 */
public class Usuario {
    
    private String Nombre;
    private String Apellido;
    private String Email;
    private String Direccion;
    private int Telefono;
    private String Pass;
    private int ID_Tienda;

    public Usuario() {
    }

    /**
     * Get the value of Nombre
     *
     * @return the value of Nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * Set the value of Nombre
     *
     * @param Nombre new value of Nombre
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    /**
     * Get the value of Apellido
     *
     * @return the value of Apellido
     */
    public String getApellido() {
        return Apellido;
    }

    /**
     * Set the value of Apellido
     *
     * @param Apellido new value of Apellido
     */
    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }
   
    /**
     * Get the value of Email
     *
     * @return the value of Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * Set the value of Email
     *
     * @param Email new value of Email
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }

    /**
     * Get the value of Direccion
     *
     * @return the value of Direccion
     */
    public String getDireccion() {
        return Direccion;
    }

    /**
     * Set the value of Direccion
     *
     * @param Direccion new value of Direccion
     */
    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }
    
    /**
     * Get the value of Telefono
     *
     * @return the value of Telefono
     */
    public int getTelefono() {
        return Telefono;
    }

    /**
     * Set the value of Telefono
     *
     * @param Telefono new value of Telefono
     */
    public void setTelefono(int Telefono) {
        this.Telefono = Telefono;
    }

    /**
     * Get the value of Pass
     *
     * @return the value of Pass
     */
    public String getPass() {
        return Pass;
    }

    /**
     * Set the value of Pass
     *
     * @param Pass new value of Pass
     */
    public void setPass(String Pass) {
        this.Pass = Pass;
    }

    /**
     * Get the value of ID_Tienda
     *
     * @return the value of ID_Tienda
     */
    public int getID_Tienda() {
        return ID_Tienda;
    }

    /**
     * Set the value of ID_Tienda
     *
     * @param ID_Tienda new value of ID_Tienda
     */
    public void setID_Tienda(int ID_Tienda) {
        this.ID_Tienda = ID_Tienda;
    }

    @Override
    public String toString() {
        return "Nombre=" + Nombre + ", Apellido=" + Apellido + ", Email=" + Email + ", Direccion=" + Direccion + ", Telefono=" + Telefono + ", Password=" + Pass + ", ID_Tienda=" + ID_Tienda ;
    }
}
