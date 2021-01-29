
package ProxyLogin;

/**
 *
 * @author Alberto
 */
public class Proxy implements Servidor{
    
    private Servidor servidor;
    
    public Proxy(Servidor serv){
        this.servidor = serv;
    }

    /**
     * Retorna el servidor real
     *
     * @return servidor real
     */
    public Servidor getServidor() {
        return servidor;
    }

    /**
     * Establece el servidor real
     *
     * @param servidor servidor real
     */
    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }
    
    @Override
    public boolean esCliente(String correo, String pass) {
        return servidor.esCliente(correo, pass);
    }

    @Override
    public boolean esEmpleado(String correo, String pass) {
        return servidor.esEmpleado(correo, pass);
    }

    @Override
    public boolean esAdmin(String correo, String pass) {
        return servidor.esAdmin(correo, pass);
    }
    
}
