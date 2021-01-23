/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UsuarioDao;
import java.awt.event.ActionListener;
import Views.Login;
import Model.Usuario.Cliente;
import Model.Usuario.Empleado;
import Views.InicioCliente;
import Views.InicioEmpleado;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import util.Log;

/**
 *
 * @author Alberto
 */
public class LoginController implements ActionListener{
    
    private Login login;
    private Cliente cliente;
    private Empleado empleado;
    
    private UsuarioDao consulta = new UsuarioDao();
    
    public LoginController(Login loginVista){
        this.login = loginVista;
        this.login.iniciarSesion.addActionListener(this);
        this.login.borrar.addActionListener(this);
    }
    
    public void iniciar(){
        login.setTitle("INICIO SESION");
        login.setLocationRelativeTo(null);
        login.setResizable(false);
        
        ImageIcon logo = new ImageIcon("./images/logo.png");
        login.setIconImage(logo.getImage());
    }
    
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
    
    public void reset(){
        login.contrasenna.setText(null);
        login.usuario.setText(null);
    }
    
    @Override
    public void actionPerformed(ActionEvent boton){
        if(boton.getSource() == login.iniciarSesion){
            Log.log.info("Vista Login - evento iniciarSesion");
            
            if(comprobarFormulario()){
                String usuario = login.usuario.getText();
                char[] valorContrasenna = login.contrasenna.getPassword();
                String contrasenna = new String(valorContrasenna);
                
                Log.log.info("USUARIO: "+ usuario +" - CONTRASEÑA: "+ contrasenna);
                String tipoUsuario = consulta.getTipoUsuario(usuario, contrasenna);

                if(tipoUsuario.equalsIgnoreCase("cliente")){
                    Log.log.info("TIPO USUARIO: "+ tipoUsuario);
                    cliente = consulta.getCliente(usuario);
                    Log.log.info("Usuario  "+ cliente.toString());
                    
                    //Creamos la siguiente vista y su controlador
                    InicioCliente inicioVista = new InicioCliente();
                    ClienteController inicio = new ClienteController(inicioVista, cliente);
                    //Iniciamos la nueva vista y cerramos la anterior
                    inicio.iniciar();
                    login.setVisible(false);
                    inicioVista.setVisible(true);
                }
                else if(tipoUsuario.equalsIgnoreCase("empleado")){
                    Log.log.info("TIPO USUARIO: "+ tipoUsuario);
                    empleado = consulta.getEmpleado(usuario);
                    Log.log.info("Usuario  "+ empleado.toString());
                    
                    //Creamos la siguiente vista y su controlador
                    InicioEmpleado inicioVista = new InicioEmpleado();
                    InicioEmpleadoController inicio = new InicioEmpleadoController(inicioVista, empleado);
                    //Iniciamos la nueva vista y cerramos la anterior
                    inicio.iniciar();
                    login.setVisible(false);
                    inicioVista.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null, "ERROR: Usuario no registrado");
                }
            }
        }
        else if(boton.getSource() == login.borrar){
            Log.log.info("Vista Login - evento borrar");
            reset();
        }
        else{
            Log.log.error("ERROR: Boton no encontrado");
        }
    }
}
