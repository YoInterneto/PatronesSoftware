
package DAO;

import Model.Usuario.Cliente;
import Model.Usuario.Empleado;
import Model.Usuario.Tienda;
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
    
    /**
     * Dado el id de una tienda realiza una consulta a la base de datos y
     * nos devuelve toda la informaciçon sobre esa tienda
     *
     * @param idTienda 
     * @return Devuelve un objeto de tipo Tienda
     */
    public Tienda getTienda(int idTienda){
        Tienda tienda = new Tienda();
        Log.logBd.info("CONSULTA getTienda");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - getTienda()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from tienda where id='" + idTienda + "';");
            Log.logBd.info("Realizada consulta - getTienda()");

            while (resultado.next()) {
                tienda.setId_tienda(idTienda);
                tienda.setNombre(resultado.getString("nombre"));
                tienda.setDireccion(resultado.getString("direccion"));
                tienda.setCodigo_postal(resultado.getInt("cp"));
                tienda.setCuidad(resultado.getString("ciudad"));
                tienda.setProvincia(resultado.getString("provincia"));
            }
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getTienda(): " + error);
            Log.logBd.error("              SQL State - " + error.getSQLState());
            Log.logBd.error("              ErrorCode - " + error.getErrorCode());
        }

        Log.logBd.info("Consulta realizada con éxito - getTienda()");
        return tienda;
    }
    
    /**
     * Dado los nuevos datos que quiere cambiar el cliente de su perfil
     * se realiza una actualización en la base de datos para ese cliente
     *
     * @param correo 
     * @param nombre 
     * @param apellido 
     * @param direccion 
     * @param telefono 
     * @return Devuelve un boolean para saber si se ha realizado o no la consulta
     */
    public boolean editarUsuario(String correo, String nombre, String apellido, String direccion, int telefono){
        boolean hecho = false;
        Log.logBd.info("CONSULTA EditarUsuario");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - editarUsuario()");
            Statement s = conexion.createStatement();
            int codigo = s.executeUpdate("UPDATE empleado SET nombre='"+ nombre +"', apellido='"+ apellido +"', direccion='"
                    + direccion +"', telefono="+ telefono +" WHERE email='"+ correo +"';");
            Log.logBd.info("Realizada consulta - editarUsuario()");
            
            if(codigo > 0){
                hecho = true;
                Log.logBd.info("Consulta realizada con éxito - editarUsuario()");
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en editarUsuario(): " + error);
            Log.logBd.error("                  SQL State - " + error.getSQLState());
            Log.logBd.error("                  ErrorCode - " + error.getErrorCode());
        }
        
        return hecho;
    }
    
    /**
     * Dado los nuevos datos que quiere cambiar el cliente de su perfil
     * se realiza una actualización en la base de datos para ese cliente
     * 
     * @param correo
     * @param nombre
     * @param apellido
     * @param direccion
     * @param telefono
     * @param pass
     * @return Devuelve un boolean para saber si se ha realizado o no la consulta
     */
    public boolean editarUsuarioPass(String correo, String nombre, String apellido, String direccion, int telefono, String pass){
        boolean hecho = false;
        Log.logBd.info("CONSULTA EditarUsuarioPass");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - editarUsuarioPass()");
            Statement s = conexion.createStatement();
            int codigo = s.executeUpdate("UPDATE empleado SET nombre='"+ nombre +"', apellido='"+ apellido +"', direccion='"
                    + direccion +"', telefono="+ telefono +", pass='"+ pass +"' where email='"+ correo +"';");
            Log.logBd.info("Realizada consulta - editarUsuarioPass()");
            
            if(codigo > 0){
                hecho = true;
                Log.logBd.info("Consulta realizada con éxito - editarUsuarioPass()");
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en editarUsuarioPass(): " + error);
            Log.logBd.error("                      SQL State - " + error.getSQLState());
            Log.logBd.error("                      ErrorCode - " + error.getErrorCode());
        }

        return hecho;
    }
}
