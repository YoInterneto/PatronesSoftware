
package DAO;

import Model.Articulos.Placa_base;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.Conexion;
import SingletonLog.Log;

/**
 * DAO para las operaciones de datos de la tabla y objeto placa base.
 * 
 */
public class Placa_baseDao {
    
    private Connection conexion;
    
    /**
     * Dado el codigo de un placa realiza una consulta en la base de
     * datos y devuelve todos los datos correspondientes a dicho placa.
     *
     * @param codigo
     * @return Devuelve un objeto de tipo Placa_base
     */
    public Placa_base getPlaca_base(int codigo){
        Placa_base placa = new Placa_base();
        Log.logBd.info("CONSULTA getPlaca_base");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - getPlaca_base()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from placa_base where codigo_ref='" + codigo + "';");
            Log.logBd.info("Realizada consulta - getPlaca_base()");

            while (resultado.next()) {
                placa.setCodigo_ref(codigo);
                placa.setModelo(resultado.getString("Modelo"));
                placa.setPrecio(Float.parseFloat(resultado.getString("Precio")));
                placa.setDescripcion(resultado.getString("Descripcion"));
                placa.setStock(Integer.parseInt(resultado.getString("Stock")));
                placa.setRutaImagen(resultado.getString("rutaImagen"));
                
                placa.setSocket(resultado.getString("Socket"));

            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getPlaca_base: " + error);
            Log.logBd.error("                SQL State - " + error.getSQLState());
            Log.logBd.error("                ErrorCode - " + error.getErrorCode());
        }
        
        Log.logBd.info("Consulta realizada con éxito - getPlaca_base");
        return placa;
    }
    
    /**
     * Realiza una consulta en la base de datos y devuelve todos los datos correspondientes sobre la/las placas.
     *
     * @return Devuelve una lista de objetos de tipo Placa base
     */
    public ArrayList<Placa_base> getAllPlacas() {
        
        ArrayList<Placa_base> placadb = new ArrayList<>();
        
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
     * Realiza una consulta en la base de datos para añadir un nuevo artículo placa base.
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
