
package Facade;

import Views.Login;

/**
 * Clase que da la funcionalidad a los botones de borrar en la interfaz de registro.
 * 
 */
public class ResetCampos {
    private Login login;
    
    public ResetCampos(Login loginVista){
        this.login = loginVista;
    }
    
    /**
     * Resetea los campos del formulario login.
     * 
     */
    public void resetCamposLogin(){
        login.contrasenna.setText(null);
        login.usuario.setText(null);
    }
    
    /**
     * Resetea los campos del formulario registro.
     * 
     */
    public void resetCamposRegistro(){
        login.nombreRegistro.setText(null);
        login.apellidoRegistro.setText(null);
        login.correoRegistro.setText(null);
        login.telefonoRegistro.setText(null);
        login.direccionRegistro.setText(null);
        login.tarjetaRegistro.setText(null);
        login.contrasenaRegistro.setText(null);
        login.contrasenaRepitaRegistro.setText(null);
    }
}
