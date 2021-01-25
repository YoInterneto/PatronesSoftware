
package DAO;

import Model.Articulos.Monitor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.Conexion;
import util.Log;


public class MonitorDao {
    
    private Connection conexion;
    
    /**
     * Realiza una consulta en la base de
     * datos y devuelve todos los datos correspondientes.
     *
     * @return Devuelve una lista de objetos de tipo Monitor
     */
    public ArrayList<Monitor> getAllMonitores() {
        
        ArrayList<Monitor> monitordb = new ArrayList<Monitor>();
        
            try {
                conexion = Conexion.getConexion();
                Log.logBd.info("Realizada conexion - ggetAllMonitores()");
                Statement s = conexion.createStatement();
                ResultSet rs = s.executeQuery("select * from monitor;");
                Log.logBd.info("CONSULTA getAllMonitores");
                
                while (rs.next()) {                	
                	
                    Monitor monitor = new Monitor();
                  
                    monitor.setCodigo_ref(rs.getInt("Codigo_ref"));
                    monitor.setDescripcion(rs.getString("Descripcion"));
                    monitor.setModelo(rs.getString("Modelo"));
                    monitor.setPrecio(rs.getFloat("Precio"));
                    monitor.setRutaImagen(rs.getString("RutaImagen"));
                    monitor.setPanel(rs.getString("Panel"));
                    monitor.setStock(rs.getInt("Stock"));
                    
                    monitordb.add(monitor);
                }               
            } catch (SQLException error) {
                Log.logBd.error("ERROR SQL en getAllMonitores(): " + error);
                Log.logBd.error("                   SQL State - " + error.getSQLState());
                Log.logBd.error("                   ErrorCode - " + error.getErrorCode());
            }                   
      
        Log.logBd.info("Consulta realizada con éxito - getAllMonitores()");
       return monitordb;
    }  
}
