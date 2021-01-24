
package DAO;

import Model.Articulos.WebCam;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.Conexion;
import util.Log;


public class WebCamDao {
    
    private Connection conexion;
    
    /**
     * Realiza una consulta en la base de
     * datos y devuelve todos los datos correspondientes.
     *
     * @return Devuelve una lista de objetos de tipo WebCam
     */
    public ArrayList<WebCam> getAllWebCams() {
        
        ArrayList<WebCam> camdb = new ArrayList<WebCam>();
        
            try {
                conexion = Conexion.getConexion();
                Log.logBd.info("Realizada conexion - ggetAllWebCams()");
                Statement s = conexion.createStatement();
                ResultSet rs = s.executeQuery("select * from WebCam;");
                Log.logBd.info("CONSULTA getAllWebCams");
                
                while (rs.next()) {                	
                	
                    WebCam cam = new WebCam();
                  
                    cam.setCodigo_ref(rs.getInt("Codigo_ref"));
                    cam.setDescripcion(rs.getString("Descripcion"));
                    cam.setModelo(rs.getString("Modelo"));
                    cam.setPrecio(rs.getInt("Precio"));
                    cam.setRutaImagen(rs.getString("RutaImagen"));
                    cam.setStock(rs.getInt("Stock"));
                    
                    cam.setCalidad(rs.getString("Calidad"));
                    
                    camdb.add(cam);
                }               
            } catch (SQLException error) {
                Log.logBd.error("ERROR SQL en getAllWebCams(): " + error);
                Log.logBd.error("                   SQL State - " + error.getSQLState());
                Log.logBd.error("                   ErrorCode - " + error.getErrorCode());
            }                   
      
        Log.logBd.info("Consulta realizada con éxito - getAllWebCams()");
       return camdb;
    }  
}
