
package DAO;

import Model.Articulos.Disco_duro;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.Conexion;
import util.Log;


public class DiscoDuroDao {
    
    private Connection conexion;
    
    /**
     * Dado el codigo de un disco_duro realiza una consulta en la base de
     * datos y devuelve todos los datos correspondientes a dicho disco_duro.
     *
     * @param codigo
     * @return Devuelve un objeto de tipo Disco_duro
     */
    public Disco_duro getDisco_duro(int codigo){
        Disco_duro disco_duro = new Disco_duro();
        Log.logBd.info("CONSULTA getDisco_duro");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - getDisco_duro()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from disco_duro where codigo_ref='" + codigo + "';");
            Log.logBd.info("Realizada consulta - getDisco_duro()");

            while (resultado.next()) {
                disco_duro.setCodigo_ref(codigo);
                disco_duro.setModelo(resultado.getString("Modelo"));
                disco_duro.setPrecio(Float.parseFloat(resultado.getString("Precio")));
                disco_duro.setDescripcion(resultado.getString("Descripcion"));
                disco_duro.setStock(Integer.parseInt(resultado.getString("Stock")));
                disco_duro.setRutaImagen(resultado.getString("rutaImagen"));
                
                disco_duro.setTipo(resultado.getString("Tipo"));             
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getDisco_duro: " + error);
            Log.logBd.error("                SQL State - " + error.getSQLState());
            Log.logBd.error("                ErrorCode - " + error.getErrorCode());
        }
        
        Log.logBd.info("Consulta realizada con éxito - getDisco_duro");
        return disco_duro;
    }
    
    /**
     * Realiza una consulta en la base de
     * datos y devuelve todos los datos correspondientes.
     *
     * @return Devuelve una lista de objetos de tipo Disco_duro
     */
    public ArrayList<Disco_duro> getAllDiscos() {
        
        ArrayList<Disco_duro> discodb = new ArrayList<Disco_duro>();
        
            try {
                conexion = Conexion.getConexion();
                Log.logBd.info("Realizada conexion - ggetAllDiscos()");
                Statement s = conexion.createStatement();
                ResultSet rs = s.executeQuery("select * from disco_duro;");
                Log.logBd.info("CONSULTA getAllDisco_duroes");
                
                while (rs.next()) {                	
                	
                    Disco_duro disco = new Disco_duro();
                  
                    disco.setCodigo_ref(rs.getInt("Codigo_ref"));
                    disco.setDescripcion(rs.getString("Descripcion"));
                    disco.setModelo(rs.getString("Modelo"));
                    disco.setPrecio(rs.getFloat("Precio"));
                    disco.setRutaImagen(rs.getString("RutaImagen"));
                    disco.setTipo(rs.getString("Tipo"));
                    disco.setStock(rs.getInt("Stock"));
                    
                    discodb.add(disco);
                }               
            } catch (SQLException error) {
                Log.logBd.error("ERROR SQL en getAllDiscos: " + error);
                Log.logBd.error("                   SQL State - " + error.getSQLState());
                Log.logBd.error("                   ErrorCode - " + error.getErrorCode());
            }                   
      
        Log.logBd.info("Consulta realizada con éxito - getAllDiscos");
       return discodb;
    }  
    
    /**
     * Realiza una consulta en la base de datos para añadir
     * un nuevo artículo disco duro
     *
     * @param modelo
     * @param codigoReferencia
     * @param precio
     * @param descripcion
     * @param stock
     * @param rutaImagen
     * @param idTienda
     * @return Devuelve un boolean para saber si se ha insertado
     */
    public boolean anadirDiscoDuro(String modelo, int codigoReferencia, float precio, String descripcion, int stock, String rutaImagen, int idTienda, String tipo){
        boolean hecho = false;
        try {
            Log.logBd.info("CONSULTA AnadirDiscoDuro");
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - anadirDiscoDuro()");
            Statement s = conexion.createStatement();
            int codigo = s.executeUpdate("INSERT into disco_duro VALUES('"+ modelo +"', "+ codigoReferencia +", "+ precio +", '"+ descripcion +"', "+ 
                    stock +", '"+ rutaImagen +"', "+ idTienda +", '"+ tipo +"');");          
        
            if(codigo>0){
                hecho = true;
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en anadirDiscoDuro(): " + error);
            Log.logBd.error("                    SQL State - " + error.getSQLState());
            Log.logBd.error("                    ErrorCode - " + error.getErrorCode());
        }
        
        return hecho;
    }
}
