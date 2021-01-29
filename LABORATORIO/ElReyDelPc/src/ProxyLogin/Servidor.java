
package ProxyLogin;

/**
 *
 * @author Alberto
 */
public interface Servidor {
    
    /**
     * Comprueba si el usuario es cliente de la aplicación
     *
     * @param correo
     * @param pass
     * @return Devuelve boolean si es o no cliente
     */
    public boolean esCliente(String correo, String pass);
    
    /**
     * Comprueba si el usuario es empleado de la aplicación
     *
     * @param correo
     * @param pass
     * @return Devuelve boolean si es o no empleado
     */
    public boolean esEmpleado(String correo, String pass);
    
    /**
     * Comprueba si el usuario es administrador de la aplicación
     *
     * @param correo
     * @param pass
     * @return Devuelve boolean si es o no administrador
     */
    public boolean esAdmin(String correo, String pass);
}
