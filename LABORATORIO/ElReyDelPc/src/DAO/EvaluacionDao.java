package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.Conexion;
import SingletonLog.Log;

/**
 * DAO para las operaciones de datos para la evaluaciones de los clientes sobre un artículo.
 * 
 */
public class EvaluacionDao {
    
    private Connection conexion;
    
    /**
     * Crea una nueva evaluación de un producto.
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
     * Comprueba si el usuario ha evaluado con anterioridad el artículo.
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
     * Calcula la media de las puntuaciones del producto con el codigo de referencia dado.
     * 
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
}
