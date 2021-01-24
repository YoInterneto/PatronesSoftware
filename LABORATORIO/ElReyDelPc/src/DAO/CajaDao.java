
package DAO;

import Model.Articulos.Caja;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.Conexion;
import util.Log;


public class CajaDao {
    
    private Connection conexion;
    
    /**
     * Realiza una consulta en la base de
     * datos y devuelve todos los datos correspondientes.
     *
     * @return Devuelve una lista de objetos de tipo Caja
     */
    public ArrayList<Caja> getAllCajas() {
        
        ArrayList<Caja> cajadb = new ArrayList<Caja>();
        
            try {
                conexion = Conexion.getConexion();
                Log.logBd.info("Realizada conexion - ggetAllCajaes()");
                Statement s = conexion.createStatement();
                ResultSet rs = s.executeQuery("select * from caja;");
                Log.logBd.info("CONSULTA getAllCajaes");
                
                while (rs.next()) {                	
                	
                    Caja caja = new Caja();
                  
                    caja.setCodigo_ref(rs.getInt("Codigo_ref"));
                    caja.setDescripcion(rs.getString("Descripcion"));
                    caja.setModelo(rs.getString("Modelo"));
                    caja.setPrecio(rs.getInt("Precio"));
                    caja.setRutaImagen(rs.getString("RutaImagen"));
                    caja.setCristal(rs.getBoolean("Cristal"));
                    caja.setStock(rs.getInt("Stock"));
                    
                    cajadb.add(caja);
                }               
            } catch (SQLException error) {
                Log.logBd.error("ERROR SQL en getAllCajaes(): " + error);
                Log.logBd.error("                   SQL State - " + error.getSQLState());
                Log.logBd.error("                   ErrorCode - " + error.getErrorCode());
            }                   
      
        Log.logBd.info("Consulta realizada con éxito - getAllCajaes()");
       return cajadb;
    }  
}
