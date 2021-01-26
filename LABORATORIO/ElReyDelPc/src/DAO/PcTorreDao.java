
package DAO;

import Model.Articulos.PcTorre;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.Conexion;
import util.Log;


public class PcTorreDao {
    
    private Connection conexion;
    /**
     * Realiza una consulta en la base de
     * datos y devuelve todos los datos correspondientes.
     *
     * @return Devuelve una lista de objetos de tipo PcTorre
     */
    public ArrayList<PcTorre> getAllPcTorre() {
        
        ArrayList<PcTorre> portatildb = new ArrayList<PcTorre>();
        
            try {
                conexion = Conexion.getConexion();
                Log.logBd.info("Realizada conexion - getAllPcTorre");
                Statement s = conexion.createStatement();
                ResultSet rs = s.executeQuery("select * from pctorre;");
                Log.logBd.info("CONSULTA getAllPcTorre");
                
                while (rs.next()) {                	
                	
                    PcTorre portatil = new PcTorre();
                  
                    portatil.setCodigo_ref(rs.getInt("Codigo_ref"));
                    portatil.setDescripcion(rs.getString("Descripcion"));
                    portatil.setModelo(rs.getString("Modelo"));
                    portatil.setPrecio(rs.getFloat("Precio"));
                    portatil.setRutaImagen(rs.getString("RutaImagen"));
                    portatil.setNombre(rs.getString("Nombre"));
                    portatil.setStock(rs.getInt("Stock"));
                    
                    portatildb.add(portatil);
                }               
            } catch (SQLException error) {
                Log.logBd.error("ERROR SQL en getAllPcTorre: " + error);
                Log.logBd.error("                   SQL State - " + error.getSQLState());
                Log.logBd.error("                   ErrorCode - " + error.getErrorCode());
            }                   
      
        Log.logBd.info("Consulta realizada con éxito - getAllPcTorre");
       return portatildb;
    }  
    
    
}
