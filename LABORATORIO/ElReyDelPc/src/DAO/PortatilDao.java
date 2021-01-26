
package DAO;

import Model.Articulos.Fuente_alimentacion;
import Model.Articulos.Portatil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.Conexion;
import util.Log;


public class PortatilDao {
    
    private Connection conexion;
    /**
     * Realiza una consulta en la base de
     * datos y devuelve todos los datos correspondientes.
     *
     * @return Devuelve una lista de objetos de tipo Portatil
     */
    public ArrayList<Portatil> getAllPortatil() {
        
        ArrayList<Portatil> portatildb = new ArrayList<Portatil>();
        
            try {
                conexion = Conexion.getConexion();
                Log.logBd.info("Realizada conexion - getAllPortatil");
                Statement s = conexion.createStatement();
                ResultSet rs = s.executeQuery("select * from portatil;");
                Log.logBd.info("CONSULTA getAllPortatil");
                
                while (rs.next()) {                	
                	
                    Portatil portatil = new Portatil();
                  
                    portatil.setCodigo_ref(rs.getInt("Codigo_ref"));
                    portatil.setDescripcion(rs.getString("Descripcion"));
                    portatil.setModelo(rs.getString("Modelo"));
                    portatil.setPrecio(rs.getFloat("Precio"));
                    portatil.setRutaImagen(rs.getString("RutaImagen"));
                    portatil.setPeso(rs.getInt("Peso"));
                    portatil.setPanel(rs.getString("Panel"));
                    portatil.setStock(rs.getInt("Stock"));
                    
                    portatildb.add(portatil);
                }               
            } catch (SQLException error) {
                Log.logBd.error("ERROR SQL en getAllPortatil: " + error);
                Log.logBd.error("                   SQL State - " + error.getSQLState());
                Log.logBd.error("                   ErrorCode - " + error.getErrorCode());
            }                   
      
        Log.logBd.info("Consulta realizada con éxito - getAllPortatil");
       return portatildb;
    }  
    
}
