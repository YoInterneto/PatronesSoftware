/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import java.awt.event.ActionListener;
import Views.Login;
import ProxyLogin.Proxy;
import ProxyLogin.Servidor;
import ProxyLogin.ServidorLogin;
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
public class FachadaLogin implements ActionListener{
    
    private final Login login;
    
    private final ComprobarFormulario formulario;
    private final IniciarSesion inicioSesion;
    private final RegistrarUsuario resgistro;
    private final ResetCampos reset;
    
    public FachadaLogin(Login loginVista){
        this.login = loginVista;
        //Inicializamos las clases del Facade
        this.formulario = new ComprobarFormulario(loginVista);
        this.inicioSesion = new IniciarSesion(loginVista);
        this.resgistro = new RegistrarUsuario(loginVista);
        this.reset = new ResetCampos(loginVista);
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
        
        //Evento para boton de borrar campos del login
        this.login.borrarLogin.addActionListener(this);
        
        //Evento para boton de borrar campos del registro
        this.login.borrarRegistro.addActionListener(this);
        
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
                
                reset.resetCamposLogin();
            }
        });
    }
    
    @Override
    public void actionPerformed(ActionEvent boton){
        try{
            if(boton.getSource() == login.iniciarSesion){
                login.iniciarSesion.setBackground(new java.awt.Color(255, 153, 0));
                login.iniciarSesion.setForeground(new java.awt.Color(51, 51, 51));
                Log.log.info("Vista Login - evento iniciarSesion");

                if(formulario.comprobarFormulario()){
                    String usuario = login.usuario.getText();
                    char[] valorContrasenna = login.contrasenna.getPassword();
                    String contrasenna = new String(valorContrasenna);
                    
                    Servidor proxy = new Proxy(new ServidorLogin("ServidorLogin"));
                    
                    if(proxy.esCliente(usuario, contrasenna)){
                        inicioSesion.inicioCliente(usuario);
                    }
                    else if(proxy.esEmpleado(usuario, contrasenna)){
                        inicioSesion.inicioEmpleado(usuario);
                    }
                    else if(proxy.esAdmin(usuario, contrasenna)){
                        
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
            else if(boton.getSource() == login.borrarLogin){
                reset.resetCamposLogin();
                Log.log.info("Vista Login - evento borrarLogin");
                login.usuario.setText("email@email.com");
                login.contrasenna.setText("pass1");
            }
            else if(boton.getSource() == login.borrarRegistro){
                reset.resetCamposRegistro();
                Log.log.info("Vista Login - evento borrarRegistro");
            }
            else if(boton.getSource() == login.btnDarAlta){
                if(formulario.comprobarFormularioRegistro()){
                    resgistro.nuevoUsuario();       
                    reset.resetCamposLogin();
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
