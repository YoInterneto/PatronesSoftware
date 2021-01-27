
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
     * Dado el codigo de un grafica realiza una consulta en la base de
     * datos y devuelve todos los datos correspondientes a dicho grafica.
     *
     * @param codigo
     * @return Devuelve un objeto de tipo Grafica
     */
    public Grafica getGrafica(int codigo){
        Grafica grafica = new Grafica();
        Log.logBd.info("CONSULTA getGrafica");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - getGrafica()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from grafica where codigo_ref='" + codigo + "';");
            Log.logBd.info("Realizada consulta - getGrafica()");

            while (resultado.next()) {
                grafica.setCodigo_ref(codigo);
                grafica.setModelo(resultado.getString("Modelo"));
                grafica.setPrecio(resultado.getFloat("Precio"));
                grafica.setDescripcion(resultado.getString("Descripcion"));
                grafica.setStock(Integer.parseInt(resultado.getString("Stock")));
                grafica.setRutaImagen(resultado.getString("rutaImagen"));
                
                grafica.setGeneracion(Integer.parseInt(resultado.getString("Generacion")));

            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getGrafica: " + error);
            Log.logBd.error("                SQL State - " + error.getSQLState());
            Log.logBd.error("                ErrorCode - " + error.getErrorCode());
        }
        
        Log.logBd.info("Consulta realizada con éxito - getGrafica");
        return grafica;
    }
    
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
                    grafica.setPrecio(rs.getFloat("Precio"));
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
     * @param generacion
     * @return Devuelve un boolean para saber si se ha insertado
     */
    public boolean anadirGrafica(String modelo, int codigoReferencia, float precio, String descripcion, int stock, String rutaImagen, int idTienda, int generacion){
        boolean hecho = false;
        try {
            Log.logBd.info("CONSULTA AnadirGrafica");
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - anadirGrafica()");
            Statement s = conexion.createStatement();
            int codigo = s.executeUpdate("INSERT into grafica VALUES('"+ modelo +"', "+ codigoReferencia +", "+ precio +", '"+ descripcion +"', "+ 
                    stock +", '"+ rutaImagen +"', "+ idTienda +", "+ generacion +");");          
        
            if(codigo>0){
                hecho = true;
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en anadirGrafica(): " + error);
            Log.logBd.error("                  SQL State - " + error.getSQLState());
            Log.logBd.error("                  ErrorCode - " + error.getErrorCode());
        }
        
        return hecho;
    }
}
