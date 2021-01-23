
package DAO;

import Model.Usuario.Cliente;
import Model.Usuario.Empleado;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Conexion;
import util.Log;


public class UsuarioDao {
    
    private Connection conexion;
    
    /**
     * Identifica qué usuario inicia sesión y devuelve su tipo
     *
     * @param correo
     * @param contrasenna
     * @return Devuelve un String que representa el tipo de usuario que inicia
     * sesión (cliente o empleado)
     */
    public String getTipoUsuario(String correo, String contrasenna) {
        String tipo = "";
        Log.logBd.info("CONSULTA GetTipoUsuario");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - getTipoUsuario()");
            Statement s = conexion.createStatement();
            ResultSet resultadoEmpleado = s.executeQuery("select * from empleado where email='" + correo + "';");
            Log.logBd.info("Realizada consulta - getTipoUsuario()");
            if (resultadoEmpleado.next()) {
                if (resultadoEmpleado.getString("pass").equalsIgnoreCase(contrasenna)) {
                    tipo = "empleado";
                } else {
                    tipo = "error";
                }
            } else {
                ResultSet resultadoUsuario = s.executeQuery("select * from cliente where email='" + correo + "';");  //Si no es ni usuario ni empleado es error

                if (resultadoUsuario.next()) {
                    if (resultadoUsuario.getString("pass").equalsIgnoreCase(contrasenna)) {
                        tipo = "cliente";
                    } else {
                        tipo = "error";
                    }
                } else {
                    tipo = "error";
                }
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getTipoUsuario(): " + error);
            Log.logBd.error("                   SQL State - " + error.getSQLState());
            Log.logBd.error("                   ErrorCode - " + error.getErrorCode());

            tipo = "error";
        }
        
        Log.logBd.info("Consulta realizada con éxito - getTipoUsuario()");
        return tipo;
    }
    
    /**
     * Dado el correo de un empleado realiza una consulta en la base de
     * datos y devuelve todos los datos correspondientes a dicho empleado.
     *
     * @param correo
     * @return Devuelve un objeto de tipo Empleado
     */
    public Empleado getEmpleado(String correo){
        Empleado empleado = new Empleado();
        Log.logBd.info("CONSULTA GetEmpleado");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - getEmpleado()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from empleado where email='" + correo + "';");
            Log.logBd.info("Realizada consulta - getEmpleado()");

            while (resultado.next()) {
                empleado.setEmail(correo);
                empleado.setNombre(resultado.getString("nombre"));
                empleado.setApellido(resultado.getString("apellido"));
                empleado.setDireccion(resultado.getString("direccion"));
                empleado.setTelefono(resultado.getInt("telefono"));
                empleado.setPass(resultado.getString("pass"));
                empleado.setID_Tienda(resultado.getInt("id_tienda"));
                empleado.setDNI(resultado.getString("dni"));
                empleado.setCargo(resultado.getString("cargo"));
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getEmpleado(): " + error);
            Log.logBd.error("                SQL State - " + error.getSQLState());
            Log.logBd.error("                ErrorCode - " + error.getErrorCode());
        }
        
        Log.logBd.info("Consulta realizada con éxito - getEmpleado()");
        return empleado;
    }
    
    /**
     * Dado el correo de un cliente realiza una consulta en la base de datos y
     * nos devuelve todos los datos correspondientes a dicho cliente
     *
     * @param correo
     * @return Devuelve un objeto de tipo Cliente
     */
    public Cliente getCliente(String correo){
        Cliente cliente = new Cliente();
        Log.logBd.info("CONSULTA getCliente");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - getCliente()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from cliente where email='" + correo + "';");
            Log.logBd.info("Realizada consulta - getCliente()");

            while (resultado.next()) {
                cliente.setEmail(correo);
                cliente.setNombre(resultado.getString("nombre"));
                cliente.setApellido(resultado.getString("apellido"));
                cliente.setDireccion(resultado.getString("direccion"));
                cliente.setTelefono(resultado.getInt("telefono"));
                cliente.setPass(resultado.getString("pass"));
                cliente.setID_Tienda(resultado.getInt("id_tienda"));
                cliente.setTarjeta(resultado.getString("tarjeta"));
            }
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getCliente(): " + error);
            Log.logBd.error("               SQL State - " + error.getSQLState());
            Log.logBd.error("               ErrorCode - " + error.getErrorCode());
        }

        Log.logBd.info("Consulta realizada con éxito - getCliente()");
        return cliente;
    }
}
