/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import DAO.UsuarioDao;
import Model.Usuario.Cliente;
import Model.Usuario.Empleado;
import Views.Login;
import javax.swing.JOptionPane;

/**
 *
 * @author Alberto
 */
public class RegistrarUsuario {
    
    private Login login;
    
    private final UsuarioDao consulta;
    
    public RegistrarUsuario(Login loginVista){
        this.login = loginVista;
        this.consulta = new UsuarioDao();
    }
    
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
