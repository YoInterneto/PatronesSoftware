
package DAO;

import Model.Articulos.Monitor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.Conexion;
import SingletonLog.Log;

/**
 * DAO para las operaciones de datos de la tabla y objeto monitor.
 * 
 */
public class MonitorDao {
    
    private Connection conexion;
    
    /**
     * Dado el codigo de un monitor realiza una consulta en la base de
     * datos y devuelve todos los datos correspondientes a dicho monitor.
     *
     * @pamonitor codigo
     * @return Devuelve un objeto de tipo Monitor
     */
    public Monitor getMonitor(int codigo){
        Monitor monitor = new Monitor();
        Log.logBd.info("CONSULTA getMonitor");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - getMonitor()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from monitor where codigo_ref='" + codigo + "';");
            Log.logBd.info("Realizada consulta - getMonitor()");

            while (resultado.next()) {
                monitor.setCodigo_ref(codigo);
                monitor.setModelo(resultado.getString("Modelo"));
                monitor.setPrecio(Float.parseFloat(resultado.getString("Precio")));
                monitor.setDescripcion(resultado.getString("Descripcion"));
                monitor.setStock(Integer.parseInt(resultado.getString("Stock")));
                monitor.setRutaImagen(resultado.getString("rutaImagen"));
                
                monitor.setPanel(resultado.getString("Panel"));
                monitor.setPulgadas(Integer.parseInt(resultado.getString("Pulgadas")));
                monitor.setHz(Integer.parseInt(resultado.getString("Hz")));
 
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getMonitor: " + error);
            Log.logBd.error("                SQL State - " + error.getSQLState());
            Log.logBd.error("                ErrorCode - " + error.getErrorCode());
        }
        
        Log.logBd.info("Consulta realizada con éxito - getMonitor");
        return monitor;
    }
    /**
     * Realiza una consulta en la base de datos y devuelve todos los datos correspondientes sobre el/los monitores.
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
    
    /**
     * Realiza una consulta en la base de datos para añadir un nuevo artículo monitor.
     *
     * @param modelo
     * @param codigoReferencia
     * @param precio
     * @param descripcion
     * @param stock
     * @param rutaImagen
     * @param idTienda
     * @param pulgadas
     * @param panel
     * @param hz
     * @return Devuelve un boolean para saber si se ha insertado
     */
    public boolean anadirMonitor(String modelo, int codigoReferencia, float precio, String descripcion, int stock, String rutaImagen, int idTienda, int pulgadas, String panel, int hz){
        boolean hecho = false;
        try {
            Log.logBd.info("CONSULTA AnadirMonitor");
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - anadirMonitor()");
            Statement s = conexion.createStatement();
            int codigo = s.executeUpdate("INSERT into monitor VALUES('"+ modelo +"', "+ codigoReferencia +", "+ precio +", '"+ descripcion +"', "+ 
                    stock +", '"+ rutaImagen +"', "+ idTienda +", "+ pulgadas +", '"+ panel +"', "+ hz +");");          
        
            if(codigo>0){
                hecho = true;
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en anadirMonitor(): " + error);
            Log.logBd.error("                  SQL State - " + error.getSQLState());
            Log.logBd.error("                  ErrorCode - " + error.getErrorCode());
        }
        
        return hecho;
    }
}
