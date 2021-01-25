package Controller;

import DAO.ArticuloDao;
import DAO.CajaDao;
import DAO.DiscoDuroDao;
import DAO.FuenteDao;
import DAO.GraficaDao;
import DAO.MemoriaRAMDao;
import DAO.MonitorDao;
import DAO.Placa_baseDao;
import DAO.ProcesadorDao;
import DAO.RatonDao;
import DAO.TecladoDao;
import DAO.UsuarioDao;
import DAO.WebCamDao;
import Model.Articulos.Articulo;
import Model.Articulos.Caja;
import Model.Articulos.Disco_duro;
import Model.Articulos.Fuente_alimentacion;
import Model.Articulos.Grafica;
import Model.Articulos.Memoria_RAM;
import Model.Articulos.Monitor;
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
import javax.swing.ImageIcon;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import util.Log;

public class ClienteController implements ActionListener {

    private InicioCliente client;
    private Cliente cliente;

    private ArticuloDao consultaArticulo = new ArticuloDao();
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
    private MonitorDao daoMonitor;

    private String tipo;
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
        daoMonitor = new MonitorDao();
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
                client.panelElegirProducto.setVisible(false);
                
                resetValuesBox();
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
                resetValuesBox();
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
                listaArticulos();
                resetValuesBox();
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
                resetValuesBox();
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

