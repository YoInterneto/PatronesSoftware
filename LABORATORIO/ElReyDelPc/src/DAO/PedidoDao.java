/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Negocio.Pedido;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.Conexion;
import util.Log;

/**
 *
 * @author Alberto
 */
public class PedidoDao {
    
    private Connection conexion;
    
    public ArrayList<Pedido> getAllPedidos(){
        ArrayList<Pedido> listaPedidos = new ArrayList<>();
        Log.logBd.info("CONSULTA GetAllPedidos");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - getAllPedidos()");
            
            /*while(listaPedidos.size()<15){
                Pedido pedido = new Pedido();
                pedido.setEmail_cliente("email"+listaPedidos.size()+"@gmail.com");
                pedido.setIdPedido(listaPedidos.size());
                pedido.setFecha("12/03/2021");
                pedido.setPrecio_total(listaPedidos.size()*100);
                
                listaPedidos.add(pedido);
            }*/
            
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from pedido;");
            Log.logBd.info("Realizada consulta - getAllPedidos()");

            while (resultado.next()) {
                Pedido pedido = new Pedido();
                pedido.setEmail_cliente(resultado.getString("email_cliente"));
                pedido.setIdPedido(resultado.getInt("id"));
                pedido.setFecha(resultado.getString("fecha"));
                pedido.setPrecio_total(resultado.getInt("precio_total"));
                
                listaPedidos.add(pedido);
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getAllPedidos(): " + error);
            Log.logBd.error("                  SQL State - " + error.getSQLState());
            Log.logBd.error("                  ErrorCode - " + error.getErrorCode());
        }
        
        Log.logBd.info("Consulta realizada con éxito - getAllPedidos()");
        return listaPedidos;
    }
    
    public Pedido getPedido(int idPedido){
        Pedido pedido = new Pedido();
        Log.logBd.info("CONSULTA GetPedido");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - getPedido()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from pedido where id="+ idPedido +";");
            Log.logBd.info("Realizada consulta - getPedido()");

            while (resultado.next()) {
                pedido.setEmail_cliente(resultado.getString("email_cliente"));
                pedido.setIdPedido(resultado.getInt("id"));
                pedido.setFecha(resultado.getString("fecha"));
                pedido.setPrecio_total(resultado.getInt("precio_total"));
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getPedido(): " + error);
            Log.logBd.error("              SQL State - " + error.getSQLState());
            Log.logBd.error("              ErrorCode - " + error.getErrorCode());
        }
        
        Log.logBd.info("Consulta realizada con éxito - getPedido()");
        return pedido;
    }
}
