
package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                if (resultadoEmpleado.getString("password").equalsIgnoreCase(contrasenna)) {
                    tipo = "empleado";
                } else {
                    tipo = "error";
                }
            } else {
                ResultSet resultadoUsuario = s.executeQuery("select * from cliente where email='" + correo + "';");  //Si no es ni usuario ni empleado es error

                if (resultadoUsuario.next()) {
                    if (resultadoUsuario.getString("password").equalsIgnoreCase(contrasenna)) {
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
}
