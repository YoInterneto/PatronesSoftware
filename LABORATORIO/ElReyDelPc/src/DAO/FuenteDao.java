
package DAO;

import Model.Articulos.Fuente_alimentacion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.Conexion;
import util.Log;


public class FuenteDao {
    
    private Connection conexion;
    
    /**
     * Realiza una consulta en la base de
     * datos y devuelve todos los datos correspondientes.
     *
     * @return Devuelve una lista de objetos de tipo Fuente_alimentacion
     */
    public ArrayList<Fuente_alimentacion> getAllFuentes() {
        
        ArrayList<Fuente_alimentacion> fuentedb = new ArrayList<Fuente_alimentacion>();
        
            try {
                conexion = Conexion.getConexion();
                Log.logBd.info("Realizada conexion - ggetAllFuente_alimentaciones()");
                Statement s = conexion.createStatement();
                ResultSet rs = s.executeQuery("select * from fuente_alimentacion;");
                Log.logBd.info("CONSULTA getAllFuente_alimentaciones");
                
                while (rs.next()) {                	
                	
                    Fuente_alimentacion fuente = new Fuente_alimentacion();
                  
                    fuente.setCodigo_ref(rs.getInt("Codigo_ref"));
                    fuente.setDescripcion(rs.getString("Descripcion"));
                    fuente.setModelo(rs.getString("Modelo"));
                    fuente.setPrecio(rs.getFloat("Precio"));
                    fuente.setRutaImagen(rs.getString("RutaImagen"));
                    fuente.setCertificacion(rs.getString("Certificacion"));
                    fuente.setStock(rs.getInt("Stock"));
                    
                    fuentedb.add(fuente);
                }               
            } catch (SQLException error) {
                Log.logBd.error("ERROR SQL en getAllFuente_alimentaciones(): " + error);
                Log.logBd.error("                   SQL State - " + error.getSQLState());
                Log.logBd.error("                   ErrorCode - " + error.getErrorCode());
            }                   
      
        Log.logBd.info("Consulta realizada con éxito - getAllFuente_alimentaciones()");
       return fuentedb;
    }  
    
    /**
     * Realiza una consulta en la base de datos para añadir
     * un nuevo artículo fuente de alimentacion
     *
     * @param modelo
     * @param codigoReferencia
     * @param precio
     * @param descripcion
     * @param stock
     * @param rutaImagen
     * @param idTienda
     * @param potencia
     * @param certificacion
     * @return Devuelve un boolean para saber si se ha insertado
     */
    public boolean anadirFuente(String modelo, int codigoReferencia, float precio, String descripcion, int stock, String rutaImagen, int idTienda, int potencia, String certificacion){
        boolean hecho = false;
        try {
            Log.logBd.info("CONSULTA AnadirFuente");
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - anadirFuente()");
            Statement s = conexion.createStatement();
            int codigo = s.executeUpdate("INSERT into fuente_alimentacion VALUES('"+ modelo +"', "+ codigoReferencia +", "+ precio +", '"+ descripcion +"', "+ 
                    stock +", '"+ rutaImagen +"', "+ idTienda +", "+ potencia +", '"+ certificacion +"');");          
        
            if(codigo>0){
                hecho = true;
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en anadirFuente(): " + error);
            Log.logBd.error("                 SQL State - " + error.getSQLState());
            Log.logBd.error("                 ErrorCode - " + error.getErrorCode());
        }
        
        return hecho;
    }
}
