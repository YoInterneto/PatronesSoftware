
package DAO;


import Model.Articulos.Teclado;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.Conexion;
import SingletonLog.Log;


public class TecladoDao {
    
    private Connection conexion;
    
    /**
     * Dado el codigo de un teclado realiza una consulta en la base de
     * datos y devuelve todos los datos correspondientes a dicho teclado.
     *
     * @pateclado codigo
     * @return Devuelve un objeto de tipo Teclado
     */
    public Teclado getTeclado(int codigo){
        Teclado teclado = new Teclado();
        Log.logBd.info("CONSULTA getTeclado");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - getTeclado()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from teclado where codigo_ref='" + codigo + "';");
            Log.logBd.info("Realizada consulta - getTeclado()");

            while (resultado.next()) {
                teclado.setCodigo_ref(codigo);
                teclado.setModelo(resultado.getString("Modelo"));
                teclado.setPrecio(Float.parseFloat(resultado.getString("Precio")));
                teclado.setDescripcion(resultado.getString("Descripcion"));
                teclado.setStock(Integer.parseInt(resultado.getString("Stock")));
                teclado.setRutaImagen(resultado.getString("rutaImagen"));
                
                teclado.setTipo(resultado.getString("Tipo"));
 
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getTeclado: " + error);
            Log.logBd.error("                SQL State - " + error.getSQLState());
            Log.logBd.error("                ErrorCode - " + error.getErrorCode());
        }
        
        Log.logBd.info("Consulta realizada con éxito - getTeclado");
        return teclado;
    }
    /**
     * Realiza una consulta en la base de
     * datos y devuelve todos los datos correspondientes.
     *
     * @return Devuelve una lista de objetos de tipo Teclado
     */
    public ArrayList<Teclado> getAllTeclados() {
        
        ArrayList<Teclado> tecladodb = new ArrayList<Teclado>();
        
            try {
                conexion = Conexion.getConexion();
                Log.logBd.info("Realizada conexion - ggetAllTecladoes()");
                Statement s = conexion.createStatement();
                ResultSet rs = s.executeQuery("select * from teclado;");
                Log.logBd.info("CONSULTA getAllTecladoes");
                
                while (rs.next()) {                	
                	
                    Teclado teclado = new Teclado();
                  
                    teclado.setCodigo_ref(rs.getInt("Codigo_ref"));
                    teclado.setDescripcion(rs.getString("Descripcion"));
                    teclado.setModelo(rs.getString("Modelo"));
                    teclado.setPrecio(rs.getFloat("Precio"));
                    teclado.setRutaImagen(rs.getString("RutaImagen"));
                    teclado.setTipo(rs.getString("Tipo"));
                    teclado.setStock(rs.getInt("Stock"));
                    
                    tecladodb.add(teclado);
                }               
            } catch (SQLException error) {
                Log.logBd.error("ERROR SQL en getAllTecladoes(): " + error);
                Log.logBd.error("                   SQL State - " + error.getSQLState());
                Log.logBd.error("                   ErrorCode - " + error.getErrorCode());
            }                   
      
        Log.logBd.info("Consulta realizada con éxito - getAllTecladoes()");
       return tecladodb;
    }  
    
    /**
     * Realiza una consulta en la base de datos para añadir
     * un nuevo artículo placa base
     *
     * @param modelo
     * @param codigoReferencia
     * @param precio
     * @param descripcion
     * @param stock
     * @param rutaImagen
     * @param idTienda
     * @param tipo
     * @return Devuelve un boolean para saber si se ha insertado
     */
    public boolean anadirTeclado(String modelo, int codigoReferencia, float precio, String descripcion, int stock, String rutaImagen, int idTienda, String tipo){
        boolean hecho = false;
        try {
            Log.logBd.info("CONSULTA AnadirTeclado");
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - anadirTeclado()");
            Statement s = conexion.createStatement();
            int codigo = s.executeUpdate("INSERT into teclado VALUES('"+ modelo +"', "+ codigoReferencia +", "+ precio +", '"+ descripcion +"', "+ 
                    stock +", '"+ rutaImagen +"', "+ idTienda +", '"+ tipo +"');");          
        
            if(codigo>0){
                hecho = true;
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en anadirTeclado(): " + error);
            Log.logBd.error("                  SQL State - " + error.getSQLState());
            Log.logBd.error("                  ErrorCode - " + error.getErrorCode());
        }
        
        return hecho;
    }
}
