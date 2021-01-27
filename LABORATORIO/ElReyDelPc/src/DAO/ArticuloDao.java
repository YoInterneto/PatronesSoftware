
package DAO;

import Model.Articulos.*;
import java.awt.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.Conexion;
import util.Log;


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
                	
                    Articulo articulo = new Articulo();
                  
                    articulo.setCodigo_ref(rs.getInt("Codigo_ref"));
                    articulo.setDescripcion(rs.getString("Descripcion"));
                    articulo.setModelo(rs.getString("Modelo"));
                    articulo.setPrecio(rs.getFloat("Precio"));
                    articulo.setRutaImagen(rs.getString("RutaImagen"));
                    articulo.setStock(rs.getInt("Stock"));
                    
                    articulodb.add(articulo);
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
     * 
     * @param nota
     * @param codigoReferencia
     * @return Devuelve true si la consula se ha realizado correctamente
     */
    public boolean insertarEvaluacion(int nota, String email,int codigoReferencia){
        boolean hecho = false;
        try {
            Log.logBd.info("CONSULTA insertarEvaluacion");
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - insertarEvaluacion()");
            Statement s = conexion.createStatement();
            int codigo = s.executeUpdate("INSERT into Puntuacion VALUES("+ nota +",'"+ email +"',"+codigoReferencia+");");          
        
            if(codigo>0){
                hecho = true;
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en insertarEvaluacion(): " + error);
            Log.logBd.error("               SQL State - " + error.getSQLState());
            Log.logBd.error("               ErrorCode - " + error.getErrorCode());
        }
        
        return hecho;
    }
    /**
     * 
     * @param email
     * @param codigoReferencia
     * @return Devuelve true si la consula tiene un valor devuelto
     */
    public boolean comprobarEvaluacion(String email,int codigoReferencia){
        boolean hecho = false;
        try {
            Log.logBd.info("CONSULTA comprobarEvaluacion");
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - comprobarEvaluacion()");
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery("select * from Puntuacion where email='"+ email+"' and codigo_ref="+codigoReferencia+";");          
            if (rs.next()){
                hecho = true;
            }
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en comprobarEvaluacion(): " + error);
            Log.logBd.error("               SQL State - " + error.getSQLState());
            Log.logBd.error("               ErrorCode - " + error.getErrorCode());
        }
        
        return hecho;
    }
    
    /**
     * Calcula la media de las puntuaciones del producto con el codigo 
     * de referencia dado
     * @param codigo
     * @return 
     */
    public float mediaEvaluacion(int codigo){
        float notaMedia = 0;
        try {
            Log.logBd.info("CONSULTA mediaEvaluacion");
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - mediaEvaluacion()");
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery("select round(avg(nota),2) from Puntuacion where codigo_ref="+ codigo+";");          
            if (rs.next()){
                notaMedia = rs.getFloat("round");
            }
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en mediaEvaluacion(): " + error);
            Log.logBd.error("               SQL State - " + error.getSQLState());
            Log.logBd.error("               ErrorCode - " + error.getErrorCode());
        }
        return notaMedia;
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
                if(busqueda != -1) {
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
     * Dado el codigo de un caja realiza una consulta en la base de
     * datos y devuelve todos los datos correspondientes a dicho caja.
     *
     * @param codigo
     * @return Devuelve un objeto de tipo Caja
     */
    public Caja getCaja(int codigo){
        Caja caja = new Caja();
        Log.logBd.info("CONSULTA getCaja");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - getCaja()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from caja where codigo_ref='" + codigo + "';");
            Log.logBd.info("Realizada consulta - getCaja()");

            while (resultado.next()) {
                caja.setCodigo_ref(codigo);
                caja.setModelo(resultado.getString("Modelo"));
                caja.setPrecio(Float.parseFloat(resultado.getString("Precio")));
                caja.setDescripcion(resultado.getString("Descripcion"));
                caja.setStock(Integer.parseInt(resultado.getString("Stock")));
                caja.setRutaImagen(resultado.getString("rutaImagen"));
                
                caja.setCristal(Boolean.parseBoolean(resultado.getString("Cristal")));
                
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getCaja: " + error);
            Log.logBd.error("                SQL State - " + error.getSQLState());
            Log.logBd.error("                ErrorCode - " + error.getErrorCode());
        }
        
        Log.logBd.info("Consulta realizada con éxito - getCaja");
        return caja;
    }
    
    /**
     * Dado el codigo de un raton realiza una consulta en la base de
     * datos y devuelve todos los datos correspondientes a dicho raton.
     *
     * @param codigo
     * @return Devuelve un objeto de tipo Raton
     */
    public Raton getRaton(int codigo){
        Raton raton = new Raton();
        Log.logBd.info("CONSULTA getRaton");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - getRaton()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from raton where codigo_ref='" + codigo + "';");
            Log.logBd.info("Realizada consulta - getRaton()");

            while (resultado.next()) {
                raton.setCodigo_ref(codigo);
                raton.setModelo(resultado.getString("Modelo"));
                raton.setPrecio(Float.parseFloat(resultado.getString("Precio")));
                raton.setDescripcion(resultado.getString("Descripcion"));
                raton.setStock(Integer.parseInt(resultado.getString("Stock")));
                raton.setRutaImagen(resultado.getString("rutaImagen"));
                
                raton.setDPI(Integer.parseInt(resultado.getString("DPI")));
                raton.setPeso(Integer.parseInt(resultado.getString("Peso")));
                raton.setTipo(resultado.getString("Tipo"));
                              
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getRaton: " + error);
            Log.logBd.error("                SQL State - " + error.getSQLState());
            Log.logBd.error("                ErrorCode - " + error.getErrorCode());
        }
        
        Log.logBd.info("Consulta realizada con éxito - getRaton");
        return raton;
    }
    
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
     * Dado el codigo de un fuente realiza una consulta en la base de
     * datos y devuelve todos los datos correspondientes a dicho fuente.
     *
     * @param codigo
     * @return Devuelve un objeto de tipo Fuente_alimentacion
     */
    public Fuente_alimentacion getFuente(int codigo){
        Fuente_alimentacion fuente = new Fuente_alimentacion();
        Log.logBd.info("CONSULTA getFuente_alimentacion");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - getFuente_alimentacion()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from fuente_alimentacion where codigo_ref='" + codigo + "';");
            Log.logBd.info("Realizada consulta - getFuente_alimentacion()");

            while (resultado.next()) {
                fuente.setCodigo_ref(codigo);
                fuente.setModelo(resultado.getString("Modelo"));
                fuente.setPrecio(resultado.getFloat("Precio"));
                fuente.setDescripcion(resultado.getString("Descripcion"));
                fuente.setStock(Integer.parseInt(resultado.getString("Stock")));
                fuente.setRutaImagen(resultado.getString("rutaImagen"));
                
                fuente.setPotencia(Integer.parseInt(resultado.getString("Potencia")));
		fuente.setCertificacion(resultado.getString("Certificacion"));
 
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getFuente_alimentacion: " + error);
            Log.logBd.error("                SQL State - " + error.getSQLState());
            Log.logBd.error("                ErrorCode - " + error.getErrorCode());
        }
        
        Log.logBd.info("Consulta realizada con éxito - getFuente_alimentacion");
        return fuente;
    }
    
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
     * Dado el codigo de un ram realiza una consulta en la base de
     * datos y devuelve todos los datos correspondientes a dicho ram.
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
     * Dado el codigo de un placa realiza una consulta en la base de
     * datos y devuelve todos los datos correspondientes a dicho placa.
     *
     * @paplaca codigo
     * @return Devuelve un objeto de tipo Placa_base
     */
    public Placa_base getPlaca_base(int codigo){
        Placa_base placa = new Placa_base();
        Log.logBd.info("CONSULTA getPlaca_base");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - getPlaca_base()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from placa_base where codigo_ref='" + codigo + "';");
            Log.logBd.info("Realizada consulta - getPlaca_base()");

            while (resultado.next()) {
                placa.setCodigo_ref(codigo);
                placa.setModelo(resultado.getString("Modelo"));
                placa.setPrecio(Float.parseFloat(resultado.getString("Precio")));
                placa.setDescripcion(resultado.getString("Descripcion"));
                placa.setStock(Integer.parseInt(resultado.getString("Stock")));
                placa.setRutaImagen(resultado.getString("rutaImagen"));
                
                placa.setSocket(resultado.getString("Socket"));

            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getPlaca_base: " + error);
            Log.logBd.error("                SQL State - " + error.getSQLState());
            Log.logBd.error("                ErrorCode - " + error.getErrorCode());
        }
        
        Log.logBd.info("Consulta realizada con éxito - getPlaca_base");
        return placa;
    }
    
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
     * Dado el codigo de un monitor realiza una consulta en la base de
     * datos y devuelve todos los datos correspondientes a dicho monitor.
     *
     * @pamonitor codigo
     * @return Devuelve un objeto de tipo Monitor
     */
    public Monitor getMonitor(int codigo){
        Monitor monitor = new Monitor();
        Log.logBd.info("CONSULTA getMonitor");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - getMonitor()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from monitor where codigo_ref='" + codigo + "';");
            Log.logBd.info("Realizada consulta - getMonitor()");

            while (resultado.next()) {
                monitor.setCodigo_ref(codigo);
                monitor.setModelo(resultado.getString("Modelo"));
                monitor.setPrecio(Float.parseFloat(resultado.getString("Precio")));
                monitor.setDescripcion(resultado.getString("Descripcion"));
                monitor.setStock(Integer.parseInt(resultado.getString("Stock")));
                monitor.setRutaImagen(resultado.getString("rutaImagen"));
                
                monitor.setPanel(resultado.getString("Panel"));
                monitor.setPulgadas(Integer.parseInt(resultado.getString("Pulgadas")));
                monitor.setHz(Integer.parseInt(resultado.getString("Hz")));
 
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getMonitor: " + error);
            Log.logBd.error("                SQL State - " + error.getSQLState());
            Log.logBd.error("                ErrorCode - " + error.getErrorCode());
        }
        
        Log.logBd.info("Consulta realizada con éxito - getMonitor");
        return monitor;
    }
    /**
     * Dado el codigo de un webcam realiza una consulta en la base de
     * datos y devuelve todos los datos correspondientes a dicho webcam.
     *
     * @pawebcam codigo
     * @return Devuelve un objeto de tipo Webcam
     */
    public WebCam getWebcam(int codigo){
        WebCam webcam = new WebCam();
        Log.logBd.info("CONSULTA getWebCam");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - getWebCam()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from webcam where codigo_ref='" + codigo + "';");
            Log.logBd.info("Realizada consulta - getWebCam()");

            while (resultado.next()) {
                webcam.setCodigo_ref(codigo);
                webcam.setModelo(resultado.getString("Modelo"));
                webcam.setPrecio(Float.parseFloat(resultado.getString("Precio")));
                webcam.setDescripcion(resultado.getString("Descripcion"));
                webcam.setStock(Integer.parseInt(resultado.getString("Stock")));
                webcam.setRutaImagen(resultado.getString("rutaImagen"));
                
                webcam.setCalidad(resultado.getString("Calidad"));
 
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getWebCam: " + error);
            Log.logBd.error("                SQL State - " + error.getSQLState());
            Log.logBd.error("                ErrorCode - " + error.getErrorCode());
        }
        
        Log.logBd.info("Consulta realizada con éxito - getWebCam");
        return webcam;
    }
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
     * Dado el codigo de un torre realiza una consulta en la base de
     * datos y devuelve todos los datos correspondientes a dicho torre.
     *
     * @patorre codigo
     * @return Devuelve un objeto de tipo PcTorre
     */
    public PcTorre getPcTorre(int codigo){
        PcTorre torre = new PcTorre();
        Log.logBd.info("CONSULTA getPcTorre");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - getPcTorre()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from pctorre where codigo_ref='" + codigo + "';");
            Log.logBd.info("Realizada consulta - getPcTorre()");

            while (resultado.next()) {
                torre.setCodigo_ref(codigo);
                torre.setModelo(resultado.getString("Modelo"));
                torre.setPrecio(Float.parseFloat(resultado.getString("Precio")));
                torre.setDescripcion(resultado.getString("Descripcion"));
                torre.setStock(Integer.parseInt(resultado.getString("Stock")));
                torre.setRutaImagen(resultado.getString("rutaImagen"));
                
                torre.setNombre(resultado.getString("Nombre"));
 
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getPcTorre: " + error);
            Log.logBd.error("                SQL State - " + error.getSQLState());
            Log.logBd.error("                ErrorCode - " + error.getErrorCode());
        }
        
        Log.logBd.info("Consulta realizada con éxito - getPcTorre");
        return torre;
    }
}
