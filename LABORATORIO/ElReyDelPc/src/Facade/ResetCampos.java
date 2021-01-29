/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Views.Login;

/**
 *
 * @author Alberto
 */
public class ResetCampos {
    private Login login;
    
    public ResetCampos(Login loginVista){
        this.login = loginVista;
    }
    
    public void resetCamposLogin(){
        login.contrasenna.setText(null);
        login.usuario.setText(null);
    }
    
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
