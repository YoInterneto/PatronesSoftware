
package DAO;

import Model.Articulos.Raton;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.Conexion;
import SingletonLog.Log;

/**
 * DAO para las operaciones de datos de la tabla y objeto ratón.
 * 
 */
public class RatonDao {
    
    private Connection conexion;
    
    /**
     * Dado el codigo de un raton realiza una consulta en la base de
     * datos y devuelve todos los datos correspondientes a dicho raton.
     *
     * @param codigo
     * @return Devuelve un objeto de tipo Raton
     */
    public Raton getRaton(int codigo){
        Raton raton = new Raton();
        Log.logBd.info("CONSULTA getRaton");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - getRaton()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from raton where codigo_ref='" + codigo + "';");
            Log.logBd.info("Realizada consulta - getRaton()");

            while (resultado.next()) {
                raton.setCodigo_ref(codigo);
                raton.setModelo(resultado.getString("Modelo"));
                raton.setPrecio(Float.parseFloat(resultado.getString("Precio")));
                raton.setDescripcion(resultado.getString("Descripcion"));
                raton.setStock(Integer.parseInt(resultado.getString("Stock")));
                raton.setRutaImagen(resultado.getString("rutaImagen"));
                
                raton.setDPI(Integer.parseInt(resultado.getString("DPI")));
                raton.setPeso(Integer.parseInt(resultado.getString("Peso")));
                raton.setTipo(resultado.getString("Tipo"));
                              
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getRaton: " + error);
            Log.logBd.error("                SQL State - " + error.getSQLState());
            Log.logBd.error("                ErrorCode - " + error.getErrorCode());
        }
        
        Log.logBd.info("Consulta realizada con éxito - getRaton");
        return raton;
    }
    
     /**
     * Realiza una consulta en la base de datos y devuelve todos los datos correspondientes sobre el/los ratones.
     *
     * @return Devuelve una lista de objetos de tipo Raton
     */
    public ArrayList<Raton> getAllRatones() {
        
        ArrayList<Raton> ratondb = new ArrayList<>();
        
            try {
                conexion = Conexion.getConexion();
                Log.logBd.info("Realizada conexion - ggetAllRatons()");
                Statement s = conexion.createStatement();
                ResultSet rs = s.executeQuery("select * from raton;");
                Log.logBd.info("CONSULTA getAllRatons");
                
                while (rs.next()) {                	
                	
                    Raton raton = new Raton();
                  
                    raton.setCodigo_ref(rs.getInt("Codigo_ref"));
                    raton.setDescripcion(rs.getString("Descripcion"));
                    raton.setModelo(rs.getString("Modelo"));
                    raton.setPrecio(rs.getFloat("Precio"));
                    raton.setRutaImagen(rs.getString("RutaImagen"));
                    raton.setStock(rs.getInt("Stock"));
                    raton.setTipo(rs.getString("Tipo"));
                    raton.setDPI(rs.getInt("DPI"));
                    raton.setPeso(rs.getInt("Peso"));
                    
                    ratondb.add(raton);
                }               
            } catch (SQLException error) {
                Log.logBd.error("ERROR SQL en getAllRatons(): " + error);
                Log.logBd.error("                   SQL State - " + error.getSQLState());
                Log.logBd.error("                   ErrorCode - " + error.getErrorCode());
            }                   
      
        Log.logBd.info("Consulta realizada con éxito - getAllRatons()");
        return ratondb;
    }  
    
    /**
     * Realiza una consulta en la base de datos para añadir un nuevo artículo raton.
     *
     * @param modelo
     * @param codigoReferencia
     * @param precio
     * @param descripcion
     * @param stock
     * @param rutaImagen
     * @param idTienda
     * @param dpi
     * @param tipo
     * @param peso
     * @return Devuelve un boolean para saber si se ha insertado
     */
    public boolean anadirRaton(String modelo, int codigoReferencia, float precio, String descripcion, int stock, String rutaImagen, int idTienda, int dpi, String tipo, int peso){
        boolean hecho = false;
        try {
            Log.logBd.info("CONSULTA AnadirRaton");
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - anadirRaton()");
            Statement s = conexion.createStatement();
            int codigo = s.executeUpdate("INSERT into raton VALUES('"+ modelo +"', "+ codigoReferencia +", "+ precio +", '"+ descripcion +"', "+ 
                    stock +", '"+ rutaImagen +"', "+ idTienda +", "+ dpi +", '"+ tipo +"', "+ peso +");");          
        
            if(codigo>0){
                hecho = true;
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en anadirRaton(): " + error);
            Log.logBd.error("                SQL State - " + error.getSQLState());
            Log.logBd.error("                ErrorCode - " + error.getErrorCode());
        }
        
        return hecho;
    }
}
