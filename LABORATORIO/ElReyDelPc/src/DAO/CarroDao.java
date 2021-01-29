
package DAO;

import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.Conexion;
import SingletonLog.Log;


public class CarroDao {
    
    private Connection conexion;
    /**
     * Realiza una consulta en la base de
     * datos y devuelve todos los datos correspondientes.
     *
     * @return Devuelve una lista de objetos de tipo Grafica
     */
    public String getArticulosCarro(String email) {
        
        String cesta = "";
        
            try {
                conexion = Conexion.getConexion();
                Log.logBd.info("Realizada conexion - ggetAllGraficaes()");
                Statement s = conexion.createStatement();
                ResultSet rs = s.executeQuery("select * from Carrito where email_cliente='"+email+"';");
                Log.logBd.info("CONSULTA getAllGraficaes");
                
                if (rs.next()) {                	
                    cesta = rs.getString("datosArticulos");
                }               
            } catch (SQLException error) {
                Log.logBd.error("ERROR SQL en getAllGraficaes(): " + error);
                Log.logBd.error("                   SQL State - " + error.getSQLState());
                Log.logBd.error("                   ErrorCode - " + error.getErrorCode());
            }                   
      
        Log.logBd.info("Consulta realizada con éxito - getAllGraficaes()");
       return cesta;
    }  
    
    public void actualizaCarro(String email,String nuevoCarro){
        
        Log.logBd.info("CONSULTA actualizaCarro");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - actualizaCarro()");
            Statement s = conexion.createStatement();
            s.executeUpdate("UPDATE carrito SET datosArticulos='"+ nuevoCarro +"' where email_cliente='"+email+"';");
            Log.logBd.info("Realizada consulta - actualizaCarro()");
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en editarArticulo(): " + error);
            Log.logBd.error("                   SQL State - " + error.getSQLState());
            Log.logBd.error("                   ErrorCode - " + error.getErrorCode());
        }
        
        
    }
    
}
