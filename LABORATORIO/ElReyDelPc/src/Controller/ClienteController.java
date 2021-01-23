
package Controller;

import DAO.UsuarioDao;
import Model.Usuario.Cliente;

import Views.InicioCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;



public class ClienteController implements ActionListener{
    private InicioCliente client;
    private Cliente cliente;
    
    
    private UsuarioDao consulta = new UsuarioDao();
    
    public ClienteController(InicioCliente clientVista, Cliente cliente){
        this.cliente = cliente;
        this.client = clientVista;
        
    }
    
    public void iniciar(){
        client.setTitle("INICIO - CLIENTE");
        client.setLocationRelativeTo(null);
        this.client.btnProducto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource()==client.btnProducto){
                    client.panelInicio.setVisible(false);
                    client.panelArticulo.setVisible(true);
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    

   
    
}
