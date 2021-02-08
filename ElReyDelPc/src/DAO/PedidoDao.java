
package DAO;

import Model.Articulos.PcTorre;
import StatePedido.Pedido;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import util.Conexion;
import SingletonLog.Log;
import StatePedido.EstadoEnviado;
import StatePedido.EstadoPreparacion;
import StatePedido.EstadoRecibido;

/**
 * DAO para las operaciones de datos de la tabla y objeto pedido.
 * 
 */
public class PedidoDao {

    private Connection conexion;

    /**
     * Consulta para conseguir información sobre todos los pedidos de la base de datos.
     * 
     * @return lista de pedidos completa
     */
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
                pedido.setFecha(resultado.getDate("fecha"));
                pedido.setPrecio_total(resultado.getFloat("precio_total"));
                pedido.setListaArticulos(resultado.getString("codigos"));
                
                int estado = resultado.getInt("estado");
                
                //Pedido en preparacion
                if(estado == 0){
                    EstadoPreparacion estadoPedido = new EstadoPreparacion();
                    pedido.setEstado(estadoPedido);
                    listaPedidos.add(pedido);
                }
                //Pedido enviado
                else if(estado == 1){
                    EstadoEnviado estadoPedido = new EstadoEnviado();
                    pedido.setEstado(estadoPedido);
                    listaPedidos.add(pedido);
                }
                //Pedido confirmada recepcion
                else if(estado == 2){
                    EstadoRecibido estadoPedido = new EstadoRecibido();
                    pedido.setEstado(estadoPedido);
                    listaPedidos.add(pedido);
                }
            }

        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getAllPedidos(): " + error);
            Log.logBd.error("                  SQL State - " + error.getSQLState());
            Log.logBd.error("                  ErrorCode - " + error.getErrorCode());
        }

        Log.logBd.info("Consulta realizada con éxito - getAllPedidos()");
        return listaPedidos;
    }
    
    /**
     * Consulta para conseguir los pedidos de un cliente en concreto.
     * 
     * @param email
     * @return lista de pedidos del cliente
     */
    public ArrayList<Pedido> getAllPedidosCliente(String email) {
        ArrayList<Pedido> listaPedidos = new ArrayList<>();
        Log.logBd.info("CONSULTA getAllPedidosCliente");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - getAllPedidosCliente()");

            Statement s = conexion.createStatement();
            ResultSet resultado = s.executeQuery("select * from pedido where email_cliente='"+email+"';");
            Log.logBd.info("Realizada consulta - getAllPedidosCliente()");

            while (resultado.next()) {
                Pedido pedido = new Pedido();
                pedido.setEmail_cliente(resultado.getString("email_cliente"));
                pedido.setIdPedido(resultado.getInt("id"));
                pedido.setFecha(resultado.getDate("fecha"));
                pedido.setPrecio_total(resultado.getFloat("precio_total"));
                pedido.setListaArticulos(resultado.getString("codigos"));

                int estado = resultado.getInt("estado");
                
                //Pedido en preparacion
                if(estado == 0){
                    EstadoPreparacion estadoPedido = new EstadoPreparacion();
                    pedido.setEstado(estadoPedido);
                    listaPedidos.add(pedido);
                }
                //Pedido enviado
                else if(estado == 1){
                    EstadoEnviado estadoPedido = new EstadoEnviado();
                    pedido.setEstado(estadoPedido);
                    listaPedidos.add(pedido);
                }
                //Pedido confirmada recepcion
                else if(estado == 2){
                    EstadoRecibido estadoPedido = new EstadoRecibido();
                    pedido.setEstado(estadoPedido);
                    listaPedidos.add(pedido);
                }
            }

        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getAllPedidos(): " + error);
            Log.logBd.error("                  SQL State - " + error.getSQLState());
            Log.logBd.error("                  ErrorCode - " + error.getErrorCode());
        }

        Log.logBd.info("Consulta realizada con éxito - getAllPedidosCliente()");
        return listaPedidos;
    }

    /**
     * Dado el id de un pedido, se retorna la información referente a ese pedido.
     * 
     * @param idPedido
     * @return 
     */
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
                pedido.setFecha(resultado.getDate("fecha"));
                pedido.setPrecio_total(resultado.getInt("precio_total"));
                pedido.setListaArticulos(resultado.getString("codigos"));
                
                int estado = resultado.getInt("estado");
                
                //Pedido en preparacion
                if(estado == 0){
                    EstadoPreparacion estadoPedido = new EstadoPreparacion();
                    pedido.setEstado(estadoPedido);
                }
                //Pedido enviado
                else if(estado == 1){
                    EstadoEnviado estadoPedido = new EstadoEnviado();
                    pedido.setEstado(estadoPedido);
                }
                //Pedido confirmada recepcion
                else if(estado == 2){
                    EstadoRecibido estadoPedido = new EstadoRecibido();
                    pedido.setEstado(estadoPedido);
                }
            }
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en getPedido(): " + error);
            Log.logBd.error("              SQL State - " + error.getSQLState());
            Log.logBd.error("              ErrorCode - " + error.getErrorCode());
        }

        Log.logBd.info("Consulta realizada con éxito - getPedido()");
        return pedido;
    }

    /**
     * Crea un nuevo pedido el cual tendrá, los artículos comprados, el precio, el email del cliente y 
     * su id.
     * 
     * @param codigos
     * @param precio
     * @param email
     * @param idPedido
     * @return 
     */
    public boolean hacerPedidoCarro(ArrayList<Integer> codigos, String precio, String email, int idPedido) {
        boolean hecho = false;
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
                    + date + "'," + idPedido + ",'{" + cadenaCodigos + "}', 0,'" + email + "');");
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

    /**
     * Inserta un nuevo pedido en la base de datos con los parametros dados.
     *
     * @param codigoRef
     * @param precio
     * @param email
     * @param idPedido
     * @return
     */
    public boolean hacerPedido(int codigoRef, String precio, String email, int idPedido) {
        boolean hecho = false;
        Date date = new Date();

        Log.logBd.info("CONSULTA hacerPedido");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - hacerPedido()");
            Statement s = conexion.createStatement();

            int codigo = s.executeUpdate("INSERT INTO Pedido VALUES(" + Float.parseFloat(precio) + " ,'"
                    + date + "'," + idPedido + ",'{" + codigoRef + "}', 0,'" + email + "');");
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
     * Retorna el id maximo de la tabla pedidos.
     *
     * @return 
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

    
    /**
     * Crea un pedido nuevo con una torre de pc montada por el usuario.
     *
     * @param torre
     * @param idPedido
     * @param email
     * @return Devuelve un boolean si se ha podido insertar
     */
    public boolean hacerPedidoCustom(PcTorre torre,int idPedido, String email) {
        boolean hecho = false;
        Date date = new Date();
        Log.logBd.info("CONSULTA hacerPedidoCustom");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - hacerPedidoCustom()");
            Statement s = conexion.createStatement();

            int codigo = s.executeUpdate("INSERT INTO Pedido VALUES(" + torre.getPrecio() + " ,'"
                    + date + "'," + idPedido + ",'{" + torre.getCodigo_ref() + "}', 0,'" + email + "');");
            Log.logBd.info("Realizada consulta - hacerPedidoCustom()");

            if (codigo > 0) {
                hecho = true;
                Log.logBd.info("Consulta realizada con éxito - hacerPedidoCustom()");
            }
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en hacerPedidoCustom(): " + error);
            Log.logBd.error("              SQL State - " + error.getSQLState());
            Log.logBd.error("              ErrorCode - " + error.getErrorCode());
        }

        return hecho;
    }
    
    /**
     * Elimina un pedido de la base de datos.
     *
     * @param idPedido
     * @return Devuelve si se ha podido eliminar el pedido
     */
    public boolean eliminarPedido(int idPedido) {
        boolean hecho = false;
        Log.logBd.info("CONSULTA EliminarPedido");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - eliminarPedido()");
            Statement s = conexion.createStatement();

            int codigo = s.executeUpdate("DELETE from pedido where id="+ idPedido);

            if (codigo > 0) {
                hecho = true;
                Log.logBd.info("Consulta realizada con éxito - eliminarPedido()");
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en eliminarPedido(): " + error);
            Log.logBd.error("                   SQL State - " + error.getSQLState());
            Log.logBd.error("                   ErrorCode - " + error.getErrorCode());
        }

        return hecho;
    }
    
    /**
     * Cambia el estado de un pedido.
     * El estado puede ser preparacion-0, enviado-1 o recibido-2.
     *
     * @param idPedido
     * @param estado
     * @return Devuelve si se ha podido modificar el estado
     */
    public boolean cambiarEstadoPedido(int idPedido, int estado) {
        boolean hecho = false;
        Log.logBd.info("CONSULTA CambiarEstadoPedido");
        try {
            conexion = Conexion.getConexion();
            Log.logBd.info("Realizada conexion - cambiarEstadoPedido()");
            Statement s = conexion.createStatement();
            
            int codigo = s.executeUpdate("UPDATE pedido SET estado="+ estado +" where id="+ idPedido);

            if (codigo > 0) {
                hecho = true;
                Log.logBd.info("Consulta realizada con éxito - cambiarEstadoPedido()");
            }
            
        } catch (SQLException error) {
            Log.logBd.error("ERROR SQL en cambiarEstadoPedido(): " + error);
            Log.logBd.error("                        SQL State - " + error.getSQLState());
            Log.logBd.error("                        ErrorCode - " + error.getErrorCode());
        }

        return hecho;
    }
}
