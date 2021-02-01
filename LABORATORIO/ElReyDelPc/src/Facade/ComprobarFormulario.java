
package Facade;

import Views.Login;
import javax.swing.JOptionPane;

/**
 * Clase que tiene la funcionalidad para la comprobación de formularios.
 * 
 */
public class ComprobarFormulario {
    
    private Login login;
    
    public ComprobarFormulario(Login loginVista){
        this.login = loginVista;
    }
    
    /**
     * Comprueba el formulario del inicio de sesión.
     * 
     * @return 
     */
    public boolean comprobarFormulario(){
        boolean correcto = false;
        if(!login.usuario.getText().equalsIgnoreCase("")){
            if(login.contrasenna.getPassword().length != 0){
                correcto = true;
            }
            else{
                JOptionPane.showMessageDialog(null, "ERROR: Introduzca la contraseña");
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "ERROR: Introduzca el nombre de usuario");
        }
        
        return correcto;
    }
    
    /**
     * Comprueba el formulario del registro de un nuevo usuario.
     * 
     * @return 
     */
    public boolean comprobarFormularioRegistro(){
        boolean correcto = false;
        if(login.nombreRegistro.getText().equalsIgnoreCase("") || login.apellidoRegistro.getText().equalsIgnoreCase("")
                || login.telefonoRegistro.getText().equalsIgnoreCase("") || login.tarjetaRegistro.getText().equalsIgnoreCase("")
                || login.contrasenaRegistro.getPassword().length == 0 || login.contrasenaRepitaRegistro.getPassword().length == 0){
            JOptionPane.showMessageDialog(null, "ERROR: Rellene todos los campos");
        }
        else{
            char[] valorContrasenna = login.contrasenaRegistro.getPassword();
            String contrasenna = new String(valorContrasenna);
            char[] valorContrasennaRepite = login.contrasenaRepitaRegistro.getPassword();
            String contrasennaRepite = new String(valorContrasennaRepite);
            
            if(contrasenna.equals(contrasennaRepite)){
                correcto = true;
            }
            else{
                JOptionPane.showMessageDialog(null, "ERROR: Las contraseñas no coinciden");
            }
        }
        
        return correcto;
    }
}
