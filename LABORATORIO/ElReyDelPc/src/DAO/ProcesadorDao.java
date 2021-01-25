
package DAO;

import Model.Articulos.Procesador;
import java.awt.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.Conexion;
import util.Log;


public class ProcesadorDao {
    
    private Connection conexion;
    
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
}
