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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import util.Conexion;
import util.Log;

/**
 *
 * @author Alberto
 */
public class PedidoDao {

    private Connection conexion;

    public ArrayList<Pedido> getAllPedidos() {
        ArrayList<Pedido> listaPedidos = new ArrayList<>();
        Log.logBd.info("CONSULTA GetAllPedidos");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - getAllPedidos()");

            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from pedido;");
            Log.logBd.info("Realizada consulta - getAllPedidos()");

            while (resultado.next()) {
                Pedido pedido = new Pedido();
                pedido.setEmail_cliente(resultado.getString("email_cliente"));
                pedido.setIdPedido(resultado.getInt("id"));
                pedido.setFecha(resultado.getString("fecha"));
                pedido.setPrecio_total(resultado.getFloat("precio_total"));

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

    public Pedido getPedido(int idPedido) {
        Pedido pedido = new Pedido();
        Log.logBd.info("CONSULTA GetPedido");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - getPedido()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from pedido where id=" + idPedido + ";");
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

    public boolean hacerPedidoCarro(ArrayList<Integer> codigos, String precio, String email, int idPedido) {
        boolean hecho = false;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();

        Log.logBd.info("CONSULTA hacerPedidoCarro");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - hacerPedidoCarro()");
            Statement s = conexion.createStatement();
            String cadenaCodigos = "";

            for (int i = 0; i < codigos.size(); i++) {
                if (i == 0) {
                    cadenaCodigos = String.valueOf(codigos.get(i));
                } else {
                    cadenaCodigos = cadenaCodigos + "," + codigos.get(i);
                }

            }

            int codigo = s.executeUpdate("INSERT INTO Pedido VALUES(" + Float.parseFloat(precio) + " ,'"
                    + formatter.format(date) + "'," + idPedido + ",'{" + cadenaCodigos + "}','" + email + "');");
            Log.logBd.info("Realizada consulta - hacerPedidoCarro()");

            if (codigo > 0) {
                hecho = true;
                Log.logBd.info("Consulta realizada con éxito - hacerPedidoCarro()");
            }

        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en hacerPedidoCarro(): " + error);
            Log.logBd.error("              SQL State - " + error.getSQLState());
            Log.logBd.error("              ErrorCode - " + error.getErrorCode());
        }

        return hecho;
    }

    public boolean hacerPedido(int codigoRef, String precio, String email, int idPedido) {
        boolean hecho = false;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();

        Log.logBd.info("CONSULTA hacerPedido");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - hacerPedido()");
            Statement s = conexion.createStatement();
            
            int codigo = s.executeUpdate("INSERT INTO Pedido VALUES(" + Float.parseFloat(precio) + " ,'"
                    + formatter.format(date) + "'," + idPedido + ",'{" + codigoRef + "}','" + email + "');");
            Log.logBd.info("Realizada consulta - hacerPedido()");

            if (codigo > 0) {
                hecho = true;
                Log.logBd.info("Consulta realizada con éxito - hacerPedido()");
            }

        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en hacerPedido(): " + error);
            Log.logBd.error("              SQL State - " + error.getSQLState());
            Log.logBd.error("              ErrorCode - " + error.getErrorCode());
        }

        return hecho;
    }

    /**
     * Retorna el id maximo
     *
     * @return Devuelve un entero
     */
    public int getIdPedidoMax() {
        int idMax = -1;
        Log.logBd.info("CONSULTA getIdPedidoMax");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - getIdPedidoMax()");
            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select MAX(id) as maximo from pedido");
            Log.logBd.info("Realizada consulta - getIdPedidoMax()");

            if (resultado.next()) {
                idMax = resultado.getInt("maximo") + 1;
            }

        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getIdPedidoMax(): " + error);
            Log.logBd.error("                 SQL State - " + error.getSQLState());
            Log.logBd.error("                 ErrorCode - " + error.getErrorCode());
        }

        Log.logBd.info("Consulta realizada con éxito - getIdPedidoMax()");
        return idMax;
    }

}
