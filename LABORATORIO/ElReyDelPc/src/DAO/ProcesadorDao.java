
package DAO;

import Model.Articulos.Procesador;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.Conexion;
import SingletonLog.Log;


public class ProcesadorDao {
    
    private Connection conexion;
    
    /**
     * Dado el codigo de un articulo realiza una consulta en la base de
 datos y devuelve todos los datos correspondientes a dicho articulo.
     *
     * @pacpu codigo
     * @return Devuelve un objeto de tipo Procesador
     */
    public Procesador getProcesador(int codigo){
        Procesador cpu = new Procesador();
        Log.logBd.info("CONSULTA getProcesador");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - getProcesador()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from Procesador where codigo_ref='" + codigo + "';");
            Log.logBd.info("Realizada consulta - getProcesador()");

            while (resultado.next()) {
                cpu.setCodigo_ref(codigo);
                cpu.setModelo(resultado.getString("Modelo"));
                cpu.setPrecio(Float.parseFloat(resultado.getString("Precio")));
                cpu.setDescripcion(resultado.getString("Descripcion"));
                cpu.setStock(Integer.parseInt(resultado.getString("Stock")));
                cpu.setRutaImagen(resultado.getString("rutaImagen"));
                
                cpu.setSocket(resultado.getString("Socket"));
 
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getProcesador: " + error);
            Log.logBd.error("                SQL State - " + error.getSQLState());
            Log.logBd.error("                ErrorCode - " + error.getErrorCode());
        }
        
        Log.logBd.info("Consulta realizada con éxito - getProcesador");
        return cpu;
    }
    
    /**
     * Realiza una consulta en la base de
     * datos y devuelve todos los datos correspondientes.
     *
     * @return Devuelve una lista de objetos de tipo Procesador
     */
    public ArrayList<Procesador> getAllProcesadores() {
        
        ArrayList<Procesador> procesadordb = new ArrayList<Procesador>();
        
            try {
                conexion = Conexion.getConexion();
                Log.logBd.info("Realizada conexion - ggetAllProcesadores()");
                Statement s = conexion.createStatement();
                ResultSet rs = s.executeQuery("select * from procesador;");
                Log.logBd.info("CONSULTA getAllProcesadores");
                
                while (rs.next()) {                	
                	
                    Procesador cpu = new Procesador();
                  
                    cpu.setCodigo_ref(rs.getInt("Codigo_ref"));
                    cpu.setDescripcion(rs.getString("Descripcion"));
                    cpu.setModelo(rs.getString("Modelo"));
                    cpu.setPrecio(rs.getFloat("Precio"));
                    cpu.setRutaImagen(rs.getString("RutaImagen"));
                    cpu.setSocket(rs.getString("Socket"));
                    cpu.setStock(rs.getInt("Stock"));
                    
                    procesadordb.add(cpu);
                }               
            } catch (SQLException error) {
                Log.logBd.error("ERROR SQL en getAllProcesadores(): " + error);
                Log.logBd.error("                   SQL State - " + error.getSQLState());
                Log.logBd.error("                   ErrorCode - " + error.getErrorCode());
            }                   
      
        Log.logBd.info("Consulta realizada con éxito - getAllProcesadores()");
       return procesadordb;
    }  
    
    /**
     * Realiza una consulta en la base de datos para añadir
     * un nuevo artículo procesador
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
    public boolean anadirProcesador(String modelo, int codigoReferencia, float precio, String descripcion, int stock, String rutaImagen, int idTienda, String socket){
        boolean hecho = false;
        try {
            Log.logBd.info("CONSULTA AnadirProcesador");
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - anadirProcesador()");
            Statement s = conexion.createStatement();
            int codigo = s.executeUpdate("INSERT into procesador VALUES('"+ modelo +"', "+ codigoReferencia +", "+ precio +", '"+ descripcion +"', "+ 
                    stock +", '"+ rutaImagen +"', "+ idTienda +", '"+ socket +"');");          
        
            if(codigo>0){
                hecho = true;
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en anadirProcesador(): " + error);
            Log.logBd.error("                     SQL State - " + error.getSQLState());
            Log.logBd.error("                     ErrorCode - " + error.getErrorCode());
        }
        
        return hecho;
    }
}
