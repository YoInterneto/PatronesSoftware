
package Facade;

import DAO.UsuarioDao;
import Views.Login;
import javax.swing.JOptionPane;

/**
 * Clase que tiene la funcionalidad para el registro de nuevos clientes.
 * 
 */
public class RegistrarUsuario {
    
    private Login login;
    
    private final UsuarioDao consulta;
    
    public RegistrarUsuario(Login loginVista){
        this.login = loginVista;
        this.consulta = new UsuarioDao();
    }
    
    /**
     * Crea al nuevo usuario.
     * 
     */
    public void nuevoUsuario(){
        char[] valorContrasenna = login.contrasenaRegistro.getPassword();
        String contrasenna = new String(valorContrasenna);
        String nombre = login.nombreRegistro.getText();
        String apellido = login.apellidoRegistro.getText();
        String correo = login.correoRegistro.getText();
        String direccion = login.direccionRegistro.getText();
        int telefono = Integer.parseInt(login.telefonoRegistro.getText());
        int idTienda = 0;
        String tarjeta = login.tarjetaRegistro.getText();

        boolean exito = consulta.darAltaUsuario(nombre, apellido, correo, direccion, telefono, contrasenna, idTienda, tarjeta);

        if(exito){
            JOptionPane.showMessageDialog(null, "Se ha insertado el usuario "+ correo +" con éxito.");

            login.panelRegistro.setVisible(false);
            login.panelIniciarSesion.setVisible(true);
        }
        else{
            JOptionPane.showMessageDialog(null, "ERROR: No se ha podido insertar al usuario.");
        }
    }
}
