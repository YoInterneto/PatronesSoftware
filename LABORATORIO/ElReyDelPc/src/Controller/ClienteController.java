
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
                    client.panelCarro.setVisible(false);
                    client.panelPerfil.setVisible(false);
                    client.panelMonta.setVisible(false);
                    client.panelProducto.setVisible(false);
                    client.panelArticulo.setVisible(true);
                }
            }
        });
        this.client.btnInicio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource()==client.btnInicio){
                    client.panelInicio.setVisible(true);
                    client.panelCarro.setVisible(false);
                    client.panelPerfil.setVisible(false);
                    client.panelMonta.setVisible(false);
                    client.panelProducto.setVisible(false);
                    client.panelArticulo.setVisible(false);
                }
            }
        });
        this.client.btnCarro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource()==client.btnCarro){
                    client.panelInicio.setVisible(false);
                    client.panelCarro.setVisible(true);
                    client.panelPerfil.setVisible(false);
                    client.panelMonta.setVisible(false);
                    client.panelProducto.setVisible(false);
                    client.panelArticulo.setVisible(false);
                }
            }
         });
        this.client.btnMontar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {              
                 if(e.getSource()==client.btnMontar){
                    client.panelInicio.setVisible(false);
                    client.panelCarro.setVisible(false);
                    client.panelPerfil.setVisible(false);
                    client.panelMonta.setVisible(true);
                    client.panelProducto.setVisible(false);
                    client.panelArticulo.setVisible(false);
                }
            }
         });
        this.client.btnPerfil.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {              
                 if(e.getSource()==client.btnPerfil){
                    client.panelInicio.setVisible(false);
                    client.panelCarro.setVisible(false);
                    client.panelPerfil.setVisible(true);
                    client.panelMonta.setVisible(false);
                    client.panelProducto.setVisible(false);
                    client.panelArticulo.setVisible(false);
                }
            }
         });
        this.client.btnCerrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {                             
                 if(e.getSource()==client.btnCerrar){
                    //client.panelInicio.setVisible(true);
                    //client.panelArticulo.setVisible(false);
                }
            }
         });    
    }
            

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    

   
    
}
