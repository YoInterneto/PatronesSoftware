package Command;

import Command.memento.Conserje;
import Command.memento.Originador;
import Command.memento.Recuerdo;
import DAO.ArticuloDao;
import DAO.PedidoDao;
import Model.Articulos.Articulo;
import StatePedido.Pedido;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Implementa ComandoDeshacer. 
 * Proporciona el comportamiento necesario para borrar un pedido
 * @author Salvador Oton
 */
public class ComandoCambiarAlgo implements ComandoDeshacer {

    // Referencia al receptor.
    private Pedido pedido; 		
    // Gestor de recuerdos.
    private Conserje conserje = new Conserje();   
    
    private PedidoDao daoPedido = new PedidoDao();
    private ArticuloDao consultaArticulo = new ArticuloDao();

    @Override
    public Pedido getPedido() {
        return this.pedido;
    }

    @Override
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    /**
     * Cambia el lugar de la reunión por otro introducida por el usuario y
     * almacena el valor anterior de la reunión por si luego se quiere recuperar.
     * @param idPedido
     */    
    @Override
    public void ejecutar(int idPedido) {
        if (daoPedido.eliminarPedido(idPedido)) {
            //Actualizamos el stock de los articulos cuando se elimina el pedido
            ArrayList<Integer> listaArticulos =  pedido.getListaArticulos();
            for (int i = 0; i < listaArticulos.size(); i++) {
                Articulo articulo = consultaArticulo.getArticulo(listaArticulos.get(i));
                consultaArticulo.actualizarStock(articulo.getCodigo_ref(), articulo.getStock()+1);
            }
            
            //Creamos el recuerdo con el pedido eliminado
            Originador originador = new Originador();
            originador.setPedido(pedido);
            conserje.pushRecuerdo(originador.crearRecuerdo());
            
            JOptionPane.showMessageDialog(null, "Pedido " + idPedido + " eliminado con éxito.");
        }
        else{
            JOptionPane.showMessageDialog(null, "ERROR: No se ha podido eliminar el pedido " + idPedido + ".");
        }
    }

    /**
     * Recupera el valor del pedido borrado anteriormente por el usuario
     */
    @Override
    public void deshacer() {
        Recuerdo recuerdo = conserje.popRecuerdo();
        if (recuerdo != null){
            Pedido pedidoRecuerdo = recuerdo.getPedido();
            
            if(daoPedido.hacerPedidoCarro(pedidoRecuerdo.getListaArticulos(), String.valueOf(pedidoRecuerdo.getPrecio_total()), 
                    pedidoRecuerdo.getEmail_cliente(), pedidoRecuerdo.getIdPedido())){
                JOptionPane.showMessageDialog(null, "Pedido " + pedidoRecuerdo.getIdPedido() + " rehecho con éxito.");
                setPedido(null);
            }
            else{
                JOptionPane.showMessageDialog(null, "ERROR: No se ha podido rehacer el pedido " + pedidoRecuerdo.getIdPedido() + ".");
                
                //Si no se puede deshacer la eliminacion volvemos a meter el recuerdo del pedido
                Originador originador = new Originador();
                originador.setPedido(pedido);
                conserje.pushRecuerdo(originador.crearRecuerdo()); 
            }
        }
    }
}
