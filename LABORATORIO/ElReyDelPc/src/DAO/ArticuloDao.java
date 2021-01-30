
package DAO;

import Model.Articulos.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.Conexion;
import SingletonLog.Log;


public class ArticuloDao {
    
    private Connection conexion;
    
    /**
     * Identifica qué modelo es y devuelve su tipo
     *
     * @param modelo
     * @return Devuelve un String que representa el modelo de articulo
     * sesión (cliente o portatil)
     */
    public String getArticuloIgualModelo(String modelo) {
        String tipo = "";
        Log.logBd.info("CONSULTA getArticuloIgualModelo");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - getArticuloIgualModelo()");
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery("select * from articulo where modelo='" + modelo + "';");
            Log.logBd.info("Realizada consulta - getArticuloIgualModelo()");
            if (rs.next()) {
               
            }           
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getArticuloIgualModelo(): " + error);
            Log.logBd.error("                   SQL State - " + error.getSQLState());
            Log.logBd.error("                   ErrorCode - " + error.getErrorCode());

            tipo = "error";
        }
        Log.logBd.info("Consulta realizada con éxito - getArticuloIgualModelo()");
        return tipo;
    }
    
    /**
     * Retorna el articulo de la base de datos el cual tenga codigo de referencia indicado
     *
     * @param codigoReferencia
     * @return Devuelve un objeto articulo
     */
    public Articulo getArticulo(int codigoReferencia) {
        Articulo articulo = new Articulo();
        Log.logBd.info("CONSULTA GetArticulo");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - getArticulo()");
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery("select * from articulo where codigo_ref="+ codigoReferencia);
            Log.logBd.info("Realizada consulta - getArticulo()");
             
            if(rs.next()) {
                articulo.setCodigo_ref(rs.getInt("Codigo_ref"));
                articulo.setDescripcion(rs.getString("Descripcion"));
                articulo.setModelo(rs.getString("Modelo"));
                articulo.setPrecio(rs.getFloat("Precio"));
                articulo.setRutaImagen(rs.getString("RutaImagen"));
                articulo.setStock(rs.getInt("Stock"));
            }           
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getArticulo(): " + error);
            Log.logBd.error("                SQL State - " + error.getSQLState());
            Log.logBd.error("                ErrorCode - " + error.getErrorCode());
        }
        
        Log.logBd.info("Consulta realizada con éxito - getArticulo()");
        return articulo;
    }
    
    /**
     * Retorna el codigo de referencia maximo que hay en la tabla
     *
     * @return Devuelve un objeto articulo
     */
    public int getCodRefMax() {
        int codRefMax = -1;
        Log.logBd.info("CONSULTA getCodRefMax");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - getCodRefMax()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select MAX(codigo_ref) as maximo from articulo");
            Log.logBd.info("Realizada consulta - getCodRefMax()"); 
            
            if(resultado.next()){
                codRefMax = resultado.getInt("maximo") + 1;
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getCodRefMax(): " + error);
            Log.logBd.error("                 SQL State - " + error.getSQLState());
            Log.logBd.error("                 ErrorCode - " + error.getErrorCode());
        }
        
        Log.logBd.info("Consulta realizada con éxito - getCodRefMax()");
        return codRefMax;
    }  
    
    /**
     * Realiza una consulta en la base de
     * datos y devuelve todos los datos correspondientes.
     *
     * @return Devuelve una lista de objetos de tipo Articulo
     */
    public ArrayList<Articulo> getAllArticulos() {
        
        ArrayList<Articulo> articulodb = new ArrayList<Articulo>();
        
            try {
                conexion = Conexion.getConexion();
                Log.logBd.info("Realizada conexion - getAllArticulos()");
                Statement s = conexion.createStatement();
                ResultSet rs = s.executeQuery("select * from articulo order by codigo_ref ASC;");
                Log.logBd.info("CONSULTA getAllArticulos");
                
                while (rs.next()) {       
                    String modelo = rs.getString("Modelo").toLowerCase();
                    //Añade todos los artículos del catálogo excepto los pc montados por cliente
                    if(!modelo.contains("custom-")){
                        Articulo articulo = new Articulo();
                  
                        articulo.setCodigo_ref(rs.getInt("Codigo_ref"));
                        articulo.setDescripcion(rs.getString("Descripcion"));
                        articulo.setModelo(rs.getString("Modelo"));
                        articulo.setPrecio(rs.getFloat("Precio"));
                        articulo.setRutaImagen(rs.getString("RutaImagen"));
                        articulo.setStock(rs.getInt("Stock"));

                        articulodb.add(articulo);
                    }
                }               
            } catch (SQLException error) {
                Log.logBd.error("ERROR SQL en getAllArticulos(): " + error);
                Log.logBd.error("                   SQL State - " + error.getSQLState());
                Log.logBd.error("                   ErrorCode - " + error.getErrorCode());
            }                   
      
        Log.logBd.info("Consulta realizada con éxito - getAllArticulos()");
       return articulodb;
    }
    
    /**
     * Realiza una consulta en la base de
     * datos y devuelve todos los datos correspondientes.
     *
     * @return Devuelve una lista de objetos de tipo Articulo
     */
    public ArrayList<Articulo> getAllArticulosTipo(String tipo) {
        
        ArrayList<Articulo> articulodb = new ArrayList<Articulo>();
        
            try {
                conexion = Conexion.getConexion();
                Log.logBd.info("Realizada conexion - getAllArticulosTipo()");
                Statement s = conexion.createStatement();
                ResultSet rs = s.executeQuery("select * from articulo natural join "+ tipo +" where "+ tipo+ ".codigo_ref=articulo.codigo_ref order by codigo_ref ASC;");
                Log.logBd.info("CONSULTA getAllArticulosTipo");
                
                while (rs.next()) {       
                    String modelo = rs.getString("Modelo").toLowerCase();
                    //Añade todos los artículos del catálogo excepto los pc montados por cliente
                    if(!modelo.contains("custom-")){
                        Articulo articulo = new Articulo();
                  
                        articulo.setCodigo_ref(rs.getInt("Codigo_ref"));
                        articulo.setDescripcion(rs.getString("Descripcion"));
                        articulo.setModelo(rs.getString("Modelo"));
                        articulo.setPrecio(rs.getFloat("Precio"));
                        articulo.setRutaImagen(rs.getString("RutaImagen"));
                        articulo.setStock(rs.getInt("Stock"));

                        articulodb.add(articulo);
                    }
                }               
            } catch (SQLException error) {
                Log.logBd.error("ERROR SQL en getAllArticulosTipo(): " + error);
                Log.logBd.error("                   SQL State - " + error.getSQLState());
                Log.logBd.error("                   ErrorCode - " + error.getErrorCode());
            }                   
      
        Log.logBd.info("Consulta realizada con éxito - getAllArticulosTipo()");
       return articulodb;
    }
    
    /**
     * Dado el nombre de la tabla y el codigo de referencia retorna la ruta de la imagen
     * del artículo
     *
     * @param tabla
     * @param codigo
     * @return Devuelve un String
     */
    public String getRutaImg(String tabla, int codigo){
        String ruta = null;
        Log.logBd.info("CONSULTA GetRutaImg");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - getRutaImg()");
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery("select rutaImagen from "+ tabla+ " where codigo_ref="+ codigo);
            Log.logBd.info("Realizada consulta - getRutaImg()");
            if(rs.next()) { 
                ruta = rs.getString("RutaImagen");
            }

        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getRutaImg(): " + error);
            Log.logBd.error("                SQL State - " + error.getSQLState());
            Log.logBd.error("                ErrorCode - " + error.getErrorCode());
        }

        Log.logBd.info("Consulta realizada con éxito - getRutaImg()");
        return ruta;
    }
    
    /**
     * Dado los nuevos datos que quiere cambiar el empleado de un articulo
     * se realiza una actualización en la base de datos para ese articulo
     * 
     * @param codigoReferencia
     * @param modelo
     * @param descripcion
     * @param precio
     * @param stock
     * @return Devuelve un boolean para saber si se ha realizado o no la consulta
     */
    public boolean editarArticulo(int codigoReferencia, String modelo, String descripcion, int stock, float precio){
        boolean hecho = false;
        Log.logBd.info("CONSULTA EditarArticulo");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - editarArticulo()");
            Statement s = conexion.createStatement();
            int codigo = s.executeUpdate("UPDATE articulo SET modelo='"+ modelo +"', descripcion='"+ descripcion +"', precio="+ precio +
                    ", stock="+ stock +" WHERE codigo_ref="+ codigoReferencia +";");
            Log.logBd.info("Realizada consulta - editarArticulo()");
            
            if(codigo > 0){
                hecho = true;
                Log.logBd.info("Consulta realizada con éxito - editarArticulo()");
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en editarArticulo(): " + error);
            Log.logBd.error("                   SQL State - " + error.getSQLState());
            Log.logBd.error("                   ErrorCode - " + error.getErrorCode());
        }
        
        return hecho;
    }
    
    /**
     * Dado el codigo de referencia borra el articulo
     *
     * @return Devuelve un boolean que dice si se ha podido realizar la operacion
     */
    public boolean eliminarArticulo(int codigoReferencia){
        boolean hecho = false;
        Log.logBd.info("CONSULTA EliminarArticulo");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - eliminarArticulo()");
            Statement s = conexion.createStatement();
            int codigo = s.executeUpdate("DELETE from articulo where codigo_ref="+ codigoReferencia);
            
            if(codigo > 0){
                hecho = true;
                Log.logBd.info("Consulta realizada con éxito - eliminarArticulo()");
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en editarArticulo(): " + error);
            Log.logBd.error("                   SQL State - " + error.getSQLState());
            Log.logBd.error("                   ErrorCode - " + error.getErrorCode());
        }
        
        return hecho;
    }
    
    /**
     * Actualiza el stock del articulo dado
     * @param codigoRef
     * @param stock
     * @return 
     */
    public boolean actualizarStock(int codigoRef,int stock){
        boolean hecho = false;
        Log.logBd.info("CONSULTA actualizarStock");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - actualizarStock()");
            Statement s = conexion.createStatement();
            int codigo = s.executeUpdate("UPDATE articulo SET stock="+ stock +" WHERE codigo_ref="+ codigoRef +";");
            Log.logBd.info("Realizada consulta - actualizarStock()");
            
            if(codigo > 0){
                hecho = true;
                Log.logBd.info("Consulta realizada con éxito - actualizarStock()");
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en actualizarStock(): " + error);
            Log.logBd.error("                   SQL State - " + error.getSQLState());
            Log.logBd.error("                   ErrorCode - " + error.getErrorCode());
        }
        
        return hecho;
    }
    
    /**
     * Dada una palabra busca en la base de datos articulos que contengan esa palabra
     * 
     * @param palabraClave
     * @return Devuelve una lista de los artículos encontrados que coinciden
     */
    public ArrayList<Articulo> buscarArticuloPalabraClave(String palabraClave){
        ArrayList<Articulo> listaArticulos = new ArrayList<>();
        try {
            Log.logBd.info("CONSULTA BuscarArticuloPalabraClave");
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - buscarArticuloPalabraClave()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from articulo;");        
        
            while(resultado.next()){
                String modelo = resultado.getString("modelo").toLowerCase();
                int busqueda = modelo.indexOf(palabraClave.toLowerCase());
                
                //Si se ha encontrado la palabra clave añadimos el artículo
                if(busqueda != -1 && !modelo.contains("custom-")) {
                    Articulo articulo = new Articulo();
                    articulo.setModelo(modelo);
                    articulo.setCodigo_ref(resultado.getInt("codigo_ref"));
                    articulo.setDescripcion(resultado.getString("descripcion"));
                    articulo.setID_Tienda(resultado.getInt("id_tienda"));
                    articulo.setPrecio(resultado.getInt("precio"));
                    articulo.setRutaImagen(resultado.getString("rutaImagen"));
                    articulo.setStock(resultado.getInt("stock"));
                    
                    listaArticulos.add(articulo);
                }
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en buscarArticuloPalabraClave(): " + error);
            Log.logBd.error("                               SQL State - " + error.getSQLState());
            Log.logBd.error("                               ErrorCode - " + error.getErrorCode());
        }
        
        return listaArticulos;
    }
    
}
