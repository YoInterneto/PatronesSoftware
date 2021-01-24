package Controller;

import DAO.DiscoDuroDao;
import DAO.GraficaDao;
import DAO.MemoriaRAMDao;
import DAO.Placa_baseDao;
import DAO.ProcesadorDao;
import DAO.UsuarioDao;
import Model.Articulos.Grafica;
import Model.Articulos.Memoria_RAM;
import Model.Articulos.Placa_base;
import Model.Articulos.Procesador;
import Model.Usuario.Cliente;
import Util.ListaDinamicaImagen;

import Views.InicioCliente;
import Views.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ClienteController implements ActionListener {

    private InicioCliente client;
    private Cliente cliente;
    

    private UsuarioDao consulta = new UsuarioDao();
    private ProcesadorDao daoCpu;
    private Placa_baseDao daoPlaca;
    private MemoriaRAMDao daoRam;
    private GraficaDao daoGrafica;
    private DiscoDuroDao daoDisco;

    public ClienteController(InicioCliente clientVista, Cliente cliente) {
        this.cliente = cliente;
        this.client = clientVista;
        daoCpu = new ProcesadorDao();
    }

    public void iniciar() {
        client.setTitle("INICIO - CLIENTE");
        client.setLocationRelativeTo(null);
        client.nombreCliente.setText(cliente.getNombre());
        //client.fotoInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fotopc.png")));
        this.client.btnProducto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                client.panelInicio.setVisible(false);
                client.panelCarro.setVisible(false);
                client.panelPerfil.setVisible(false);
                client.panelMonta.setVisible(false);
                client.panelProducto.setVisible(false);
                client.panelArticulo.setVisible(true);
                //iniciarPanelProducto();
            }
        });
        this.client.btnInicio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                client.panelInicio.setVisible(true);
                client.panelCarro.setVisible(false);
                client.panelPerfil.setVisible(false);
                client.panelMonta.setVisible(false);
                client.panelProducto.setVisible(false);
                client.panelArticulo.setVisible(false);

            }
        });
        this.client.btnCarro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                client.panelInicio.setVisible(false);
                client.panelCarro.setVisible(true);
                client.panelPerfil.setVisible(false);
                client.panelMonta.setVisible(false);
                client.panelProducto.setVisible(false);
                client.panelArticulo.setVisible(false);
                hola();
            }
        });
        this.client.btnMontar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                client.panelInicio.setVisible(false);
                client.panelCarro.setVisible(false);
                client.panelPerfil.setVisible(false);
                client.panelMonta.setVisible(true);
                client.panelProducto.setVisible(false);
                client.panelArticulo.setVisible(false);
                iniciarPanelMontar();
            }
        });
        this.client.btnPerfil.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                client.panelInicio.setVisible(false);
                client.panelCarro.setVisible(false);
                client.panelPerfil.setVisible(true);
                client.panelMonta.setVisible(false);
                client.panelProducto.setVisible(false);
                client.panelArticulo.setVisible(false);
                iniciarPanelPerfil();
            }
        });
        this.client.btnCerrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                Login loginVista = new Login();
        
                LoginController login = new LoginController(loginVista);
        
                login.iniciar();
                client.setVisible(false);
                loginVista.setVisible(true);
            }
        });
        this.client.listaPedidos.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting()) {
                    System.out.println("DATO SELECCIONADO "+ client.listaPedidos.getSelectedIndex());
                }
            }
        });
    }

    public void iniciarPanelProducto(){
        client.productoSeleccionado.setText("NombreProducto");
        
        client.datoModelo.setText("datoModelo");
        client.codigo_ref.setText("codigo");
        client.datoStock.setText("300");
        client.descripcion.setText("blbla");
        client.precio.setText("32$");
        
        //client.imgProducto.setIcon("");
        if (true){ // En funcion del numero de atributos
//            client.datoDni.setText(empleado.getDNI());
//            client.idTienda.setText(""+tienda.getId_tienda());
//            client.nombreTienda.setText(tienda.getNombre());
        }
        
    }
    
    public void iniciarPanelPerfil(){
        client.nombrePerfil.setText(cliente.getNombre());
        client.apellidoPerfil.setText(cliente.getApellido());
        client.datoTelefono.setText(String.valueOf(cliente.getTelefono()));
        client.datoDireccion.setText(cliente.getDireccion());
        client.datoTarjeta.setText(cliente.getTarjeta());
        client.datoEmail.setText(cliente.getEmail());   
    }
    
    public void iniciarPanelMontar(){
        
        ArrayList<Procesador> listaCpu = daoCpu.getAllProcesadores();
        for (int i = 0 ; i<listaCpu.size(); i++) {
            client.cpuBox.addItem(listaCpu.get(i).getModelo()+ " - " + listaCpu.get(i).getSocket() + " - " +listaCpu.get(i).getPrecio() + " €" );
        }
        ArrayList<Placa_base> listaPlaca = daoPlaca.getAllProcesadores();
        for (int i = 0 ; i<listaCpu.size(); i++) {
            client.cpuBox.addItem(listaPlaca.get(i).getModelo()+ " - " + listaPlaca.get(i).getSocket() + " - " +listaPlaca.get(i).getPrecio() + " €" );
        }
        ArrayList<Memoria_RAM> listaRam = daoRam.getAllProcesadores();
        for (int i = 0 ; i<listaCpu.size(); i++) {
            client.cpuBox.addItem(listaRam.get(i).getModelo()+ " - " + listaRam.get(i).getSocket() + " - " +listaRam.get(i).getPrecio() + " €" );
        }
        ArrayList<Grafica> listaGrafica = daoGrafica.getAllProcesadores();
        for (int i = 0 ; i<listaCpu.size(); i++) {
            client.cpuBox.addItem(listaGrafica.get(i).getModelo()+ " - " + listaGrafica.get(i).getSocket() + " - " +listaGrafica.get(i).getPrecio() + " €" );
        }
        
    }
    
    public void hola(){
        DefaultListModel listModel = new DefaultListModel();
        
        ArrayList<String> lista = new ArrayList<>();
        ArrayList<String> listaRuta = new ArrayList<>();
        
        lista.add("1 GRAFICA MODELO1 389€");
        lista.add("GRAFICA   MODELO 2   389€");
        lista.add("GRAFICA   MODELO 3   389€");
        lista.add("GRAFICA   MODELO 4   389€");
        lista.add("GRAFICA   MODELO 5   389€");
        lista.add("GRAFICA   MODELO 6   389€");
        lista.add("GRAFICA   MODELO 7   389€");
        lista.add("GRAFICA   MODELO 8   389€");
        lista.add("GRAFICA   MODELO 9   389€");
        lista.add("GRAFICA   MODELO 10   389€");
        lista.add("GRAFICA   MODELO 11  389€");
        lista.add("GRAFICA   MODELO 12  389€");
        lista.add("GRAFICA   MODELO 13  389€");
        lista.add("GRAFICA   MODELO 14  389€");
        lista.add("GRAFICA   MODELO 15  389€");
        lista.add("GRAFICA   MODELO 16  389€");
        
        listaRuta.add("/images/aaaa.png");
        listaRuta.add("/images/perfil.png");
        listaRuta.add("/images/perfil.png");
        listaRuta.add("/images/pc.png");
        listaRuta.add("/images/perfil.png");
        listaRuta.add("/images/torre.png");
        listaRuta.add("/images/perfil.png");
        listaRuta.add("/images/nvidia_23133.png");
        listaRuta.add("/images/perfil.png");
        listaRuta.add("/images/nvidia_23133.png");
        listaRuta.add("/images/perfil.png");
        listaRuta.add("/images/a.png");
        listaRuta.add("/images/a.png");
        listaRuta.add("/images/a.png");
        listaRuta.add("/images/aa.png");
        listaRuta.add("/images/aa.png");        
        
        for(int i = 0; i<lista.size(); i++){
            //En vez de i añadir cod referencia
            listModel.add(i, lista.get(i));
            
        }
        
        client.listaPedidos.setModel(listModel);
        String titulo = "TITULO"; //PON EL TITULO QUE QUIERAS
        client.listaPedidos.setCellRenderer(new ListaDinamicaImagen(lista, listaRuta, titulo));
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
