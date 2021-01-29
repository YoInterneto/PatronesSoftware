
package ProxyLogin;

import DAO.UsuarioDao;

/**
 *
 * @author Alberto
 */
public class ServidorLogin implements Servidor{
    
    private String correoLogin;
    private String passLogin;
    
    private final String nombreServidor;
    
    private final UsuarioDao consultaUsuario;

    /**
     * Constructor.
     *
     * @param nombre Nombre del servidor.
     */
    public ServidorLogin(String nombre){
        this.nombreServidor = nombre;
        this.consultaUsuario = new UsuarioDao();
    }

    @Override
    public boolean esCliente(String correo, String pass) {
        this.correoLogin = correo;
        this.passLogin = pass;
        String tipo = consultaUsuario.getTipoUsuario(this.correoLogin, this.passLogin);
        return tipo.equalsIgnoreCase("cliente");
    }

    @Override
    public boolean esEmpleado(String correo, String pass) {
        this.correoLogin = correo;
        this.passLogin = pass;
        String tipo = consultaUsuario.getTipoUsuario(this.correoLogin, this.passLogin);
        return tipo.equalsIgnoreCase("empleado");
    }

    @Override
    public boolean esAdmin(String correo, String pass) {
        this.correoLogin = correo;
        this.passLogin = pass;
        String tipo = consultaUsuario.getTipoUsuario(this.correoLogin, this.passLogin);
        return tipo.equalsIgnoreCase("admin");
    }

}
