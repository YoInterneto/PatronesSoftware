
package DAO;

import Model.Articulos.Memoria_RAM;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.Conexion;
import SingletonLog.Log;

/**
 * DAO para las operaciones de datos de la tabla y objeto RAM.
 * 
 */
public class MemoriaRAMDao {
    
    private Connection conexion;
    
    /**
     * Dado el codigo de un ram realiza una consulta en la base de datos y devuelve todos los datos correspondientes a dicho ram.
     *
     * @param codigo
     * @return Devuelve un objeto de tipo Memoria_RAM
     */
    public Memoria_RAM getMemoria_RAM(int codigo){
        Memoria_RAM ram = new Memoria_RAM();
        Log.logBd.info("CONSULTA getMemoria_RAM");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - getMemoria_RAM()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from Memoria_ram where codigo_ref='" + codigo + "';");
            Log.logBd.info("Realizada consulta - getMemoria_RAM()");

            while (resultado.next()) {
                ram.setCodigo_ref(codigo);
                ram.setModelo(resultado.getString("Modelo"));
                ram.setPrecio(Float.parseFloat(resultado.getString("Precio")));
                ram.setDescripcion(resultado.getString("Descripcion"));
                ram.setStock(Integer.parseInt(resultado.getString("Stock")));
                ram.setRutaImagen(resultado.getString("rutaImagen"));
                
                ram.setPN(resultado.getString("PN"));
 
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getMemoria_RAM: " + error);
            Log.logBd.error("                SQL State - " + error.getSQLState());
            Log.logBd.error("                ErrorCode - " + error.getErrorCode());
        }
        
        Log.logBd.info("Consulta realizada con éxito - getMemoria_RAM");
        return ram;
    }
    
    /**
     * Realiza una consulta en la base de datos y devuelve todos los datos correspondientes de la/las RAM.
     *
     * @return Devuelve una lista de objetos de tipo Memoria_RAM
     */
    public ArrayList<Memoria_RAM> getAllMemoria_RAM() {
        
        ArrayList<Memoria_RAM> MemoriaRamdb = new ArrayList<Memoria_RAM>();
        
            try {
                conexion = Conexion.getConexion();
                Log.logBd.info("Realizada conexion - ggetAllMemoria_RAMes()");
                Statement s = conexion.createStatement();
                ResultSet rs = s.executeQuery("select * from Memoria_RAM;");
                Log.logBd.info("CONSULTA getAllMemoria_RAMes");
                
                while (rs.next()) {                	
                	
                    Memoria_RAM ram = new Memoria_RAM();
                  
                    ram.setCodigo_ref(rs.getInt("Codigo_ref"));
                    ram.setDescripcion(rs.getString("Descripcion"));
                    ram.setModelo(rs.getString("Modelo"));
                    ram.setPrecio(rs.getFloat("Precio"));
                    ram.setRutaImagen(rs.getString("RutaImagen"));
                    ram.setPN(rs.getString("PN"));
                    ram.setStock(rs.getInt("Stock"));
                    
                    MemoriaRamdb.add(ram);
                }               
            } catch (SQLException error) {
                Log.logBd.error("ERROR SQL en getAllMemoria_RAMes(): " + error);
                Log.logBd.error("                   SQL State - " + error.getSQLState());
                Log.logBd.error("                   ErrorCode - " + error.getErrorCode());
            }                   
      
        Log.logBd.info("Consulta realizada con éxito - getAllMemoria_RAMes()");
        return MemoriaRamdb;
    }  
    
    /**
     * Realiza una consulta en la base de datos para añadir un nuevo artículo memoria RAM.
     *
     * @param modelo
     * @param codigoReferencia
     * @param precio
     * @param descripcion
     * @param stock
     * @param rutaImagen
     * @param idTienda
     * @param pn
     * @return Devuelve un boolean para saber si se ha insertado
     */
    public boolean anadirRAM(String modelo, int codigoReferencia, float precio, String descripcion, int stock, String rutaImagen, int idTienda, String pn){
        boolean hecho = false;
        try {
            Log.logBd.info("CONSULTA AnadirRAM");
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - anadirRAM()");
            Statement s = conexion.createStatement();
            int codigo = s.executeUpdate("INSERT into memoria_ram VALUES('"+ modelo +"', "+ codigoReferencia +", "+ precio +", '"+ descripcion +"', "+ 
                    stock +", '"+ rutaImagen +"', "+ idTienda +", '"+ pn +"');");          
        
            if(codigo>0){
                hecho = true;
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en anadirRAM(): " + error);
            Log.logBd.error("              SQL State - " + error.getSQLState());
            Log.logBd.error("              ErrorCode - " + error.getErrorCode());
        }
        
        return hecho;
    }
}
