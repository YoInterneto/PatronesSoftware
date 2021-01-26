
package DAO;

import Model.Articulos.Placa_base;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.Conexion;
import util.Log;


public class Placa_baseDao {
    
    private Connection conexion;
    
    /**
     * Realiza una consulta en la base de
     * datos y devuelve todos los datos correspondientes.
     *
     * @return Devuelve una lista de objetos de tipo Placa base
     */
    public ArrayList<Placa_base> getAllPlacas() {
        
        ArrayList<Placa_base> placadb = new ArrayList<Placa_base>();
        
            try {
                conexion = Conexion.getConexion();
                Log.logBd.info("Realizada conexion - ggetAllPlaca_basees()");
                Statement s = conexion.createStatement();
                ResultSet rs = s.executeQuery("select * from Placa_base;");
                Log.logBd.info("CONSULTA getAllPlaca_basees");
                
                while (rs.next()) {                	
                	
                    Placa_base placa = new Placa_base();
                  
                    placa.setCodigo_ref(rs.getInt("Codigo_ref"));
                    placa.setDescripcion(rs.getString("Descripcion"));
                    placa.setModelo(rs.getString("Modelo"));
                    placa.setPrecio(rs.getFloat("Precio"));
                    placa.setRutaImagen(rs.getString("RutaImagen"));
                    placa.setSocket(rs.getString("Socket"));
                    placa.setStock(rs.getInt("Stock"));
                    
                    placadb.add(placa);
                }               
            } catch (SQLException error) {
                Log.logBd.error("ERROR SQL en getAllPlaca_basees(): " + error);
                Log.logBd.error("                   SQL State - " + error.getSQLState());
                Log.logBd.error("                   ErrorCode - " + error.getErrorCode());
            }                   
      
        Log.logBd.info("Consulta realizada con éxito - getAllPlaca_basees()");
       return placadb;
    }
    
    /**
     * Realiza una consulta en la base de datos para añadir
     * un nuevo artículo placa base
     *
     * @param modelo
     * @param codigoReferencia
     * @param precio
     * @param descripcion
     * @param stock
     * @param rutaImagen
     * @param idTienda
     * @param socket
     * @return Devuelve un boolean para saber si se ha insertado
     */
    public boolean anadirPlacaBase(String modelo, int codigoReferencia, float precio, String descripcion, int stock, String rutaImagen, int idTienda, String socket){
        boolean hecho = false;
        try {
            Log.logBd.info("CONSULTA AnadirPlacaBase");
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - anadirPlacaBase()");
            Statement s = conexion.createStatement();
            int codigo = s.executeUpdate("INSERT into placa_base VALUES('"+ modelo +"', "+ codigoReferencia +", "+ precio +", '"+ descripcion +"', "+ 
                    stock +", '"+ rutaImagen +"', "+ idTienda +", '"+ socket +"');");          
        
            if(codigo>0){
                hecho = true;
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en anadirPlacaBase(): " + error);
            Log.logBd.error("                    SQL State - " + error.getSQLState());
            Log.logBd.error("                    ErrorCode - " + error.getErrorCode());
        }
        
        return hecho;
    }
}
