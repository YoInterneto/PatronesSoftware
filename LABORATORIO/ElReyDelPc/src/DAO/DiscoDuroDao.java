
package DAO;

import Model.Articulos.Disco_duro;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.Conexion;
import util.Log;


public class DiscoDuroDao {
    
    private Connection conexion;
    
    /**
     * Realiza una consulta en la base de
     * datos y devuelve todos los datos correspondientes.
     *
     * @return Devuelve una lista de objetos de tipo Disco_duro
     */
    public ArrayList<Disco_duro> getAllDiscos() {
        
        ArrayList<Disco_duro> discodb = new ArrayList<Disco_duro>();
        
            try {
                conexion = Conexion.getConexion();
                Log.logBd.info("Realizada conexion - ggetAllDiscos()");
                Statement s = conexion.createStatement();
                ResultSet rs = s.executeQuery("select * from disco_duro;");
                Log.logBd.info("CONSULTA getAllDisco_duroes");
                
                while (rs.next()) {                	
                	
                    Disco_duro disco = new Disco_duro();
                  
                    disco.setCodigo_ref(rs.getInt("Codigo_ref"));
                    disco.setDescripcion(rs.getString("Descripcion"));
                    disco.setModelo(rs.getString("Modelo"));
                    disco.setPrecio(rs.getFloat("Precio"));
                    disco.setRutaImagen(rs.getString("RutaImagen"));
                    disco.setTipo(rs.getString("Tipo"));
                    disco.setStock(rs.getInt("Stock"));
                    
                    discodb.add(disco);
                }               
            } catch (SQLException error) {
                Log.logBd.error("ERROR SQL en getAllDiscos: " + error);
                Log.logBd.error("                   SQL State - " + error.getSQLState());
                Log.logBd.error("                   ErrorCode - " + error.getErrorCode());
            }                   
      
        Log.logBd.info("Consulta realizada con éxito - getAllDiscos");
       return discodb;
    }  
}
