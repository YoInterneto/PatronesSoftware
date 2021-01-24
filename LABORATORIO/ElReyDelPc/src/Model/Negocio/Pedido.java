/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.Negocio;

/**
 *
 * @author Alberto
 */
public class Pedido {
    
    private int precio_total;
    private String fecha;
    private String email_cliente;
    private int idPedido;

    /**
     * Get the value of idPedido
     *
     * @return the value of idPedido
     */
    public int getIdPedido() {
        return idPedido;
    }

    /**
     * Set the value of idPedido
     *
     * @param idPedido new value of idPedido
     */
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }


    /**
     * Get the value of email_cliente
     *
     * @return the value of email_cliente
     */
    public String getEmail_cliente() {
        return email_cliente;
    }

    /**
     * Set the value of email_cliente
     *
     * @param email_cliente new value of email_cliente
     */
    public void setEmail_cliente(String email_cliente) {
        this.email_cliente = email_cliente;
    }


    /**
     * Get the value of fecha
     *
     * @return the value of fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * Set the value of fecha
     *
     * @param fecha new value of fecha
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


    /**
     * Get the value of precio_total
     *
     * @return the value of precio_total
     */
    public int getPrecio_total() {
        return precio_total;
    }

    /**
     * Set the value of precio_total
     *
     * @param precio_total new value of precio_total
     */
    public void setPrecio_total(int precio_total) {
        this.precio_total = precio_total;
    }

}
