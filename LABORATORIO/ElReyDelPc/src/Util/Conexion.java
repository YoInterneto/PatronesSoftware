
package util;

import SingletonLog.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
    
    private static Connection conexion = null;
    
    public static Connection getConexion() {
        String driver = "org.postgresql.Driver";
        String nombreBd = "ElReyDelPc";
        String puerto = "5432";
        String user = "postgres";
        String password = "postgres";
        String url = "jdbc:postgresql://localhost:"+ puerto +"/"+ nombreBd;
        Log.logBd.info("Inicio de conexion en puerto["+ puerto + "]");
        if (conexion != null){
            return conexion;
        }
        else{
            try{
                Class.forName(driver);
                conexion = DriverManager.getConnection(url, user, password);
                Log.logBd.info("Conexion establecida en puerto["+ puerto +"]");
            }catch(ClassNotFoundException error){
                Log.logBd.error("ERROR Conexion: "+ error.getMessage());
            }catch(SQLException error){
                Log.logBd.error("ERROR SQL: "+ error.getMessage());
            }
            
            return conexion;
        }
    }
    
    public void desconectar(){
        try{
            Log.logBd.info("Cerrando la conexión con la Base de Datos...");
            if(conexion != null){
                conexion.close();
            }
            Log.logBd.info("Conexión cerrada con éxito");
        }catch(SQLException error){
            Log.logBd.error("ERROR SQL: "+ error);
        }
    }
}
