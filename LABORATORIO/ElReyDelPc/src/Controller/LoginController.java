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
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    
    private final UsuarioDao consulta = new UsuarioDao();
    
    public LoginController(Login loginVista){
        this.login = loginVista;
    }
    
    public void iniciar(){
        login.setTitle("INICIO SESION");
        login.setSize(635, 432);
        login.setLocationRelativeTo(null);
        login.setResizable(false);
        
        //Evento para boton de iniciar sesion
        this.login.iniciarSesion.addActionListener(this);
        
        //Evento para boton de borrar
        this.login.borrar.addActionListener(this);
        
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
                
                login.panelRegistro.setVisible(false);
                login.panelIniciarSesion.setVisible(true);
                
                
                login.tipoVentana.setText("INICIO SESION");
            }
        });
        
        ImageIcon logo = new ImageIcon("/images/error.png");
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
            login.iniciarSesion.setBackground(new java.awt.Color(255, 153, 0));
            login.iniciarSesion.setForeground(new java.awt.Color(51, 51, 51));
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
                    EmpleadoController inicio = new EmpleadoController(inicioVista, empleado);
                    //Iniciamos la nueva vista y cerramos la anterior
                    inicio.iniciar();
                    login.setVisible(false);
                    inicioVista.setVisible(true);
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
        else{
            Log.log.error("ERROR: Boton no encontrado");
        }
    }
}