        this.client.placaBase.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                client.panelInicio.setVisible(false);
                client.panelCarro.setVisible(false);
                client.panelPerfil.setVisible(false);
                client.panelMonta.setVisible(false);
                client.panelProducto.setVisible(false);
                client.panelArticulo.setVisible(false);
                client.panelElegirProducto.setVisible(true);
                cargarListaPlaca();
                tipo = "placa";
            }
        });
        this.client.caja.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                client.panelInicio.setVisible(false);
                client.panelCarro.setVisible(false);
                client.panelPerfil.setVisible(false);
                client.panelMonta.setVisible(false);
                client.panelProducto.setVisible(false);
                client.panelArticulo.setVisible(false);
                client.panelElegirProducto.setVisible(true);
                cargarListaCaja();
                tipo = "caja";
            }
        });
        this.client.cpu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                client.panelInicio.setVisible(false);
                client.panelCarro.setVisible(false);
                client.panelPerfil.setVisible(false);
                client.panelMonta.setVisible(false);
                client.panelProducto.setVisible(false);
                client.panelArticulo.setVisible(false);
                client.panelElegirProducto.setVisible(true);
                cargarListaCpu();
                tipo = "cpu";
            }
        });
        this.client.grafica.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                client.panelInicio.setVisible(false);
                client.panelCarro.setVisible(false);
                client.panelPerfil.setVisible(false);
                client.panelMonta.setVisible(false);
                client.panelProducto.setVisible(false);
                client.panelArticulo.setVisible(false);
                client.panelElegirProducto.setVisible(true);
                cargarListaGraficas();
                tipo = "grafica";
            }
        });
        this.client.monitor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                client.panelInicio.setVisible(false);
                client.panelCarro.setVisible(false);
                client.panelPerfil.setVisible(false);
                client.panelMonta.setVisible(false);
                client.panelProducto.setVisible(false);
                client.panelArticulo.setVisible(false);
                client.panelElegirProducto.setVisible(true);
                cargarListaMonitor();
                tipo = "monitor";
            }
        });
        this.client.teclado.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                client.panelInicio.setVisible(false);
                client.panelCarro.setVisible(false);
                client.panelPerfil.setVisible(false);
                client.panelMonta.setVisible(false);
                client.panelProducto.setVisible(false);
                client.panelArticulo.setVisible(false);
                client.panelElegirProducto.setVisible(true);
                cargarListaTeclado();
                tipo = "teclado";
            }
        });
        this.client.raton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                client.panelInicio.setVisible(false);
                client.panelCarro.setVisible(false);
                client.panelPerfil.setVisible(false);
                client.panelMonta.setVisible(false);
                client.panelProducto.setVisible(false);
                client.panelArticulo.setVisible(false);
                client.panelElegirProducto.setVisible(true);
                cargarListaRaton();
                tipo = "raton";
            }
        });
        this.client.cam.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                client.panelInicio.setVisible(false);
                client.panelCarro.setVisible(false);
                client.panelPerfil.setVisible(false);
                client.panelMonta.setVisible(false);
                client.panelProducto.setVisible(false);
                client.panelArticulo.setVisible(false);
                client.panelElegirProducto.setVisible(true);
                cargarListaCam();
                tipo = "cam";
            }
        });
        this.client.fuente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                client.panelInicio.setVisible(false);
                client.panelCarro.setVisible(false);
                client.panelPerfil.setVisible(false);
                client.panelMonta.setVisible(false);
                client.panelProducto.setVisible(false);
                client.panelArticulo.setVisible(false);
                client.panelElegirProducto.setVisible(true);
                cargarListaFuente();
                tipo = "fuente";
            }
        });
        this.client.disco.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                client.panelInicio.setVisible(false);
                client.panelCarro.setVisible(false);
                client.panelPerfil.setVisible(false);
                client.panelMonta.setVisible(false);
                client.panelProducto.setVisible(false);
                client.panelArticulo.setVisible(false);
                client.panelElegirProducto.setVisible(true);
                cargarListaDisco();
                tipo = "disco";
            }
        });
        this.client.ram.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                client.panelInicio.setVisible(false);
                client.panelCarro.setVisible(false);
                client.panelPerfil.setVisible(false);
                client.panelMonta.setVisible(false);
                client.panelProducto.setVisible(false);
                client.panelArticulo.setVisible(false);
                client.panelElegirProducto.setVisible(true);
                cargarListaRam();
                tipo = "ram";
            }
        });
        
        this.client.cpuBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculaPrecioMontaje();
            }
        });
        this.client.ramBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculaPrecioMontaje();
            }
        });
        this.client.cajaBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculaPrecioMontaje();
            }
        });
        this.client.graficaBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculaPrecioMontaje();
            }
        });
        this.client.tecladoBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculaPrecioMontaje();
            }
        });
        this.client.placaBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculaPrecioMontaje();
            }
        });
        this.client.discoBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculaPrecioMontaje();
            }
        });
        this.client.ratonBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculaPrecioMontaje();
            }
        });
        this.client.fuenteBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculaPrecioMontaje();
            }
        });
        this.client.camBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculaPrecioMontaje();
            }
        });
        
        this.client.listaProductos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    
                    int codigo = Integer.parseInt(client.listaProductos.getSelectedValue().split("-")[1]);
                    client.panelInicio.setVisible(false);
                    client.panelCarro.setVisible(false);
                    client.panelPerfil.setVisible(false);
                    client.panelMonta.setVisible(false);
                    client.panelProducto.setVisible(false);
                    client.panelArticulo.setVisible(false);
                    client.panelElegirProducto.setVisible(false);
                    client.panelProducto.setVisible(true);
                    System.out.println(tipo + codigo);
                    switch (tipo){
                        case "placa":
                            Placa_base placa = consultaArticulo.getPlaca_base(codigo);
                            iniciarPanelProductoPlaca(placa);
                            break;
                        case "cpu":
                            Procesador cpu = consultaArticulo.getProcesador(codigo);
                            iniciarPanelProductoCpu(cpu);
                            break;
                        case "grafica":
                            Grafica grafica = consultaArticulo.getGrafica(codigo);
                            iniciarPanelProductoGrafica(grafica);
                            break;
                        case "caja":
                            Caja caja = consultaArticulo.getCaja(codigo);
                            iniciarPanelProductoCaja(caja);
                            break;
                        case "monitor":
                            Monitor monitor = consultaArticulo.getMonitor(codigo);
                            iniciarPanelProductoMonitor(monitor);
                            break;
                        case "teclado":
                            Teclado teclado = consultaArticulo.getTeclado(codigo);
                            iniciarPanelProductoTeclado(teclado);
                            break;
                        case "raton":
                            Raton raton = consultaArticulo.getRaton(codigo);
                            iniciarPanelProductoRaton(raton);
                            break;
                        case "cam":
                            WebCam cam = consultaArticulo.getWebcam(codigo);
                            iniciarPanelProductoCam(cam);
                            break;
                        case "fuente":
                            Fuente_alimentacion fuente = consultaArticulo.getFuente(codigo);
                            iniciarPanelProductoFuente(fuente);
                            break;
                        case "ram":
                            Memoria_RAM ram = consultaArticulo.getMemoria_RAM(codigo);
                            iniciarPanelProductoRam(ram);
                            break;
                        case "disco":
                            Disco_duro disco = consultaArticulo.getDisco_duro(codigo);
                            iniciarPanelProductoDisco(disco);
                            break;               
                    }
                    
                }
                
            }
        });
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
    
    public void iniciarPanelProductoPlaca(Placa_base placa) {
        client.productoSeleccionado.setText("Placa base");
        client.datoModelo.setText(placa.getModelo());
        client.codigo_ref.setText(String.valueOf(placa.getCodigo_ref()));
        client.datoStock.setText(String.valueOf(placa.getStock()));
        client.descripcion.setText(placa.getDescripcion());
        client.precio.setText(String.valueOf(placa.getPrecio()) + " €");
        client.imgProducto.setIcon(new ImageIcon (getClass().getResource(placa.getRutaImagen())));
        
        client.atrParticular1.setVisible(true);
        client.nombreAtributo1.setVisible(true);
        client.atrParticular1.setText(placa.getSocket());
        client.nombreAtributo1.setText("Socket");
      
    }
    
    public void iniciarPanelProductoCpu(Procesador cpu) {
        client.productoSeleccionado.setText("CPU");
        client.datoModelo.setText(cpu.getModelo());
        client.codigo_ref.setText(String.valueOf(cpu.getCodigo_ref()));
        client.datoStock.setText(String.valueOf(cpu.getStock()));
        client.descripcion.setText(cpu.getDescripcion());
        client.precio.setText(String.valueOf(cpu.getPrecio()) + " €");
        client.imgProducto.setIcon(new ImageIcon (getClass().getResource(cpu.getRutaImagen())));
        
        client.atrParticular1.setText(cpu.getSocket());
        client.nombreAtributo1.setText("Socket");
        client.atrParticular1.setVisible(true);
        client.nombreAtributo1.setVisible(true);
      
    }
    
    public void iniciarPanelProductoGrafica(Grafica grafica) {
        client.productoSeleccionado.setText("Grafica");
        client.datoModelo.setText(grafica.getModelo());
        client.codigo_ref.setText(String.valueOf(grafica.getCodigo_ref()));
        client.datoStock.setText(String.valueOf(grafica.getStock()));
        client.descripcion.setText(grafica.getDescripcion());
        client.precio.setText(String.valueOf(grafica.getPrecio()) + " €");
        client.imgProducto.setIcon(new ImageIcon (getClass().getResource(grafica.getRutaImagen())));
        
        client.atrParticular1.setText(String.valueOf(grafica.getGeneracion()));
        client.nombreAtributo1.setText("Generacion");
        client.atrParticular1.setVisible(true);
        client.nombreAtributo1.setVisible(true);
    }
    
    public void iniciarPanelProductoCaja(Caja caja) {
        client.productoSeleccionado.setText("Caja");
        client.datoModelo.setText(caja.getModelo());
        client.codigo_ref.setText(String.valueOf(caja.getCodigo_ref()));
        client.datoStock.setText(String.valueOf(caja.getStock()));
        client.descripcion.setText(caja.getDescripcion());
        client.precio.setText(String.valueOf(caja.getPrecio()) + " €");
        client.imgProducto.setIcon(new ImageIcon (getClass().getResource(caja.getRutaImagen())));
        if (caja.isCristal()){
            client.atrParticular1.setText("Si");
        }else{
            client.atrParticular1.setText("No");
        }
        client.nombreAtributo1.setText("Cristal");
        client.atrParticular1.setVisible(true);
        client.nombreAtributo1.setVisible(true);
    }
    
    public void iniciarPanelProductoMonitor(Monitor monitor) {
        client.productoSeleccionado.setText("Monitor");
        client.datoModelo.setText(monitor.getModelo());
        client.codigo_ref.setText(String.valueOf(monitor.getCodigo_ref()));
        client.datoStock.setText(String.valueOf(monitor.getStock()));
        client.descripcion.setText(monitor.getDescripcion());
        client.precio.setText(String.valueOf(monitor.getPrecio()) + " €");
        client.imgProducto.setIcon(new ImageIcon (getClass().getResource(monitor.getRutaImagen())));
        
        client.nombreAtributo1.setText("Panel");
        client.atrParticular1.setText(monitor.getPanel());
        client.nombreAtributo2.setText("Pulgadas");
        client.atrParticular2.setText(String.valueOf(monitor.getPulgadas()));
        client.nombreAtributo3.setText("HZ");
        client.atrParticular3.setText(String.valueOf(monitor.getHz()));
        
        client.atrParticular1.setVisible(true);
        client.nombreAtributo1.setVisible(true);
        client.atrParticular2.setVisible(true);
        client.nombreAtributo2.setVisible(true);
        client.atrParticular3.setVisible(true);
        client.nombreAtributo3.setVisible(true); 
    }
    
    public void iniciarPanelProductoTeclado(Teclado teclado) {
        client.productoSeleccionado.setText("Teclado");
        client.datoModelo.setText(teclado.getModelo());
        client.codigo_ref.setText(String.valueOf(teclado.getCodigo_ref()));
        client.datoStock.setText(String.valueOf(teclado.getStock()));
        client.descripcion.setText(teclado.getDescripcion());
        client.precio.setText(String.valueOf(teclado.getPrecio()) + " €");
        client.imgProducto.setIcon(new ImageIcon (getClass().getResource(teclado.getRutaImagen())));
        
        client.nombreAtributo1.setText("Tipo");
        client.atrParticular1.setText(teclado.getTipo());
        client.atrParticular1.setVisible(true);
        client.nombreAtributo1.setVisible(true);
    }
    
    public void iniciarPanelProductoRaton(Raton raton) {
        client.productoSeleccionado.setText("Raton");
        client.datoModelo.setText(raton.getModelo());
        client.codigo_ref.setText(String.valueOf(raton.getCodigo_ref()));
        client.datoStock.setText(String.valueOf(raton.getStock()));
        client.descripcion.setText(raton.getDescripcion());
        client.precio.setText(String.valueOf(raton.getPrecio()) + " €");
        client.imgProducto.setIcon(new ImageIcon (getClass().getResource(raton.getRutaImagen())));
        
        client.nombreAtributo1.setText("Tipo");
        client.atrParticular1.setText(raton.getTipo());
        client.nombreAtributo2.setText("DPI");
        client.atrParticular2.setText(String.valueOf(raton.getDPI()));
        client.nombreAtributo3.setText("Peso");
        client.atrParticular3.setText(String.valueOf(raton.getPeso()));
      
        client.atrParticular1.setVisible(true);
        client.nombreAtributo1.setVisible(true);
        client.atrParticular2.setVisible(true);
        client.nombreAtributo2.setVisible(true);
        client.atrParticular3.setVisible(true);
        client.nombreAtributo3.setVisible(true);
    }
    
    public void iniciarPanelProductoCam(WebCam cam) {
        client.productoSeleccionado.setText("Web-Cam");
        client.datoModelo.setText(cam.getModelo());
        client.codigo_ref.setText(String.valueOf(cam.getCodigo_ref()));
        client.datoStock.setText(String.valueOf(cam.getStock()));
        client.descripcion.setText(cam.getDescripcion());
        client.precio.setText(String.valueOf(cam.getPrecio()) + " €");
        client.imgProducto.setIcon(new ImageIcon (getClass().getResource(cam.getRutaImagen())));
        
        client.nombreAtributo1.setText("Calidad");
        client.atrParticular1.setText(cam.getCalidad());
        client.atrParticular1.setVisible(true);
        client.nombreAtributo1.setVisible(true);
      
    }
    
    public void iniciarPanelProductoFuente(Fuente_alimentacion fuente) {
        client.productoSeleccionado.setText("Fuente alimentacion");
        client.datoModelo.setText(fuente.getModelo());
        client.codigo_ref.setText(String.valueOf(fuente.getCodigo_ref()));
        client.datoStock.setText(String.valueOf(fuente.getStock()));
        client.descripcion.setText(fuente.getDescripcion());
        client.precio.setText(String.valueOf(fuente.getPrecio()) + " €");
        client.imgProducto.setIcon(new ImageIcon (getClass().getResource(fuente.getRutaImagen())));
        
        client.nombreAtributo1.setText("Potencia");
        client.atrParticular1.setText(String.valueOf(fuente.getPotencia()) + " W");
        client.nombreAtributo2.setText("Certificacion");
        client.atrParticular2.setText(fuente.getCertificacion());
      
        client.atrParticular1.setVisible(true);
        client.nombreAtributo1.setVisible(true);
        client.atrParticular2.setVisible(true);
        client.nombreAtributo2.setVisible(true);
    }
    
    public void iniciarPanelProductoRam(Memoria_RAM ram) {
        client.productoSeleccionado.setText("Memoria RAM");
        client.datoModelo.setText(ram.getModelo());
        client.codigo_ref.setText(String.valueOf(ram.getCodigo_ref()));
        client.datoStock.setText(String.valueOf(ram.getStock()));
        client.descripcion.setText(ram.getDescripcion());
        client.precio.setText(String.valueOf(ram.getPrecio()) + " €");
        client.imgProducto.setIcon(new ImageIcon (getClass().getResource(ram.getRutaImagen())));
        
        client.nombreAtributo1.setText("P/N");
        client.atrParticular1.setText(ram.getPN()); 
        client.atrParticular1.setVisible(true);
        client.nombreAtributo1.setVisible(true);
    }
    
    public void iniciarPanelProductoDisco(Disco_duro disco) {
        client.productoSeleccionado.setText("Memoria RAM");
        client.datoModelo.setText(disco.getModelo());
        client.codigo_ref.setText(String.valueOf(disco.getCodigo_ref()));
        client.datoStock.setText(String.valueOf(disco.getStock()));
        client.descripcion.setText(disco.getDescripcion());
        client.precio.setText(String.valueOf(disco.getPrecio()) + " €");
        client.imgProducto.setIcon(new ImageIcon (getClass().getResource(disco.getRutaImagen())));
        
        client.nombreAtributo1.setText("Tipo");
        client.atrParticular1.setText(disco.getTipo());  
        client.atrParticular1.setVisible(true);
        client.nombreAtributo1.setVisible(true);
    }
    
    /**
     * Coloca los valores de la base de datos en los comboBox al cual pertenecen
     * mediante una consulta se guarda la lista de valores y se insertan como
     * nuevo item.
     */
    private void rellenaComboBox() {

        ArrayList<Procesador> listaCpu = daoCpu.getAllProcesadores();
        for (int i = 0; i < listaCpu.size(); i++) {
            client.cpuBox.addItem(listaCpu.get(i).getModelo() + " - " + listaCpu.get(i).getSocket() + " - € " + listaCpu.get(i).getPrecio());
        }
        ArrayList<Placa_base> listaPlaca = daoPlaca.getAllPlacas();
        for (int i = 0; i < listaPlaca.size(); i++) {
            client.placaBox.addItem(listaPlaca.get(i).getModelo() + " - " + listaPlaca.get(i).getSocket() + " - € " + listaPlaca.get(i).getPrecio());
        }
        ArrayList<Memoria_RAM> listaRam = daoRam.getAllMemoria_RAM();
        for (int i = 0; i < listaRam.size(); i++) {
            client.ramBox.addItem(listaRam.get(i).getModelo() + " - € " + listaRam.get(i).getPrecio());
        }
        ArrayList<Grafica> listaGrafica = daoGrafica.getAllGraficas();
        for (int i = 0; i < listaGrafica.size(); i++) {
            client.graficaBox.addItem(listaGrafica.get(i).getModelo() + " - € " + listaGrafica.get(i).getPrecio());
        }
        ArrayList<Disco_duro> listaDisco = daoDisco.getAllDiscos();
        for (int i = 0; i < listaDisco.size(); i++) {
            client.discoBox.addItem(listaDisco.get(i).getModelo() + " - " + listaDisco.get(i).getTipo() + " - € " + listaDisco.get(i).getPrecio());
        }
        ArrayList<Fuente_alimentacion> listaFuente = daoFuente.getAllFuentes();
        for (int i = 0; i < listaFuente.size(); i++) {
            client.fuenteBox.addItem(listaFuente.get(i).getModelo() + " - " + listaFuente.get(i).getPotencia() + " W - € " + listaFuente.get(i).getPrecio());
        }
        ArrayList<Caja> listaCaja = daoCaja.getAllCajas();
        for (int i = 0; i < listaCaja.size(); i++) {
            client.cajaBox.addItem(listaCaja.get(i).getModelo() + " - € " + listaCaja.get(i).getPrecio());
        }
        ArrayList<Raton> listaRaton = daoRaton.getAllRatones();
        for (int i = 0; i < listaRaton.size(); i++) {
            client.ratonBox.addItem(listaRaton.get(i).getModelo() + " - " + listaRaton.get(i).getPeso() + " gr - € " + listaRaton.get(i).getPrecio());
        }
        ArrayList<Teclado> listaTeclado = daoTeclado.getAllTeclados();
        for (int i = 0; i < listaTeclado.size(); i++) {
            client.tecladoBox.addItem(listaTeclado.get(i).getModelo() + " - € " + listaTeclado.get(i).getPrecio());
        }
        ArrayList<WebCam> listaCam = daoCam.getAllWebCams();
        for (int i = 0; i < listaCam.size(); i++) {
            client.camBox.addItem(listaCam.get(i).getModelo() + " - " + listaCam.get(i).getCalidad() + " - € " + listaCam.get(i).getPrecio());
        }

    }

    /**
     * Obtiene el precio del producto seleccionado mediante un split del string
     * luego suma los valores de cada uno y pone el precio total del montaje
     * 
     * @return Devuelve un valor float del precio del montaje
     */
    private float calculaPrecioMontaje(){
        float total = 0;
        
        String[] precioCpu = client.cpuBox.getSelectedItem().toString().split("€");
        String[] precioPlaca = client.placaBox.getSelectedItem().toString().split("€");
        String[] precioRam = client.ramBox.getSelectedItem().toString().split("€");
        String[] precioGrafica = client.graficaBox.getSelectedItem().toString().split("€");
        String[] precioDisco = client.discoBox.getSelectedItem().toString().split("€");
        String[] precioFuente = client.fuenteBox.getSelectedItem().toString().split("€");
        String[] precioCaja = client.cajaBox.getSelectedItem().toString().split("€");
        String[] precioRaton = client.ratonBox.getSelectedItem().toString().split("€");
        String[] precioTeclado = client.tecladoBox.getSelectedItem().toString().split("€");
        String[] precioCam = client.camBox.getSelectedItem().toString().split("€");

        
        total = sacarPrecio(precioCpu) + sacarPrecio(precioPlaca) + sacarPrecio(precioRam)+
                sacarPrecio(precioGrafica) + sacarPrecio(precioDisco) + sacarPrecio(precioCaja)+
                sacarPrecio(precioFuente) + sacarPrecio(precioRaton)+ sacarPrecio(precioTeclado)+
                sacarPrecio(precioCam);
        
        client.precioTotalPc.setText(String.valueOf(total) + " €");
        return total;
        
    }
    
    /**
     * Transforma el precio de String a float en caso de ser posible
     * si no lo es da error y pone valor a 0 dado que no hay precio disponible.
     *
     * @param text
     * @return Devuelve el precio en formato float
     */
    private float sacarPrecio(String[] text){
        float valor = 0;
        try{
            if (text[0].equals("Seleccione")){
                return 0;
            }
            valor = Float.parseFloat(text[1]);         
        }catch (NumberFormatException e){
            Log.log.info("Error al sacar precio montaje " + e);
            valor = 0;
        }finally{
            return valor;
        }
      
    }
    
    /**
     * Restablece los valores de los comboBox del panel al valor default
     */
    private void resetValuesBox(){
        client.cpuBox.setSelectedIndex(0);
        client.placaBox.setSelectedIndex(0);
        client.ramBox.setSelectedIndex(0);
        client.graficaBox.setSelectedIndex(0);
        client.discoBox.setSelectedIndex(0);
        client.fuenteBox.setSelectedIndex(0);
        client.cajaBox.setSelectedIndex(0);
        client.ratonBox.setSelectedIndex(0);
        client.tecladoBox.setSelectedIndex(0);
        client.camBox.setSelectedIndex(0);
    }
    
    public void listaArticulos() {
        DefaultListModel listModel = new DefaultListModel();

        ArrayList<String> lista = new ArrayList<>();
        ArrayList<String> listaRuta = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            
        }
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
        String titulo = "CARRITO"; //PON EL TITULO QUE QUIERAS
        client.listaPedidos.setCellRenderer(new ListaDinamicaImagen(lista, listaRuta, titulo));
    }

    public void cargarListaPlaca(){
        DefaultListModel listModel = new DefaultListModel();
        ArrayList<Placa_base> lista = daoPlaca.getAllPlacas();
        
        ArrayList<String> listaInfo = new ArrayList<>();
        ArrayList<String> listaRuta = new ArrayList<>();
        
        for(int i=0; i<lista.size(); i++){
            Placa_base articulo = lista.get(i);
            int codigoReferencia = articulo.getCodigo_ref();
            String articuloInfo = "  CodRef-"+ String.format("%04d", codigoReferencia) +"- \t"+ articulo.getModelo() +" \tPrecio € "+ articulo.getPrecio() +"    \t[stock: "+ articulo.getStock() +"]";
            articuloInfo = articuloInfo.replaceAll("\t", "           ");
            
            listaInfo.add(articuloInfo);
            if(articulo.getRutaImagen() == null){
                listaRuta.add("/images/error.png");
            }
            else{
                listaRuta.add(articulo.getRutaImagen());
            }
            
            listModel.add(i, articuloInfo);
        }
        
        client.listaProductos.setModel(listModel);
        client.listaProductos.setCellRenderer(new ListaDinamicaImagen(listaInfo, listaRuta, "Placa_base"));
    } 
    
    public void cargarListaCpu(){
        DefaultListModel listModel = new DefaultListModel();
        ArrayList<Procesador> lista = daoCpu.getAllProcesadores();
        
        ArrayList<String> listaInfo = new ArrayList<>();
        ArrayList<String> listaRuta = new ArrayList<>();
        
        for(int i=0; i<lista.size(); i++){
            Procesador cpu = lista.get(i);
            int codigoReferencia = cpu.getCodigo_ref();
            String articuloInfo = "  CodRef-"+ String.format("%04d", codigoReferencia) +"- \t"+ cpu.getModelo() +" \tPrecio € "+ cpu.getPrecio() +"    \t[stock: "+ cpu.getStock() +"]";
            articuloInfo = articuloInfo.replaceAll("\t", "           ");
            
            listaInfo.add(articuloInfo);
            if(cpu.getRutaImagen() == null){
                listaRuta.add("/images/error.png");
            }
            else{
                listaRuta.add(cpu.getRutaImagen());
            }
            
            listModel.add(i, articuloInfo);
        }
        
        client.listaProductos.setModel(listModel);
        client.listaProductos.setCellRenderer(new ListaDinamicaImagen(listaInfo, listaRuta, "Procesador"));
    } 
    
    public void cargarListaGraficas(){
        DefaultListModel listModel = new DefaultListModel();
        ArrayList<Grafica> lista = daoGrafica.getAllGraficas();
        
        ArrayList<String> listaInfo = new ArrayList<>();
        ArrayList<String> listaRuta = new ArrayList<>();
        
        for(int i=0; i<lista.size(); i++){
            Grafica grafica = lista.get(i);
            int codigoReferencia = grafica.getCodigo_ref();
            String articuloInfo = "  CodRef-"+ String.format("%04d", codigoReferencia) +"- \t"+ grafica.getModelo() +" \tPrecio € "+ grafica.getPrecio() +"    \t[stock: "+ grafica.getStock() +"]";
            articuloInfo = articuloInfo.replaceAll("\t", "           ");
            
            listaInfo.add(articuloInfo);
            if(grafica.getRutaImagen() == null){
                listaRuta.add("/images/error.png");
            }
            else{
                listaRuta.add(grafica.getRutaImagen());
            }
            
            listModel.add(i, articuloInfo);
        }
        
        client.listaProductos.setModel(listModel);
        client.listaProductos.setCellRenderer(new ListaDinamicaImagen(listaInfo, listaRuta, "Grafica"));
    }
    
    public void cargarListaMonitor(){
        DefaultListModel listModel = new DefaultListModel();
        ArrayList<Monitor> lista = daoMonitor.getAllMonitores();
        
        ArrayList<String> listaInfo = new ArrayList<>();
        ArrayList<String> listaRuta = new ArrayList<>();
        
        for(int i=0; i<lista.size(); i++){
            Monitor articulo = lista.get(i);
            int codigoReferencia = articulo.getCodigo_ref();
            String articuloInfo = "  CodRef-"+ String.format("%04d", codigoReferencia) +"- \t"+ articulo.getModelo() +" \tPrecio € "+ articulo.getPrecio() +"    \t[stock: "+ articulo.getStock() +"]";
            articuloInfo = articuloInfo.replaceAll("\t", "           ");
            
            listaInfo.add(articuloInfo);
            if(articulo.getRutaImagen() == null){
                listaRuta.add("/images/error.png");
            }
            else{
                listaRuta.add(articulo.getRutaImagen());
            }
            
            listModel.add(i, articuloInfo);
        }
        
        client.listaProductos.setModel(listModel);
        client.listaProductos.setCellRenderer(new ListaDinamicaImagen(listaInfo, listaRuta, "Placa_base"));
    } 
    
    public void cargarListaTeclado(){
        DefaultListModel listModel = new DefaultListModel();
        ArrayList<Teclado> lista = daoTeclado.getAllTeclados();
        
        ArrayList<String> listaInfo = new ArrayList<>();
        ArrayList<String> listaRuta = new ArrayList<>();
        
        for(int i=0; i<lista.size(); i++){
            Teclado articulo = lista.get(i);
            int codigoReferencia = articulo.getCodigo_ref();
            String articuloInfo = "  CodRef-"+ String.format("%04d", codigoReferencia) +"- \t"+ articulo.getModelo() +" \tPrecio € "+ articulo.getPrecio() +"    \t[stock: "+ articulo.getStock() +"]";
            articuloInfo = articuloInfo.replaceAll("\t", "           ");
            
            listaInfo.add(articuloInfo);
            if(articulo.getRutaImagen() == null){
                listaRuta.add("/images/error.png");
            }
            else{
                listaRuta.add(articulo.getRutaImagen());
            }
            
            listModel.add(i, articuloInfo);
        }
        
        client.listaProductos.setModel(listModel);
        client.listaProductos.setCellRenderer(new ListaDinamicaImagen(listaInfo, listaRuta, "Placa_base"));
    } 
    
    public void cargarListaRaton(){
        DefaultListModel listModel = new DefaultListModel();
        ArrayList<Raton> lista = daoRaton.getAllRatones();
        
        ArrayList<String> listaInfo = new ArrayList<>();
        ArrayList<String> listaRuta = new ArrayList<>();
        
        for(int i=0; i<lista.size(); i++){
            Raton articulo = lista.get(i);
            int codigoReferencia = articulo.getCodigo_ref();
            String articuloInfo = "  CodRef-"+ String.format("%04d", codigoReferencia) +"- \t"+ articulo.getModelo() +" \tPrecio € "+ articulo.getPrecio() +"    \t[stock: "+ articulo.getStock() +"]";
            articuloInfo = articuloInfo.replaceAll("\t", "           ");
            
            listaInfo.add(articuloInfo);
            if(articulo.getRutaImagen() == null){
                listaRuta.add("/images/error.png");
            }
            else{
                listaRuta.add(articulo.getRutaImagen());
            }
            
            listModel.add(i, articuloInfo);
        }
        
        client.listaProductos.setModel(listModel);
        client.listaProductos.setCellRenderer(new ListaDinamicaImagen(listaInfo, listaRuta, "Placa_base"));
    }
    
    public void cargarListaCam(){
        DefaultListModel listModel = new DefaultListModel();
        ArrayList<WebCam> lista = daoCam.getAllWebCams();
        
        ArrayList<String> listaInfo = new ArrayList<>();
        ArrayList<String> listaRuta = new ArrayList<>();
        
        for(int i=0; i<lista.size(); i++){
            WebCam articulo = lista.get(i);
            int codigoReferencia = articulo.getCodigo_ref();
            String articuloInfo = "  CodRef-"+ String.format("%04d", codigoReferencia) +"- \t"+ articulo.getModelo() +" \tPrecio € "+ articulo.getPrecio() +"    \t[stock: "+ articulo.getStock() +"]";
            articuloInfo = articuloInfo.replaceAll("\t", "           ");
            
            listaInfo.add(articuloInfo);
            if(articulo.getRutaImagen() == null){
                listaRuta.add("/images/error.png");
            }
            else{
                listaRuta.add(articulo.getRutaImagen());
            }
            
            listModel.add(i, articuloInfo);
        }
        
        client.listaProductos.setModel(listModel);
        client.listaProductos.setCellRenderer(new ListaDinamicaImagen(listaInfo, listaRuta, "Placa_base"));
    }
    
    public void cargarListaFuente(){
        DefaultListModel listModel = new DefaultListModel();
        ArrayList<Fuente_alimentacion> lista = daoFuente.getAllFuentes();
        
        ArrayList<String> listaInfo = new ArrayList<>();
        ArrayList<String> listaRuta = new ArrayList<>();
        
        for(int i=0; i<lista.size(); i++){
            Fuente_alimentacion articulo = lista.get(i);
            int codigoReferencia = articulo.getCodigo_ref();
            String articuloInfo = "  CodRef-"+ String.format("%04d", codigoReferencia) +"- \t"+ articulo.getModelo() +" \tPrecio € "+ articulo.getPrecio() +"    \t[stock: "+ articulo.getStock() +"]";
            articuloInfo = articuloInfo.replaceAll("\t", "           ");
            
            listaInfo.add(articuloInfo);
            if(articulo.getRutaImagen() == null){
                listaRuta.add("/images/error.png");
            }
            else{
                listaRuta.add(articulo.getRutaImagen());
            }
            
            listModel.add(i, articuloInfo);
        }
        
        client.listaProductos.setModel(listModel);
        client.listaProductos.setCellRenderer(new ListaDinamicaImagen(listaInfo, listaRuta, "Placa_base"));
    }
    
    public void cargarListaRam(){
        DefaultListModel listModel = new DefaultListModel();
        ArrayList<Memoria_RAM> lista = daoRam.getAllMemoria_RAM();
        
        ArrayList<String> listaInfo = new ArrayList<>();
        ArrayList<String> listaRuta = new ArrayList<>();
        
        for(int i=0; i<lista.size(); i++){
            Memoria_RAM articulo = lista.get(i);
            int codigoReferencia = articulo.getCodigo_ref();
            String articuloInfo = "  CodRef-"+ String.format("%04d", codigoReferencia) +"- \t"+ articulo.getModelo() +" \tPrecio € "+ articulo.getPrecio() +"    \t[stock: "+ articulo.getStock() +"]";
            articuloInfo = articuloInfo.replaceAll("\t", "           ");
            
            listaInfo.add(articuloInfo);
            if(articulo.getRutaImagen() == null){
                listaRuta.add("/images/error.png");
            }
            else{
                listaRuta.add(articulo.getRutaImagen());
            }
            
            listModel.add(i, articuloInfo);
        }
        
        client.listaProductos.setModel(listModel);
        client.listaProductos.setCellRenderer(new ListaDinamicaImagen(listaInfo, listaRuta, "Placa_base"));
    }
    
    public void cargarListaDisco(){
        DefaultListModel listModel = new DefaultListModel();
        ArrayList<Disco_duro> lista = daoDisco.getAllDiscos();
        
        ArrayList<String> listaInfo = new ArrayList<>();
        ArrayList<String> listaRuta = new ArrayList<>();
        
        for(int i=0; i<lista.size(); i++){
            Disco_duro articulo = lista.get(i);
            int codigoReferencia = articulo.getCodigo_ref();
            String articuloInfo = "  CodRef-"+ String.format("%04d", codigoReferencia) +"- \t"+ articulo.getModelo() +" \tPrecio € "+ articulo.getPrecio() +"    \t[stock: "+ articulo.getStock() +"]";
            articuloInfo = articuloInfo.replaceAll("\t", "           ");
            
            listaInfo.add(articuloInfo);
            if(articulo.getRutaImagen() == null){
                listaRuta.add("/images/error.png");
            }
            else{
                listaRuta.add(articulo.getRutaImagen());
            }
            
            listModel.add(i, articuloInfo);
        }
        
        client.listaProductos.setModel(listModel);
        client.listaProductos.setCellRenderer(new ListaDinamicaImagen(listaInfo, listaRuta, "Placa_base"));
    }
    
    public void cargarListaCaja(){
        DefaultListModel listModel = new DefaultListModel();
        ArrayList<Caja> lista = daoCaja.getAllCajas();
        
        ArrayList<String> listaInfo = new ArrayList<>();
        ArrayList<String> listaRuta = new ArrayList<>();
        
        for(int i=0; i<lista.size(); i++){
            Caja articulo = lista.get(i);
            int codigoReferencia = articulo.getCodigo_ref();
            String articuloInfo = "  CodRef-"+ String.format("%04d", codigoReferencia) +"- \t"+ articulo.getModelo() +" \tPrecio € "+ articulo.getPrecio() +"    \t[stock: "+ articulo.getStock() +"]";
            articuloInfo = articuloInfo.replaceAll("\t", "           ");
            
            listaInfo.add(articuloInfo);
            if(articulo.getRutaImagen() == null){
                listaRuta.add("/images/error.png");
            }
            else{
                listaRuta.add(articulo.getRutaImagen());
            }
            
            listModel.add(i, articuloInfo);
        }
        
        client.listaProductos.setModel(listModel);
        client.listaProductos.setCellRenderer(new ListaDinamicaImagen(listaInfo, listaRuta, "Placa_base"));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
