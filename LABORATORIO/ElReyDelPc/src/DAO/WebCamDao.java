
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
     * Dado el codigo de un webcam realiza una consulta en la base de
     * datos y devuelve todos los datos correspondientes a dicho webcam.
     *
     * @pawebcam codigo
     * @return Devuelve un objeto de tipo Webcam
     */
    public WebCam getWebcam(int codigo){
        WebCam webcam = new WebCam();
        Log.logBd.info("CONSULTA getWebCam");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - getWebCam()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from webcam where codigo_ref='" + codigo + "';");
            Log.logBd.info("Realizada consulta - getWebCam()");

            while (resultado.next()) {
                webcam.setCodigo_ref(codigo);
                webcam.setModelo(resultado.getString("Modelo"));
                webcam.setPrecio(Float.parseFloat(resultado.getString("Precio")));
                webcam.setDescripcion(resultado.getString("Descripcion"));
                webcam.setStock(Integer.parseInt(resultado.getString("Stock")));
                webcam.setRutaImagen(resultado.getString("rutaImagen"));
                
                webcam.setCalidad(resultado.getString("Calidad"));
 
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getWebCam: " + error);
            Log.logBd.error("                SQL State - " + error.getSQLState());
            Log.logBd.error("                ErrorCode - " + error.getErrorCode());
        }
        
        Log.logBd.info("Consulta realizada con éxito - getWebCam");
        return webcam;
    }
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
                    cam.setPrecio(rs.getFloat("Precio"));
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
     * @param calidad
     * @return Devuelve un boolean para saber si se ha insertado
     */
    public boolean anadirWebCam(String modelo, int codigoReferencia, float precio, String descripcion, int stock, String rutaImagen, int idTienda, String calidad){
        boolean hecho = false;
        try {
            Log.logBd.info("CONSULTA AnadirWebCam");
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - anadirWebCam()");
            Statement s = conexion.createStatement();
            int codigo = s.executeUpdate("INSERT into webcam VALUES('"+ modelo +"', "+ codigoReferencia +", "+ precio +", '"+ descripcion +"', "+ 
                    stock +", '"+ rutaImagen +"', "+ idTienda +", '"+ calidad +"');");          
        
            if(codigo>0){
                hecho = true;
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en anadirWebCam(): " + error);
            Log.logBd.error("                 SQL State - " + error.getSQLState());
            Log.logBd.error("                 ErrorCode - " + error.getErrorCode());
        }
        
        return hecho;
    }
}
