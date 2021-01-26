
package DAO;

import Model.Articulos.Memoria_RAM;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.Conexion;
import util.Log;


public class MemoriaRAMDao {
    
    private Connection conexion;
    
    /**
     * Realiza una consulta en la base de
     * datos y devuelve todos los datos correspondientes.
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
     * Realiza una consulta en la base de datos para añadir
     * un nuevo artículo memoria RAM
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
