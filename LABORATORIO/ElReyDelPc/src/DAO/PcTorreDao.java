
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
     * Dado el codigo de un torre realiza una consulta en la base de
     * datos y devuelve todos los datos correspondientes a dicho torre.
     *
     * @patorre codigo
     * @return Devuelve un objeto de tipo PcTorre
     */
    public PcTorre getPcTorre(int codigo){
        PcTorre torre = new PcTorre();
        Log.logBd.info("CONSULTA getPcTorre");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - getPcTorre()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from pctorre where codigo_ref='" + codigo + "';");
            Log.logBd.info("Realizada consulta - getPcTorre()");

            while (resultado.next()) {
                torre.setCodigo_ref(codigo);
                torre.setModelo(resultado.getString("Modelo"));
                torre.setPrecio(Float.parseFloat(resultado.getString("Precio")));
                torre.setDescripcion(resultado.getString("Descripcion"));
                torre.setStock(Integer.parseInt(resultado.getString("Stock")));
                torre.setCreado(resultado.getBoolean("Creado"));
                torre.setRutaImagen(resultado.getString("rutaImagen"));
                
                torre.setNombre(resultado.getString("Nombre"));
 
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getPcTorre: " + error);
            Log.logBd.error("                SQL State - " + error.getSQLState());
            Log.logBd.error("                ErrorCode - " + error.getErrorCode());
        }
        
        Log.logBd.info("Consulta realizada con éxito - getPcTorre");
        return torre;
    }
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
                	
                    PcTorre torre = new PcTorre();
                  
                    torre.setCodigo_ref(rs.getInt("Codigo_ref"));
                    torre.setDescripcion(rs.getString("Descripcion"));
                    torre.setModelo(rs.getString("Modelo"));
                    torre.setPrecio(rs.getFloat("Precio"));
                    torre.setRutaImagen(rs.getString("RutaImagen"));
                    torre.setNombre(rs.getString("Nombre"));
                    torre.setStock(rs.getInt("Stock"));
                    torre.setCreado(rs.getBoolean("Creado"));
                    
                    portatildb.add(torre);
                }               
            } catch (SQLException error) {
                Log.logBd.error("ERROR SQL en getAllPcTorre: " + error);
                Log.logBd.error("                   SQL State - " + error.getSQLState());
                Log.logBd.error("                   ErrorCode - " + error.getErrorCode());
            }                   
      
        Log.logBd.info("Consulta realizada con éxito - getAllPcTorre");
       return portatildb;
    }  
    
    /**
     * Realiza una consulta en la base de datos para añadir
     * un nuevo artículo torre de pc
     *
     * @param modelo
     * @param codigoReferencia
     * @param precio
     * @param descripcion
     * @param stock
     * @param rutaImagen
     * @param idTienda
     * @param cristal
     * @return Devuelve un boolean para saber si se ha insertado
     */
    public boolean anadirTorre(String modelo, int codigoReferencia, float precio, String descripcion, int stock, String rutaImagen, int idTienda, String nombre,boolean creado){
        boolean hecho = false;
        try {
            Log.logBd.info("CONSULTA AnadirTorre");
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - anadirTorre()");
            Statement s = conexion.createStatement();
            int codigo = s.executeUpdate("INSERT into pctorre VALUES('"+ modelo +"', "+ codigoReferencia +", "+ precio +", '"+ descripcion +"', "+ 
                    stock +", '"+ rutaImagen +"', "+ idTienda +", '"+ nombre +"', "+creado+ ");");          
        
            if(codigo>0){
                hecho = true;
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en anadirTorre(): " + error);
            Log.logBd.error("                SQL State - " + error.getSQLState());
            Log.logBd.error("                ErrorCode - " + error.getErrorCode());
        }
        
        return hecho;
    }
}
