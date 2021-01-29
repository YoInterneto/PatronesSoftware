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
import ProxyLogin.Proxy;
import ProxyLogin.Servidor;
import ProxyLogin.ServidorLogin;
import Views.InicioCliente;
import Views.InicioEmpleado;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import util.Log;

/**
 *
 * @author Alberto
 */
public class LoginController implements ActionListener{
    
    private final Login login;
    private Cliente cliente;
    private Empleado empleado;
    
    private final UsuarioDao consulta;
    
    public LoginController(Login loginVista){
        this.login = loginVista;
        this.consulta = new UsuarioDao();
    }
    
    public void iniciar(){
        login.setTitle("INICIO SESION");
        login.setSize(635, 432);
        login.setLocationRelativeTo(null);
        login.setResizable(false);
        login.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        login.panelIniciarSesion.setVisible(true);
        login.panelRegistro.setVisible(false);
        
        //Evento para boton de iniciar sesion
        this.login.iniciarSesion.addActionListener(this);
        
        //Evento para boton de borrar
        this.login.borrar.addActionListener(this);
        
        //Evento para boton de registrarse
        this.login.btnDarAlta.addActionListener(this);
        
        //Boton para crear una cuenta
        this.login.btnRegistro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                login.panelIniciarSesion.setVisible(false);
                login.panelRegistro.setVisible(true);
                
                login.tipoVentana.setText("CREAR CUENTA");
            }
        });
        
        //Boton para volver a la ventana de iniciar sesion
        this.login.btnVolver.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                login.panelIniciarSesion.setVisible(true);
                login.panelRegistro.setVisible(false);
                
                login.tipoVentana.setText("INICIO SESION");
            }
        });
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
    
    public void reset(){
        login.contrasenna.setText(null);
        login.usuario.setText(null);
    }
    
    @Override
    public void actionPerformed(ActionEvent boton){
        try{
            if(boton.getSource() == login.iniciarSesion){
                login.iniciarSesion.setBackground(new java.awt.Color(255, 153, 0));
                login.iniciarSesion.setForeground(new java.awt.Color(51, 51, 51));
                Log.log.info("Vista Login - evento iniciarSesion");

                if(comprobarFormulario()){
                    String usuario = login.usuario.getText();
                    char[] valorContrasenna = login.contrasenna.getPassword();
                    String contrasenna = new String(valorContrasenna);
                    
                    Servidor proxy = new Proxy(new ServidorLogin("ServidorLogin"));
                    
                    if(proxy.esCliente(usuario, contrasenna)){
                        Log.log.info("TIPO USUARIO: Cliente");
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
                    else if(proxy.esEmpleado(usuario, contrasenna)){
                        Log.log.info("TIPO USUARIO: Empleado");
                        empleado = consulta.getEmpleado(usuario);
                        Log.log.info("Usuario  "+ empleado.toString());

                        //Creamos la siguiente vista y su controlador
                        InicioEmpleado inicioVista = new InicioEmpleado();
                        EmpleadoController inicio = new EmpleadoController(inicioVista, empleado);
                        //Iniciamos la nueva vista y cerramos la anterior
                        inicio.iniciar();
                        login.setVisible(false);
                        inicioVista.setVisible(true);
                    }
                    else if(proxy.esAdmin(usuario, contrasenna)){
                        //TODO - Algo con admin
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "ERROR: Usuario no registrado");
                        login.iniciarSesion.setBackground(new java.awt.Color(51, 51, 51));
                        login.iniciarSesion.setForeground(new java.awt.Color(255, 153, 0));
                    }
                }
                else{
                    login.iniciarSesion.setBackground(new java.awt.Color(51, 51, 51));
                    login.iniciarSesion.setForeground(new java.awt.Color(255, 153, 0));
                }
            }
            else if(boton.getSource() == login.borrar){
                Log.log.info("Vista Login - evento borrar");
                login.usuario.setText("email@email.com");
                login.contrasenna.setText("pass1");
            }
            else if(boton.getSource() == login.btnDarAlta){
                if(comprobarFormularioRegistro()){
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
            else{
                Log.log.error("ERROR: Boton no encontrado");
            }
        }
        catch(NumberFormatException error){
            JOptionPane.showMessageDialog(null, "ERROR: "+ error.getMessage() +"\nIntroduzca de nuevo los datos.");
        }
    }
}
