package Controller;

import DAO.CajaDao;
import DAO.DiscoDuroDao;
import DAO.FuenteDao;
import DAO.GraficaDao;
import DAO.MemoriaRAMDao;
import DAO.Placa_baseDao;
import DAO.ProcesadorDao;
import DAO.RatonDao;
import DAO.TecladoDao;
import DAO.UsuarioDao;
import DAO.WebCamDao;
import Model.Articulos.Caja;
import Model.Articulos.Disco_duro;
import Model.Articulos.Fuente_alimentacion;
import Model.Articulos.Grafica;
import Model.Articulos.Memoria_RAM;
import Model.Articulos.Placa_base;
import Model.Articulos.Procesador;
import Model.Articulos.Raton;
import Model.Articulos.Teclado;
import Model.Articulos.WebCam;
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
    private CajaDao daoCaja;
    private TecladoDao daoTeclado;
    private RatonDao daoRaton;
    private WebCamDao daoCam;
    private FuenteDao daoFuente;

    public ClienteController(InicioCliente clientVista, Cliente cliente) {
        this.cliente = cliente;
        this.client = clientVista;
        daoCpu = new ProcesadorDao();
        daoPlaca = new Placa_baseDao();
        daoRam = new MemoriaRAMDao();
        daoGrafica = new GraficaDao();
        daoDisco = new DiscoDuroDao();
        daoCaja = new CajaDao();
        daoTeclado = new TecladoDao();
        daoRaton = new RatonDao();
        daoCam = new WebCamDao();
        daoFuente = new FuenteDao();
    }

    public void iniciar() {
        client.setTitle("INICIO - CLIENTE");
        client.setLocationRelativeTo(null);
        client.nombreCliente.setText(cliente.getNombre());
        String[] a = client.cpuBox.getSelectedItem().toString().split(">");
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
        this.client.listaPedidos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    System.out.println("DATO SELECCIONADO " + client.listaPedidos.getSelectedIndex());
                }
            }
        });

        this.client.cpuBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculaPrecioMontaje();
            }
        });
    }

    public void iniciarPanelProducto() {
        client.productoSeleccionado.setText("NombreProducto");

        client.datoModelo.setText("datoModelo");
        client.codigo_ref.setText("codigo");
        client.datoStock.setText("300");
        client.descripcion.setText("blbla");
        client.precio.setText("32$");

        //client.imgProducto.setIcon("");
        if (true) { // En funcion del numero de atributos
//            client.datoDni.setText(empleado.getDNI());
//            client.idTienda.setText(""+tienda.getId_tienda());
//            client.nombreTienda.setText(tienda.getNombre());
        }

    }

    public void iniciarPanelPerfil() {
        client.nombrePerfil.setText(cliente.getNombre());
        client.apellidoPerfil.setText(cliente.getApellido());
        client.datoTelefono.setText(String.valueOf(cliente.getTelefono()));
        client.datoDireccion.setText(cliente.getDireccion());
        client.datoTarjeta.setText(cliente.getTarjeta());
        client.datoEmail.setText(cliente.getEmail());
    }

    public void iniciarPanelMontar() {

        rellenaComboBox();

    }

    /**
     * Coloca los valores de la base de datos en los comboBox al cual pertenecen
     * mediante una consulta se guarda la lista de valores y se insertan como
     * nuevo item.
     */
    private void rellenaComboBox() {

        ArrayList<Procesador> listaCpu = daoCpu.getAllProcesadores();
        for (int i = 0; i < listaCpu.size(); i++) {
            client.cpuBox.addItem(listaCpu.get(i).getModelo() + " - " + listaCpu.get(i).getSocket() + " -> " + listaCpu.get(i).getPrecio());
        }
        ArrayList<Placa_base> listaPlaca = daoPlaca.getAllPlacas();
        for (int i = 0; i < listaPlaca.size(); i++) {
            client.placaBox.addItem(listaPlaca.get(i).getModelo() + " - " + listaPlaca.get(i).getSocket() + " -> " + listaPlaca.get(i).getPrecio() + " €");
        }
        ArrayList<Memoria_RAM> listaRam = daoRam.getAllMemoria_RAM();
        for (int i = 0; i < listaRam.size(); i++) {
            client.ramBox.addItem(listaRam.get(i).getModelo() + " -> " + listaRam.get(i).getPrecio() + " €");
        }
        ArrayList<Grafica> listaGrafica = daoGrafica.getAllGraficas();
        for (int i = 0; i < listaGrafica.size(); i++) {
            client.graficaBox.addItem(listaGrafica.get(i).getModelo() + " -> " + listaGrafica.get(i).getPrecio() + " €");
        }
        ArrayList<Disco_duro> listaDisco = daoDisco.getAllDiscos();
        for (int i = 0; i < listaDisco.size(); i++) {
            client.discoBox.addItem(listaDisco.get(i).getModelo() + " - " + listaDisco.get(i).getTipo() + " ->" + listaDisco.get(i).getPrecio() + " €");
        }
        ArrayList<Fuente_alimentacion> listaFuente = daoFuente.getAllFuentes();
        for (int i = 0; i < listaFuente.size(); i++) {
            client.fuenteBox.addItem(listaFuente.get(i).getModelo() + " - " + listaFuente.get(i).getPotencia() + " W -> " + listaFuente.get(i).getPrecio() + " €");
        }
        ArrayList<Caja> listaCaja = daoCaja.getAllCajas();
        for (int i = 0; i < listaCaja.size(); i++) {
            client.cajaBox.addItem(listaCaja.get(i).getModelo() + " -> " + listaCaja.get(i).getPrecio() + " €");
        }
        ArrayList<Raton> listaRaton = daoRaton.getAllRatones();
        for (int i = 0; i < listaRaton.size(); i++) {
            client.ratonBox.addItem(listaRaton.get(i).getModelo() + " - " + listaRaton.get(i).getPeso() + " gr -> " + listaRaton.get(i).getPrecio() + " €");
        }
        ArrayList<Teclado> listaTeclado = daoTeclado.getAllTeclados();
        for (int i = 0; i < listaTeclado.size(); i++) {
            client.tecladoBox.addItem(listaTeclado.get(i).getModelo() + " -> " + listaTeclado.get(i).getPrecio() + " €");
        }
        ArrayList<WebCam> listaCam = daoCam.getAllWebCams();
        for (int i = 0; i < listaCam.size(); i++) {
            client.camBox.addItem(listaCam.get(i).getModelo() + " - " + listaCam.get(i).getCalidad() + " -> " + listaCam.get(i).getPrecio() + " €");
        }

    }

    private float calculaPrecioMontaje(){
        float total = 0;
        
        
        String[] precioCpu = client.cpuBox.getSelectedItem().toString().split(">");
        String[] precioPlaca = client.placaBox.getSelectedItem().toString().split(">");
        String[] precioRam = client.ramBox.getSelectedItem().toString().split(">");
        String[] precioGrafica = client.graficaBox.getSelectedItem().toString().split(">");
        String[] precioDisco = client.discoBox.getSelectedItem().toString().split(">");
        String[] precioFuente = client.fuenteBox.getSelectedItem().toString().split(">");
        String[] precioCaja = client.cajaBox.getSelectedItem().toString().split(">");
        String[] precioRaton = client.ratonBox.getSelectedItem().toString().split(">");
        String[] precioTeclado = client.tecladoBox.getSelectedItem().toString().split(">");
        String[] precioCam = client.camBox.getSelectedItem().toString().split(">");

        try{
            total = Float.parseFloat(precioCpu[1]);
        }catch (Exception e){
            
        }finally{
            client.precioTotalPc.setText(String.valueOf(total));
            return total;
        } 
    }
    
    public void hola() {
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

        for (int i = 0; i < lista.size(); i++) {
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
