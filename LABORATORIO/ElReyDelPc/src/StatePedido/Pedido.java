
package StatePedido;

import java.util.ArrayList;
import java.util.Date;

/**
 * Objeto pedido.
 * Implementa el estado del pedido.
 *
 */
public class Pedido {
    
    private float precio_total;
    private Date fecha;
    private String email_cliente;
    private int idPedido;
    private ArrayList<Integer> listaArticulos;
    private Estado estado;

    public Pedido(float precio_total, Date fecha, String email_cliente, int idPedido, ArrayList<Integer> listaArticulos, Estado estado) {
        this.precio_total = precio_total;
        this.fecha = fecha;
        this.email_cliente = email_cliente;
        this.idPedido = idPedido;
        this.listaArticulos = listaArticulos;
        this.estado = estado;
    }
    
    public Pedido(Pedido p) {
        this.precio_total = p.getPrecio_total();
        this.fecha = p.getFecha();
        this.email_cliente = p.getEmail_cliente();
        this.idPedido = p.getIdPedido();
        this.listaArticulos = p.getListaArticulos();
        this.estado = p.getEstado();
    }
    
    public Pedido() {
    }

    /**
     * Get the value of estado
     *
     * @return the value of estado
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Set the value of estado
     *
     * @param estado new value of estado
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }


    /**
     * Get the value of listaArticulos
     *
     * @return the value of listaArticulos
     */
    public ArrayList<Integer> getListaArticulos() {
        return listaArticulos;
    }

    /**
     * Set the value of listaArticulos
     *
     * @param articulos string con los codigos de referencia de los articulos de un pedido
     */
    public void setListaArticulos(String articulos) {
        ArrayList<Integer> listaFinal = new ArrayList<>();
        
        String[] listaCodigos = articulos.substring(1, articulos.length()-1).split(",");
        for (String codReferencia : listaCodigos) {
            int codigo = Integer.parseInt(codReferencia);
            listaFinal.add(codigo);
        }
        
        this.listaArticulos = listaFinal;
    }


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
    public Date getFecha() {
        return fecha;
    }

    /**
     * Set the value of fecha
     *
     * @param fecha new value of fecha
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }


    /**
     * Get the value of precio_total
     *
     * @return the value of precio_total
     */
    public float getPrecio_total() {
        return precio_total;
    }

    /**
     * Set the value of precio_total
     *
     * @param precio_total new value of precio_total
     */
    public void setPrecio_total(float precio_total) {
        this.precio_total = precio_total;
    }

}
