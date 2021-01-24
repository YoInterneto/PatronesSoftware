
package DAO;

import Model.Articulos.Grafica;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.Conexion;
import util.Log;


public class GraficaDao {
    
    private Connection conexion;
    
    /**
     * Realiza una consulta en la base de
     * datos y devuelve todos los datos correspondientes.
     *
     * @return Devuelve una lista de objetos de tipo Grafica
     */
    public ArrayList<Grafica> getAllGraficas() {
        
        ArrayList<Grafica> graficadb = new ArrayList<Grafica>();
        
            try {
                conexion = Conexion.getConexion();
                Log.logBd.info("Realizada conexion - ggetAllGraficaes()");
                Statement s = conexion.createStatement();
                ResultSet rs = s.executeQuery("select * from grafica;");
                Log.logBd.info("CONSULTA getAllGraficaes");
                
                while (rs.next()) {                	
                	
                    Grafica grafica = new Grafica();
                  
                    grafica.setCodigo_ref(rs.getInt("Codigo_ref"));
                    grafica.setDescripcion(rs.getString("Descripcion"));
                    grafica.setModelo(rs.getString("Modelo"));
                    grafica.setPrecio(rs.getInt("Precio"));
                    grafica.setRutaImagen(rs.getString("RutaImagen"));
                    grafica.setGeneracion(rs.getInt("Generacion"));
                    grafica.setStock(rs.getInt("Stock"));
                    
                    graficadb.add(grafica);
                }               
            } catch (SQLException error) {
                Log.logBd.error("ERROR SQL en getAllGraficaes(): " + error);
                Log.logBd.error("                   SQL State - " + error.getSQLState());
                Log.logBd.error("                   ErrorCode - " + error.getErrorCode());
            }                   
      
        Log.logBd.info("Consulta realizada con éxito - getAllGraficaes()");
       return graficadb;
    }  
}
