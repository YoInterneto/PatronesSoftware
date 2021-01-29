/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Controller.ClienteController;
import Controller.EmpleadoController;
import DAO.UsuarioDao;
import Model.Usuario.Cliente;
import Model.Usuario.Empleado;
import Views.InicioCliente;
import Views.InicioEmpleado;
import Views.Login;
import SingletonLog.Log;

/**
 *
 * @author Alberto
 */
public class IniciarSesion {
    
    private Login login;
    
    private Empleado empleado;
    private Cliente cliente;
    
    private final UsuarioDao consulta;
    
    public IniciarSesion(Login loginVista){
        this.login = loginVista;
        this.consulta = new UsuarioDao();
    }
    
    public void inicioCliente(String usuario){
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
    
    public void inicioEmpleado(String usuario){
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
}
