
package DAO;

import Model.Articulos.Portatil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.Conexion;
import SingletonLog.Log;


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
    
    /**
     * Dado el codigo de un portatil realiza una consulta en la base de
     * datos y devuelve todos los datos correspondientes a dicho portatil.
     *
     * @param codigo
     * @return Devuelve un objeto de tipo Portatil
     */
    public Portatil getPortatil(int codigo){
        Portatil portatil = new Portatil();
        Log.logBd.info("CONSULTA getPortatil");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - getPortatil()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from portatil where codigo_ref='" + codigo + "';");
            Log.logBd.info("Realizada consulta - getPortatil()");

            while (resultado.next()) {
                portatil.setCodigo_ref(codigo);
                portatil.setModelo(resultado.getString("Modelo"));
                portatil.setPrecio(Float.parseFloat(resultado.getString("Precio")));
                portatil.setDescripcion(resultado.getString("Descripcion"));
                portatil.setStock(Integer.parseInt(resultado.getString("Stock")));
                portatil.setRutaImagen(resultado.getString("rutaImagen"));
                
                portatil.setPanel(resultado.getString("Panel"));
                portatil.setPeso(Integer.parseInt(resultado.getString("Peso")));
                
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getPortatil: " + error);
            Log.logBd.error("                SQL State - " + error.getSQLState());
            Log.logBd.error("                ErrorCode - " + error.getErrorCode());
        }
        
        Log.logBd.info("Consulta realizada con éxito - getPortatil");
        return portatil;
    }
    
    /**
     * Realiza una consulta en la base de datos para añadir
     * un nuevo artículo portatil
     *
     * @param modelo
     * @param codigoReferencia
     * @param precio
     * @param descripcion
     * @param stock
     * @param rutaImagen
     * @param idTienda
     * @param panel
     * @param peso
     * @return Devuelve un boolean para saber si se ha insertado
     */
    public boolean anadirPortatil(String modelo, int codigoReferencia, float precio, String descripcion, int stock, String rutaImagen, int idTienda, String panel, int peso){
        boolean hecho = false;
        try {
            Log.logBd.info("CONSULTA AnadirPortatil");
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - anadirPortatil()");
            Statement s = conexion.createStatement();
            int codigo = s.executeUpdate("INSERT into portatil VALUES('"+ modelo +"', "+ codigoReferencia +", "+ precio +", '"+ descripcion +"', "+ 
                    stock +", '"+ rutaImagen +"', "+ idTienda +", '"+ panel +"', "+ peso +");");          
        
            if(codigo>0){
                hecho = true;
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en anadirPortatil(): " + error);
            Log.logBd.error("                   SQL State - " + error.getSQLState());
            Log.logBd.error("                   ErrorCode - " + error.getErrorCode());
        }
        
        return hecho;
    }
}
