package Controller;

import DAO.*;
import Model.Articulos.*;
import Model.Usuario.Cliente;
import Util.ListaDinamicaImagen;

import Views.InicioCliente;
import Views.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import util.Log;

public class ClienteController implements ActionListener {

    private final InicioCliente client;
    private final Cliente cliente;

    private final ArticuloDao consultaArticulo = new ArticuloDao();
    private final ProcesadorDao daoCpu;
    private final Placa_baseDao daoPlaca;
    private final MemoriaRAMDao daoRam;
    private final GraficaDao daoGrafica;
    private final DiscoDuroDao daoDisco;
    private final CajaDao daoCaja;
    private final TecladoDao daoTeclado;
    private final RatonDao daoRaton;
    private final WebCamDao daoCam;
    private final FuenteDao daoFuente;
    private final MonitorDao daoMonitor;
    private final CarroDao carroDao;
    private final PortatilDao daoPortatil;
    private final PcTorreDao daoPctorre;
    private final UsuarioDao daoUser;
    private final PedidoDao daoPedido;

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
        carroDao = new CarroDao();
        daoPortatil = new PortatilDao();
        daoPctorre = new PcTorreDao();
        daoUser = new UsuarioDao();
        daoPedido = new PedidoDao();
    }

    public void iniciar() {
        client.setTitle("INICIO - CLIENTE");
        client.setLocationRelativeTo(null);
        client.nombreCliente.setText(cliente.getNombre());

        //Listeners botones menu cliente 
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

                //Restablecemos los atributos particulares del producto
                client.atrParticular1.setVisible(false);
                client.nombreAtributo1.setVisible(false);
                client.atrParticular2.setVisible(false);
                client.nombreAtributo2.setVisible(false);
                client.atrParticular3.setVisible(false);
                client.nombreAtributo3.setVisible(false);
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
                client.panelElegirProducto.setVisible(false);
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
                client.panelElegirProducto.setVisible(false);
                ArrayList<Integer> cesta = cargarCarro();
                Collections.sort(cesta);
                listaArticulos(cesta);
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
                client.panelElegirProducto.setVisible(false);
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
                client.panelElegirProducto.setVisible(false);
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

        //Listeners botones panel Articulos
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
                tipo = "placa_base";
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
                tipo = "procesador";
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
                tipo = "webcam";
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
                tipo = "fuente_alimentacion";
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
                tipo = "disco_duro";
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
                tipo = "memoria_ram";
            }
        });
        this.client.portatil.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                client.panelInicio.setVisible(false);
                client.panelCarro.setVisible(false);
                client.panelPerfil.setVisible(false);
                client.panelMonta.setVisible(false);
                client.panelProducto.setVisible(false);
                client.panelArticulo.setVisible(false);
                client.panelElegirProducto.setVisible(true);
                cargarListaPortatil();
                tipo = "portatil";
            }
        });
        this.client.pctorre.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                client.panelInicio.setVisible(false);
                client.panelCarro.setVisible(false);
                client.panelPerfil.setVisible(false);
                client.panelMonta.setVisible(false);
                client.panelProducto.setVisible(false);
                client.panelArticulo.setVisible(false);
                client.panelElegirProducto.setVisible(true);
                cargarListaPcTorre();
                tipo = "pctorre";
            }
        });

        //Listeners JComboBox montaje
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

        //Listener lista producto elegido en panel articulo
        this.client.listaProductos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    try {

                        int codigo = Integer.parseInt(client.listaProductos.getSelectedValue().split("-")[1]);
                        client.panelInicio.setVisible(false);
                        client.panelCarro.setVisible(false);
                        client.panelPerfil.setVisible(false);
                        client.panelMonta.setVisible(false);
                        client.panelProducto.setVisible(false);
                        client.panelArticulo.setVisible(false);
                        client.panelElegirProducto.setVisible(false);
                        client.panelProducto.setVisible(true);

                        switch (tipo) {
                            case "placa_base":
                                Placa_base placa = consultaArticulo.getPlaca_base(codigo);
                                iniciarPanelProductoPlaca(placa);
                                break;
                            case "procesador":
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
                            case "webcam":
                                WebCam cam = consultaArticulo.getWebcam(codigo);
                                iniciarPanelProductoCam(cam);
                                break;
                            case "fuente_alimentacion":
                                Fuente_alimentacion fuente = consultaArticulo.getFuente(codigo);
                                iniciarPanelProductoFuente(fuente);
                                break;
                            case "memoria_ram":
                                Memoria_RAM ram = consultaArticulo.getMemoria_RAM(codigo);
                                iniciarPanelProductoRam(ram);
                                break;
                            case "disco_duro":
                                Disco_duro disco = consultaArticulo.getDisco_duro(codigo);
                                iniciarPanelProductoDisco(disco);
                                break;
                            case "portatil":
                                Portatil portatil = consultaArticulo.getPortatil(codigo);
                                iniciarPanelProductoPortatil(portatil);
                                break;
                            case "pctorre":
                                PcTorre pctorre = consultaArticulo.getPcTorre(codigo);
                                iniciarPanelProductoPcTorre(pctorre);
                                break;
                        }
                    } catch (NumberFormatException ex) {
                        Log.log.error("Error en listaProductos " + ex);
                    }
                }

            }
        });

        this.client.btnComprarProducto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int seleccion = JOptionPane.showConfirmDialog(null, "¿Esta seguro de que desea realizar el pedido?", "Pedido", JOptionPane.YES_NO_OPTION);

                    int stock = Integer.parseInt(client.datoStock.getText());
                    int codigo = Integer.parseInt(client.codigo_ref.getText());
                    if (stock != 0) {
                        if (seleccion == 0) { // Confirma insertar
                            int nuevoIdPedido = daoPedido.getIdPedidoMax() + 1;
                            boolean hecho = daoPedido.hacerPedido(codigo, client.precio.getText(), cliente.getEmail(), nuevoIdPedido);

                            //Actualizar stock
                            consultaArticulo.actualizarStock(codigo, stock-1);
                            client.datoStock.setText(String.valueOf(stock - 1));
                            if (hecho) {
                                JOptionPane.showMessageDialog(null, "Pedido creado con exito", "Mensaje", JOptionPane.DEFAULT_OPTION);
                            } else {
                                JOptionPane.showMessageDialog(null, "No se ha podido crear pedido, vuelva a intentarlo.", "Mensaje", JOptionPane.DEFAULT_OPTION);

                            }
                        } else { // Deniega insertar
                            JOptionPane.showMessageDialog(null, "Operacion cancelada", "Mensaje", JOptionPane.DEFAULT_OPTION);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "No se ha podido realizar el pedido, no hay Stock", "Mensaje", JOptionPane.DEFAULT_OPTION);
                    }
                } catch (Exception ex) {
                    Log.log.error("Error en comprar producto stock " + ex);
                }
            }
        });

        //Evaluacion del producto
        this.client.puntuacion1.addMouseListener(
                new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e
            ) {
                client.puntuacion1.setIcon(new ImageIcon(getClass().getResource("/images/star.png")));
                client.puntuacion2.setIcon(new ImageIcon(getClass().getResource("/images//starVacia.png")));
                client.puntuacion3.setIcon(new ImageIcon(getClass().getResource("/images//starVacia.png")));
                client.puntuacion4.setIcon(new ImageIcon(getClass().getResource("/images//starVacia.png")));
                client.puntuacion5.setIcon(new ImageIcon(getClass().getResource("/images//starVacia.png")));
            }

            @Override
            public void mouseClicked(MouseEvent e
            ) {
                try {
                    int codigo = Integer.parseInt(client.codigo_ref.getText());
                    boolean hecho = consultaArticulo.insertarEvaluacion(1, cliente.getEmail(), codigo);
                    if (hecho) {
                        client.puntuacion1.setEnabled(false);
                        client.puntuacion2.setEnabled(false);
                        client.puntuacion3.setEnabled(false);
                        client.puntuacion4.setEnabled(false);
                        client.puntuacion5.setEnabled(false);
                        client.confirmaPuntuacion.setVisible(true);
                    }
                } catch (NumberFormatException ex) {
                    Log.log.error("Error " + ex);
                }
            }
        }
        );

        this.client.puntuacion2.addMouseListener(
                new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e
            ) {
                client.puntuacion1.setIcon(new ImageIcon(getClass().getResource("/images/star.png")));
                client.puntuacion2.setIcon(new ImageIcon(getClass().getResource("/images//star.png")));
                client.puntuacion3.setIcon(new ImageIcon(getClass().getResource("/images//starVacia.png")));
                client.puntuacion4.setIcon(new ImageIcon(getClass().getResource("/images//starVacia.png")));
                client.puntuacion5.setIcon(new ImageIcon(getClass().getResource("/images//starVacia.png")));
            }

            @Override
            public void mouseClicked(MouseEvent e
            ) {
                try {
                    int codigo = Integer.parseInt(client.codigo_ref.getText());
                    boolean hecho = consultaArticulo.insertarEvaluacion(2, cliente.getEmail(), codigo);
                    if (hecho) {
                        client.puntuacion1.setEnabled(false);
                        client.puntuacion2.setEnabled(false);
                        client.puntuacion3.setEnabled(false);
                        client.puntuacion4.setEnabled(false);
                        client.puntuacion5.setEnabled(false);
                        client.confirmaPuntuacion.setVisible(true);
                    }
                } catch (NumberFormatException ex) {
                    Log.log.error("Error " + ex);
                }
            }
        }
        );

        this.client.puntuacion3.addMouseListener(
                new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e
            ) {
                client.puntuacion1.setIcon(new ImageIcon(getClass().getResource("/images/star.png")));
                client.puntuacion2.setIcon(new ImageIcon(getClass().getResource("/images//star.png")));
                client.puntuacion3.setIcon(new ImageIcon(getClass().getResource("/images//star.png")));
                client.puntuacion4.setIcon(new ImageIcon(getClass().getResource("/images//starVacia.png")));
                client.puntuacion5.setIcon(new ImageIcon(getClass().getResource("/images//starVacia.png")));
            }

            @Override
            public void mouseClicked(MouseEvent e
            ) {
                try {
                    int codigo = Integer.parseInt(client.codigo_ref.getText());
                    boolean hecho = consultaArticulo.insertarEvaluacion(3, cliente.getEmail(), codigo);
                    if (hecho) {
                        client.puntuacion1.setEnabled(false);
                        client.puntuacion2.setEnabled(false);
                        client.puntuacion3.setEnabled(false);
                        client.puntuacion4.setEnabled(false);
                        client.puntuacion5.setEnabled(false);
                        client.confirmaPuntuacion.setVisible(true);
                    }
                } catch (NumberFormatException ex) {
                    Log.log.error("Error " + ex);
                }
            }
        }
        );

        this.client.puntuacion4.addMouseListener(
                new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e
            ) {
                client.puntuacion1.setIcon(new ImageIcon(getClass().getResource("/images/star.png")));
                client.puntuacion2.setIcon(new ImageIcon(getClass().getResource("/images//star.png")));
                client.puntuacion3.setIcon(new ImageIcon(getClass().getResource("/images//star.png")));
                client.puntuacion4.setIcon(new ImageIcon(getClass().getResource("/images//star.png")));
                client.puntuacion5.setIcon(new ImageIcon(getClass().getResource("/images//starVacia.png")));
            }

            @Override
            public void mouseClicked(MouseEvent e
            ) {
                try {
                    int codigo = Integer.parseInt(client.codigo_ref.getText());
                    boolean hecho = consultaArticulo.insertarEvaluacion(4, cliente.getEmail(), codigo);
                    if (hecho) {
                        client.puntuacion1.setEnabled(false);
                        client.puntuacion2.setEnabled(false);
                        client.puntuacion3.setEnabled(false);
                        client.puntuacion4.setEnabled(false);
                        client.puntuacion5.setEnabled(false);
                        client.confirmaPuntuacion.setVisible(true);
                    }
                } catch (NumberFormatException ex) {
                    Log.log.error("Error " + ex);
                }
            }
        }
        );

        this.client.puntuacion5.addMouseListener(
                new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e
            ) {
                client.puntuacion1.setIcon(new ImageIcon(getClass().getResource("/images/star.png")));
                client.puntuacion2.setIcon(new ImageIcon(getClass().getResource("/images//star.png")));
                client.puntuacion3.setIcon(new ImageIcon(getClass().getResource("/images//star.png")));
                client.puntuacion4.setIcon(new ImageIcon(getClass().getResource("/images//star.png")));
                client.puntuacion5.setIcon(new ImageIcon(getClass().getResource("/images//star.png")));
            }

            @Override
            public void mouseClicked(MouseEvent e
            ) {
                try {
                    int codigo = Integer.parseInt(client.codigo_ref.getText());
                    boolean hecho = consultaArticulo.insertarEvaluacion(5, cliente.getEmail(), codigo);
                    if (hecho) {
                        client.puntuacion1.setEnabled(false);
                        client.puntuacion2.setEnabled(false);
                        client.puntuacion3.setEnabled(false);
                        client.puntuacion4.setEnabled(false);
                        client.puntuacion5.setEnabled(false);
                        client.confirmaPuntuacion.setVisible(true);
                    }
                } catch (NumberFormatException ex) {
                    Log.log.error("Error " + ex);
                }
            }
        }
        );

        //Listeners carrito 
        this.client.insertarCesta.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                try {
                    int seleccion = JOptionPane.showConfirmDialog(null, "¿Esta seguro de que desea añadir al carro?", "Insertar carrito!", JOptionPane.YES_NO_OPTION);

                    if (seleccion == 0) { // Confirma insertar
                        int codigo = Integer.parseInt(client.codigo_ref.getText());
                        String nuevoCarro = "";
                        String carroActual = carroDao.getArticulosCarro(cliente.getEmail());
                        if (carroActual == null) {
                            carroActual = "";
                        }
                        nuevoCarro = codigo + "-" + carroActual;
                        carroDao.actualizaCarro(cliente.getEmail(), nuevoCarro);

                        JOptionPane.showMessageDialog(null, "Articulo añadido con exito", "Mensaje", JOptionPane.DEFAULT_OPTION);
                    } else { // Deniega insertar
                        JOptionPane.showMessageDialog(null, "Operacion cancelada", "Mensaje", JOptionPane.DEFAULT_OPTION);
                    }
                } catch (NumberFormatException ex) {
                    Log.log.error("Error en insertarCesta " + ex);
                }
            }
        }
        );

        this.client.eliminarArticulo.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {

                ArrayList<Integer> cesta = cargarCarro();
                Collections.sort(cesta);

                int indexSel = client.listaPedidos.getSelectedIndex();

                if (indexSel != -1) {
                    int seleccion = JOptionPane.showConfirmDialog(null, "¿Esta seguro de que desea eliminar este articulo del carro?",
                            "Eliminar articulo", JOptionPane.YES_NO_OPTION);
                    int index = Collections.binarySearch(cesta, cesta.get(indexSel));

                    if (seleccion == 0) { //Elimina
                        cesta.remove(index);
                        String nuevoCarro = "";
                        for (int i = 0; i < cesta.size(); i++) {
                            nuevoCarro = cesta.get(i) + "-" + nuevoCarro;
                        }
                        carroDao.actualizaCarro(cliente.getEmail(), nuevoCarro);
                        JOptionPane.showMessageDialog(null, "Articulo eliminado con exito", "Mensaje", JOptionPane.DEFAULT_OPTION);
                        listaArticulos(cesta);
                    } else { // Deniega eliminar
                        JOptionPane.showMessageDialog(null, "Operacion cancelada", "Mensaje", JOptionPane.DEFAULT_OPTION);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione algun articulo", "Mensaje", JOptionPane.DEFAULT_OPTION);
                }
            }
        }
        );

        this.client.eliminaTodoCarro.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {

                int seleccion = JOptionPane.showConfirmDialog(null, "¿Esta seguro de que desea eliminar todos los articulos del carro?",
                        "Eliminar carrito", JOptionPane.YES_NO_OPTION);

                ArrayList<Integer> cesta = cargarCarro();
                Collections.sort(cesta);

                if (seleccion == 0) { //Elimina
                    cesta.clear();
                    String nuevoCarro = "";
                    carroDao.actualizaCarro(cliente.getEmail(), nuevoCarro);
                    JOptionPane.showMessageDialog(null, "Articulos eliminados con exito", "Mensaje", JOptionPane.DEFAULT_OPTION);
                    listaArticulos(cesta);
                } else { // Deniega eliminar
                    JOptionPane.showMessageDialog(null, "Operacion cancelada", "Mensaje", JOptionPane.DEFAULT_OPTION);
                }
            }
        }
        );

        this.client.btnRealizaPedidoCarro.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                ArrayList<Integer> cesta = cargarCarro();
                int seleccion = JOptionPane.showConfirmDialog(null, "¿Esta seguro de que desea realizar el pedido?", "Pedido", JOptionPane.YES_NO_OPTION);
                boolean noHay = false;
                String modelo = "";
                if (seleccion == 0) { // Confirma insertar
                    //Actualiza stock articulo
                    for (int i = 0; i < cesta.size(); i++) {
                        int repetido = 0;
                        int codRef = cesta.get(i);
                        //Comprobamos cuantas se van a pedir y el stock futuro
                        for (int j = 0; j < cesta.size(); j++) {
                            if(codRef==cesta.get(j)){
                                repetido++;
                            }
                        }
                        Articulo articulo = consultaArticulo.getArticulo(codRef);
                        if ((articulo.getStock() - repetido) < 0) {
                            noHay = true;
                            modelo = articulo.getModelo();
                            break;
                        }
                    }
                    if (noHay) {
                        JOptionPane.showMessageDialog(null, "No hay stock de " + modelo + " eliminalo de la lista para hacer el pedido.", "Mensaje", JOptionPane.DEFAULT_OPTION);
                    } else {
                        int nuevoIdPedido = daoPedido.getIdPedidoMax() + 1;
                        boolean hecho = daoPedido.hacerPedidoCarro(cesta, client.precioCarro.getText(), cliente.getEmail(), nuevoIdPedido);
                        for (int i = 0; i < cesta.size(); i++) {
                            int codRef = cesta.get(i);
                            Articulo articulo = consultaArticulo.getArticulo(codRef);
                            consultaArticulo.actualizarStock(codRef, articulo.getStock() - 1);
                        }
                        if (hecho) {
                            carroDao.actualizaCarro(cliente.getEmail(), "");
                            cesta.clear();
                            listaArticulos(cesta);
                            JOptionPane.showMessageDialog(null, "Pedido creado con exito", "Mensaje", JOptionPane.DEFAULT_OPTION);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se ha podido crear pedido, vuelva a intentarlo.", "Mensaje", JOptionPane.DEFAULT_OPTION);

                        }
                    }

                } else { // Deniega insertar
                    JOptionPane.showMessageDialog(null, "Operacion cancelada", "Mensaje", JOptionPane.DEFAULT_OPTION);
                }
            }
        }
        );

        //Listners de perfil cliente
        this.client.btnCambiaEmail.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {

                String nuevoEmail = client.nuevoEmail.getText();
                boolean hecho = daoUser.editarEmailCliente(cliente.getEmail(), nuevoEmail);
                if (hecho) {
                    cliente.setEmail(nuevoEmail);
                    client.datoEmail.setText(nuevoEmail);
                    JOptionPane.showMessageDialog(null, "Su email se ha actualizado con exito", "Mensaje", JOptionPane.DEFAULT_OPTION);
                } else {
                    JOptionPane.showMessageDialog(null, "Su email no se ha podido actualizar es posible "
                            + "que el email introdocido ya exista, vuelva a intentarlo.", "Mensaje", JOptionPane.DEFAULT_OPTION);
                }

            }
        }
        );

        this.client.btnCambiaContra.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                boolean correcto = comprobarPassword();
                if (correcto) {
                    char[] valorContrasenna = client.nuevaPass.getPassword();
                    String passNueva = new String(valorContrasenna);

                    boolean hecho = daoUser.editarPasswordCliente(passNueva, cliente.getEmail());
                    if (hecho) {
                        cliente.setPass(passNueva);
                        JOptionPane.showMessageDialog(null, "Su contraseña se ha actualizado con exito", "Mensaje", JOptionPane.DEFAULT_OPTION);
                    } else {
                        JOptionPane.showMessageDialog(null, "Su contraseña no se ha podido actualizar, vuelva a intentarlo.", "Mensaje", JOptionPane.DEFAULT_OPTION);

                    }
                }
            }
        }
        );

        this.client.btnCambiaAtributos.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {

                if (client.datoTarjeta.getText().equalsIgnoreCase("") || client.datoTelefono.getText().equalsIgnoreCase("")
                        || client.datoDireccion.getText().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "ERROR: Rellene los campos Trjeta, Direccion, Telefono");
                } else {
                    try {
                        int telefono = Integer.parseInt(client.datoTelefono.getText());
                        String tarjeta = client.datoTarjeta.getText();
                        String direccion = client.datoDireccion.getText();
                        boolean hecho = daoUser.editarDatosCliente(cliente.getEmail(), direccion, tarjeta, telefono);

                        if (hecho) {
                            cliente.setDireccion(direccion);
                            cliente.setTelefono(telefono);
                            cliente.setTarjeta(tarjeta);
                            JOptionPane.showMessageDialog(null, "Sus datos se han actualizado con exito", "Mensaje", JOptionPane.DEFAULT_OPTION);
                        } else {
                            JOptionPane.showMessageDialog(null, "Sus datos no se han podido actualizar, vuelva a intentarlo.", "Mensaje", JOptionPane.DEFAULT_OPTION);

                        }
                    } catch (NumberFormatException ex) {
                        Log.log.error(ex + "Error en extraer datos actualizar perfil cliente");
                        JOptionPane.showMessageDialog(null, "Error al extraer los datos, vuelva a introducirlos.", "Error", JOptionPane.DEFAULT_OPTION);
                    }

                }
            }
        }
        );

    }

    /**
     * Inicia el panel de perfil cargando los valores del cliente que ha
     * iniciado sesion en su correspondiente etiqueta.
     *
     */
    private void iniciarPanelPerfil() {
        client.nombrePerfil.setText(cliente.getNombre());
        client.apellidoPerfil.setText(cliente.getApellido());
        client.datoTelefono.setText(String.valueOf(cliente.getTelefono()));
        client.datoDireccion.setText(cliente.getDireccion());
        client.datoTarjeta.setText(cliente.getTarjeta());
        client.datoEmail.setText(cliente.getEmail());
    }

    /**
     * Inicia el panel de montar cargando los valores de los articulos en los
     * respectivos JComboBox
     */
    private void iniciarPanelMontar() {
        rellenaComboBox();
    }

    /**
     * Inicia el panel del producto Placa base cargando los valores de la placa
     * en las correspondientes etiquetas las cuales referencian a su atributo.
     *
     * @param placa
     */
    private void iniciarPanelProductoPlaca(Placa_base placa) {
        client.productoSeleccionado.setText("Placa base");
        client.datoModelo.setText(placa.getModelo());
        client.codigo_ref.setText(String.valueOf(placa.getCodigo_ref()));
        client.datoStock.setText(String.valueOf(placa.getStock()));
        client.descripcion.setText(placa.getDescripcion());
        client.precio.setText(String.valueOf(placa.getPrecio()));
        String ruta = placa.getRutaImagen();
        comprobarEvaluacionRealizada(placa.getCodigo_ref());
        if (ruta == null) {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource("/images/error.png")));
        } else if (getClass().getResource(ruta) == null) {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource("/images/error.png")));
        } else {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource(ruta)));
        }

        client.atrParticular1.setVisible(true);
        client.nombreAtributo1.setVisible(true);
        client.atrParticular1.setText(placa.getSocket());
        client.nombreAtributo1.setText("Socket");

    }

    /**
     * Inicia el panel del producto procesador cargando los valores del
     * procesador en las correspondientes etiquetas las cuales referencian a su
     * atributo.
     *
     * @param cpu
     */
    private void iniciarPanelProductoCpu(Procesador cpu) {
        client.productoSeleccionado.setText("CPU");
        client.datoModelo.setText(cpu.getModelo());
        client.codigo_ref.setText(String.valueOf(cpu.getCodigo_ref()));
        client.datoStock.setText(String.valueOf(cpu.getStock()));
        client.descripcion.setText(cpu.getDescripcion());
        client.precio.setText(String.valueOf(cpu.getPrecio()));
        String ruta = cpu.getRutaImagen();
        comprobarEvaluacionRealizada(cpu.getCodigo_ref());
        if (ruta == null) {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource("/images/error.png")));
        } else if (getClass().getResource(ruta) == null) {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource("/images/error.png")));
        } else {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource(ruta)));
        }

        client.atrParticular1.setText(cpu.getSocket());
        client.nombreAtributo1.setText("Socket");
        client.atrParticular1.setVisible(true);
        client.nombreAtributo1.setVisible(true);

    }

    /**
     * Inicia el panel del producto grafica cargando los valores del grafica en
     * las correspondientes etiquetas las cuales referencian a su atributo.
     *
     * @param grafica
     */
    private void iniciarPanelProductoGrafica(Grafica grafica) {
        client.productoSeleccionado.setText("Grafica");
        client.datoModelo.setText(grafica.getModelo());
        client.codigo_ref.setText(String.valueOf(grafica.getCodigo_ref()));
        client.datoStock.setText(String.valueOf(grafica.getStock()));
        client.descripcion.setText(grafica.getDescripcion());
        client.precio.setText(String.valueOf(grafica.getPrecio()));
        String ruta = grafica.getRutaImagen();
        comprobarEvaluacionRealizada(grafica.getCodigo_ref());
        if (ruta == null) {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource("/images/error.png")));
        } else if (getClass().getResource(ruta) == null) {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource("/images/error.png")));
        } else {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource(ruta)));
        }

        client.atrParticular1.setText(String.valueOf(grafica.getGeneracion()));
        client.nombreAtributo1.setText("Generacion");
        client.atrParticular1.setVisible(true);
        client.nombreAtributo1.setVisible(true);
    }

    /**
     * Inicia el panel del producto caja cargando los valores de la caja en las
     * correspondientes etiquetas las cuales referencian a su atributo.
     *
     * @param caja
     */
    private void iniciarPanelProductoCaja(Caja caja) {
        client.productoSeleccionado.setText("Caja");
        client.datoModelo.setText(caja.getModelo());
        client.codigo_ref.setText(String.valueOf(caja.getCodigo_ref()));
        client.datoStock.setText(String.valueOf(caja.getStock()));
        client.descripcion.setText(caja.getDescripcion());
        client.precio.setText(String.valueOf(caja.getPrecio()));
        String ruta = caja.getRutaImagen();
        comprobarEvaluacionRealizada(caja.getCodigo_ref());
        if (ruta == null) {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource("/images/error.png")));
        } else if (getClass().getResource(ruta) == null) {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource("/images/error.png")));
        } else {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource(ruta)));
        }

        if (caja.isCristal()) {
            client.atrParticular1.setText("Si");
        } else {
            client.atrParticular1.setText("No");
        }
        client.nombreAtributo1.setText("Cristal");
        client.atrParticular1.setVisible(true);
        client.nombreAtributo1.setVisible(true);
    }

    /**
     * Inicia el panel del producto monitor cargando los valores del monitor en
     * las correspondientes etiquetas las cuales referencian a su atributo.
     *
     * @param monitor
     */
    private void iniciarPanelProductoMonitor(Monitor monitor) {
        client.productoSeleccionado.setText("Monitor");
        client.datoModelo.setText(monitor.getModelo());
        client.codigo_ref.setText(String.valueOf(monitor.getCodigo_ref()));
        client.datoStock.setText(String.valueOf(monitor.getStock()));
        client.descripcion.setText(monitor.getDescripcion());
        client.precio.setText(String.valueOf(monitor.getPrecio()));
        String ruta = monitor.getRutaImagen();
        comprobarEvaluacionRealizada(monitor.getCodigo_ref());
        if (ruta == null) {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource("/images/error.png")));
        } else if (getClass().getResource(ruta) == null) {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource("/images/error.png")));
        } else {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource(ruta)));
        }

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

    /**
     * Inicia el panel del teclado monitor cargando los valores del teclado en
     * las correspondientes etiquetas las cuales referencian a su atributo.
     *
     * @param teclado
     */
    private void iniciarPanelProductoTeclado(Teclado teclado) {
        client.productoSeleccionado.setText("Teclado");
        client.datoModelo.setText(teclado.getModelo());
        client.codigo_ref.setText(String.valueOf(teclado.getCodigo_ref()));
        client.datoStock.setText(String.valueOf(teclado.getStock()));
        client.descripcion.setText(teclado.getDescripcion());
        client.precio.setText(String.valueOf(teclado.getPrecio()));
        String ruta = teclado.getRutaImagen();
        comprobarEvaluacionRealizada(teclado.getCodigo_ref());
        if (ruta == null) {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource("/images/error.png")));
        } else if (getClass().getResource(ruta) == null) {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource("/images/error.png")));
        } else {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource(ruta)));
        }

        client.nombreAtributo1.setText("Tipo");
        client.atrParticular1.setText(teclado.getTipo());
        client.atrParticular1.setVisible(true);
        client.nombreAtributo1.setVisible(true);
    }

    /**
     * Inicia el panel del raton monitor cargando los valores del raton en las
     * correspondientes etiquetas las cuales referencian a su atributo.
     *
     * @param raton
     */
    private void iniciarPanelProductoRaton(Raton raton) {
        client.productoSeleccionado.setText("Raton");
        client.datoModelo.setText(raton.getModelo());
        client.codigo_ref.setText(String.valueOf(raton.getCodigo_ref()));
        client.datoStock.setText(String.valueOf(raton.getStock()));
        client.descripcion.setText(raton.getDescripcion());
        client.precio.setText(String.valueOf(raton.getPrecio()));
        String ruta = raton.getRutaImagen();
        comprobarEvaluacionRealizada(raton.getCodigo_ref());
        if (ruta == null) {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource("/images/error.png")));
        } else if (getClass().getResource(ruta) == null) {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource("/images/error.png")));
        } else {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource(ruta)));
        }

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

    /**
     * Inicia el panel de la wecbam monitor cargando los valores de la webcam en
     * las correspondientes etiquetas las cuales referencian a su atributo.
     *
     * @param cam
     */
    private void iniciarPanelProductoCam(WebCam cam) {
        client.productoSeleccionado.setText("Web-Cam");
        client.datoModelo.setText(cam.getModelo());
        client.codigo_ref.setText(String.valueOf(cam.getCodigo_ref()));
        client.datoStock.setText(String.valueOf(cam.getStock()));
        client.descripcion.setText(cam.getDescripcion());
        client.precio.setText(String.valueOf(cam.getPrecio()));
        String ruta = cam.getRutaImagen();
        comprobarEvaluacionRealizada(cam.getCodigo_ref());
        if (ruta == null) {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource("/images/error.png")));
        } else if (getClass().getResource(ruta) == null) {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource("/images/error.png")));
        } else {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource(ruta)));
        }

        client.nombreAtributo1.setText("Calidad");
        client.atrParticular1.setText(cam.getCalidad());
        client.atrParticular1.setVisible(true);
        client.nombreAtributo1.setVisible(true);

    }

    /**
     * Inicia el panel de la fuente de alimentacion monitor cargando los valores
     * de la fuente en las correspondientes etiquetas las cuales referencian a
     * su atributo.
     *
     * @param fuente
     */
    private void iniciarPanelProductoFuente(Fuente_alimentacion fuente) {
        client.productoSeleccionado.setText("Fuente alimentacion");
        client.datoModelo.setText(fuente.getModelo());
        client.codigo_ref.setText(String.valueOf(fuente.getCodigo_ref()));
        client.datoStock.setText(String.valueOf(fuente.getStock()));
        client.descripcion.setText(fuente.getDescripcion());
        client.precio.setText(String.valueOf(fuente.getPrecio()));
        String ruta = fuente.getRutaImagen();
        comprobarEvaluacionRealizada(fuente.getCodigo_ref());
        if (ruta == null) {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource("/images/error.png")));
        } else if (getClass().getResource(ruta) == null) {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource("/images/error.png")));
        } else {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource(ruta)));
        }

        client.nombreAtributo1.setText("Potencia");
        client.atrParticular1.setText(String.valueOf(fuente.getPotencia()) + " W");
        client.nombreAtributo2.setText("Certificacion");
        client.atrParticular2.setText(fuente.getCertificacion());

        client.atrParticular1.setVisible(true);
        client.nombreAtributo1.setVisible(true);
        client.atrParticular2.setVisible(true);
        client.nombreAtributo2.setVisible(true);
    }

    /**
     * Inicia el panel de la memoria ram monitor cargando los valores de la
     * memoria ram en las correspondientes etiquetas las cuales referencian a su
     * atributo.
     *
     * @param ram
     */
    private void iniciarPanelProductoRam(Memoria_RAM ram) {
        client.productoSeleccionado.setText("Memoria RAM");
        client.datoModelo.setText(ram.getModelo());
        client.codigo_ref.setText(String.valueOf(ram.getCodigo_ref()));
        client.datoStock.setText(String.valueOf(ram.getStock()));
        client.descripcion.setText(ram.getDescripcion());
        client.precio.setText(String.valueOf(ram.getPrecio()));
        String ruta = ram.getRutaImagen();
        comprobarEvaluacionRealizada(ram.getCodigo_ref());
        if (ruta == null) {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource("/images/error.png")));
        } else if (getClass().getResource(ruta) == null) {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource("/images/error.png")));
        } else {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource(ruta)));
        }

        client.nombreAtributo1.setText("P/N");
        client.atrParticular1.setText(ram.getPN());
        client.atrParticular1.setVisible(true);
        client.nombreAtributo1.setVisible(true);
    }

    /**
     * Inicia el panel del producto disco duro cargando los valores del disco en
     * las correspondientes etiquetas las cuales referencian a su atributo.
     *
     * @param disco
     */
    private void iniciarPanelProductoDisco(Disco_duro disco) {
        client.productoSeleccionado.setText("Disco duro");
        client.datoModelo.setText(disco.getModelo());
        client.codigo_ref.setText(String.valueOf(disco.getCodigo_ref()));
        client.datoStock.setText(String.valueOf(disco.getStock()));
        client.descripcion.setText(disco.getDescripcion());
        client.precio.setText(String.valueOf(disco.getPrecio()));
        String ruta = disco.getRutaImagen();
        comprobarEvaluacionRealizada(disco.getCodigo_ref());
        if (ruta == null) {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource("/images/error.png")));
        } else if (getClass().getResource(ruta) == null) {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource("/images/error.png")));
        } else {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource(ruta)));
        }

        client.nombreAtributo1.setText("Tipo");
        client.atrParticular1.setText(disco.getTipo());
        client.atrParticular1.setVisible(true);
        client.nombreAtributo1.setVisible(true);
    }

    /**
     * Inicia el panel del producto portatil cargando los valores del portatil
     * en las correspondientes etiquetas las cuales referencian a su atributo.
     *
     * @param portatil
     */
    private void iniciarPanelProductoPortatil(Portatil portatil) {
        client.productoSeleccionado.setText("Portatil");
        client.datoModelo.setText(portatil.getModelo());
        client.codigo_ref.setText(String.valueOf(portatil.getCodigo_ref()));
        client.datoStock.setText(String.valueOf(portatil.getStock()));
        client.descripcion.setText(portatil.getDescripcion());
        client.precio.setText(String.valueOf(portatil.getPrecio()));
        String ruta = portatil.getRutaImagen();
        comprobarEvaluacionRealizada(portatil.getCodigo_ref());
        if (ruta == null) {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource("/images/error.png")));
        } else if (getClass().getResource(ruta) == null) {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource("/images/error.png")));
        } else {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource(ruta)));
        }

        client.nombreAtributo1.setText("Peso");
        client.atrParticular1.setText(String.valueOf(portatil.getPeso()));
        client.nombreAtributo2.setText("Panel");
        client.atrParticular2.setText(portatil.getPanel());
        client.atrParticular1.setVisible(true);
        client.nombreAtributo1.setVisible(true);
        client.atrParticular2.setVisible(true);
        client.nombreAtributo2.setVisible(true);
    }

    /**
     * Inicia el panel del producto torre pc cargando los valores de la pc torre
     * en las correspondientes etiquetas las cuales referencian a su atributo.
     *
     * @param pctorre
     */
    private void iniciarPanelProductoPcTorre(PcTorre pctorre) {
        client.productoSeleccionado.setText("Pc Torre");
        client.datoModelo.setText(pctorre.getModelo());
        client.codigo_ref.setText(String.valueOf(pctorre.getCodigo_ref()));
        client.datoStock.setText(String.valueOf(pctorre.getStock()));
        client.descripcion.setText(pctorre.getDescripcion());
        client.precio.setText(String.valueOf(pctorre.getPrecio()));
        String ruta = pctorre.getRutaImagen();
        comprobarEvaluacionRealizada(pctorre.getCodigo_ref());
        if (ruta == null) {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource("/images/error.png")));
        } else if (getClass().getResource(ruta) == null) {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource("/images/error.png")));
        } else {
            client.imgProducto.setIcon(new ImageIcon(getClass().getResource(ruta)));
        }

        client.nombreAtributo1.setText("Nombre");
        client.atrParticular1.setText(pctorre.getNombre());
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
    private float calculaPrecioMontaje() {
        float total = 0;
        try {
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

            total = sacarPrecio(precioCpu) + sacarPrecio(precioPlaca) + sacarPrecio(precioRam)
                    + sacarPrecio(precioGrafica) + sacarPrecio(precioDisco) + sacarPrecio(precioCaja)
                    + sacarPrecio(precioFuente) + sacarPrecio(precioRaton) + sacarPrecio(precioTeclado)
                    + sacarPrecio(precioCam);

            client.precioTotalPc.setText(String.valueOf(total) + " €");
        } catch (Exception ex) {
            Log.log.error("Error en calcular el precio " + ex);
        }
        return total;

    }

    /**
     * Transforma el precio de String a float en caso de ser posible si no lo es
     * da error y pone valor a 0 dado que no hay precio disponible.
     *
     * @param text
     * @return Devuelve el precio en formato float
     */
    private float sacarPrecio(String[] text) {
        float valor = 0;
        try {
            if (text[0].equals("Seleccione")) {
                return 0;
            }
            valor = Float.parseFloat(text[1]);
        } catch (NumberFormatException e) {
            Log.log.info("Error al sacar precio montaje " + e);
            valor = 0;
        } finally {
            return valor;
        }

    }

    /**
     * Restablece los valores de los comboBox del panel al valor default
     */
    private void resetValuesBox() {
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

    /**
     * Obtiene el carro de la base de datos y lo transforma en un arraylist de
     * enteros.
     *
     * @return Devuelve un arrayList de enteros que corresponden a los codigos
     * de referencia
     */
    private ArrayList<Integer> cargarCarro() {

        ArrayList<Integer> cesta = new ArrayList<>();
        String carroActual = carroDao.getArticulosCarro(cliente.getEmail());
        try {
            if (carroActual != null) {
                String[] articulo = carroActual.split("-");
                if (!articulo[0].equals("")) {
                    for (int i = 0; i < articulo.length; i++) {
                        cesta.add(Integer.parseInt(articulo[i]));
                    }
                }
            }
        } catch (NumberFormatException ex) {
            Log.log.error("Error en cargarCarro " + ex);
        }
        return cesta;
    }

    /**
     * Crea una Jlist con los articulos que hay en el arraylist pasado por
     * parametro con informacion relativa a los mismos sacada de la base de
     * datos.
     *
     * @param cesta
     */
    private void listaArticulos(ArrayList<Integer> cesta) {
        DefaultListModel listModel = new DefaultListModel();

        ArrayList<String> listaInfo = new ArrayList<>();
        ArrayList<String> listaRuta = new ArrayList<>();

        float precioCarro = 0;
        for (int i = 0; i < cesta.size(); i++) {

            int codigo = cesta.get(i);
            Articulo articulo = consultaArticulo.getArticulo(codigo);
            String modelo = articulo.getModelo();
            float precio = articulo.getPrecio();
            String ruta = articulo.getRutaImagen();
            String articuloInfo = "  CodRef-" + String.format("%04d", codigo) + "- \t" + modelo + " \tPrecio € " + precio;
            articuloInfo = articuloInfo.replaceAll("\t", "           ");

            listaInfo.add(articuloInfo);
            if (ruta == null) {
                listaRuta.add("/images/error.png");
            } else {
                listaRuta.add(ruta);
            }
            listModel.add(i, articuloInfo);

            precioCarro += precio;
        }

        client.precioCarro.setText(String.valueOf(precioCarro));
        client.listaPedidos.setModel(listModel);
        client.listaPedidos.setCellRenderer(new ListaDinamicaImagen(listaInfo, listaRuta, tipo));
    }

    /**
     * Crea una Jlist con informacion de todas las placas que hay en la base de
     * datos.
     */
    private void cargarListaPlaca() {
        DefaultListModel listModel = new DefaultListModel();
        ArrayList<Placa_base> lista = daoPlaca.getAllPlacas();

        ArrayList<String> listaInfo = new ArrayList<>();
        ArrayList<String> listaRuta = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {
            Placa_base articulo = lista.get(i);
            int codigoReferencia = articulo.getCodigo_ref();
            String articuloInfo = "  CodRef-" + String.format("%04d", codigoReferencia) + "- \t" + articulo.getModelo() + " \tPrecio € " + articulo.getPrecio() + "    \t[stock: " + articulo.getStock() + "]";
            articuloInfo = articuloInfo.replaceAll("\t", "           ");

            listaInfo.add(articuloInfo);
            if (articulo.getRutaImagen() == null) {
                listaRuta.add("/images/error.png");
            } else {
                listaRuta.add(articulo.getRutaImagen());
            }

            listModel.add(i, articuloInfo);
        }

        client.listaProductos.setModel(listModel);
        client.listaProductos.setCellRenderer(new ListaDinamicaImagen(listaInfo, listaRuta, "Placa_base"));
    }

    /**
     * Crea una Jlist con informacion de todos los procesadores que hay en la
     * base de datos.
     */
    private void cargarListaCpu() {
        DefaultListModel listModel = new DefaultListModel();
        ArrayList<Procesador> lista = daoCpu.getAllProcesadores();

        ArrayList<String> listaInfo = new ArrayList<>();
        ArrayList<String> listaRuta = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {
            Procesador cpu = lista.get(i);
            int codigoReferencia = cpu.getCodigo_ref();
            String articuloInfo = "  CodRef-" + String.format("%04d", codigoReferencia) + "- \t" + cpu.getModelo() + " \tPrecio € " + cpu.getPrecio() + "    \t[stock: " + cpu.getStock() + "]";
            articuloInfo = articuloInfo.replaceAll("\t", "           ");

            listaInfo.add(articuloInfo);
            if (cpu.getRutaImagen() == null) {
                listaRuta.add("/images/error.png");
            } else {
                listaRuta.add(cpu.getRutaImagen());
            }

            listModel.add(i, articuloInfo);
        }

        client.listaProductos.setModel(listModel);
        client.listaProductos.setCellRenderer(new ListaDinamicaImagen(listaInfo, listaRuta, "Procesador"));
    }

    /**
     * Crea una Jlist con informacion de todas las graficas que hay en la base
     * de datos.
     */
    private void cargarListaGraficas() {
        DefaultListModel listModel = new DefaultListModel();
        ArrayList<Grafica> lista = daoGrafica.getAllGraficas();

        ArrayList<String> listaInfo = new ArrayList<>();
        ArrayList<String> listaRuta = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {
            Grafica grafica = lista.get(i);
            int codigoReferencia = grafica.getCodigo_ref();
            String articuloInfo = "  CodRef-" + String.format("%04d", codigoReferencia) + "- \t" + grafica.getModelo() + " \tPrecio € " + grafica.getPrecio() + "    \t[stock: " + grafica.getStock() + "]";
            articuloInfo = articuloInfo.replaceAll("\t", "           ");

            listaInfo.add(articuloInfo);
            if (grafica.getRutaImagen() == null) {
                listaRuta.add("/images/error.png");
            } else {
                listaRuta.add(grafica.getRutaImagen());
            }

            listModel.add(i, articuloInfo);
        }

        client.listaProductos.setModel(listModel);
        client.listaProductos.setCellRenderer(new ListaDinamicaImagen(listaInfo, listaRuta, "Grafica"));
    }

    /**
     * Crea una Jlist con informacion de todos los monitores que hay en la base
     * de datos.
     */
    private void cargarListaMonitor() {
        DefaultListModel listModel = new DefaultListModel();
        ArrayList<Monitor> lista = daoMonitor.getAllMonitores();

        ArrayList<String> listaInfo = new ArrayList<>();
        ArrayList<String> listaRuta = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {
            Monitor articulo = lista.get(i);
            int codigoReferencia = articulo.getCodigo_ref();
            String articuloInfo = "  CodRef-" + String.format("%04d", codigoReferencia) + "- \t" + articulo.getModelo() + " \tPrecio € " + articulo.getPrecio() + "    \t[stock: " + articulo.getStock() + "]";
            articuloInfo = articuloInfo.replaceAll("\t", "           ");

            listaInfo.add(articuloInfo);
            if (articulo.getRutaImagen() == null) {
                listaRuta.add("/images/error.png");
            } else {
                listaRuta.add(articulo.getRutaImagen());
            }

            listModel.add(i, articuloInfo);
        }

        client.listaProductos.setModel(listModel);
        client.listaProductos.setCellRenderer(new ListaDinamicaImagen(listaInfo, listaRuta, "Monitor"));
    }

    /**
     * Crea una Jlist con informacion de todos los teclados que hay en la base
     * de datos.
     */
    private void cargarListaTeclado() {
        DefaultListModel listModel = new DefaultListModel();
        ArrayList<Teclado> lista = daoTeclado.getAllTeclados();

        ArrayList<String> listaInfo = new ArrayList<>();
        ArrayList<String> listaRuta = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {
            Teclado articulo = lista.get(i);
            int codigoReferencia = articulo.getCodigo_ref();
            String articuloInfo = "  CodRef-" + String.format("%04d", codigoReferencia) + "- \t" + articulo.getModelo() + " \tPrecio € " + articulo.getPrecio() + "    \t[stock: " + articulo.getStock() + "]";
            articuloInfo = articuloInfo.replaceAll("\t", "           ");

            listaInfo.add(articuloInfo);
            if (articulo.getRutaImagen() == null) {
                listaRuta.add("/images/error.png");
            } else {
                listaRuta.add(articulo.getRutaImagen());
            }

            listModel.add(i, articuloInfo);
        }

        client.listaProductos.setModel(listModel);
        client.listaProductos.setCellRenderer(new ListaDinamicaImagen(listaInfo, listaRuta, "Teclado"));
    }

    /**
     * Crea una Jlist con informacion de todos los ratones que hay en la base de
     * datos.
     */
    private void cargarListaRaton() {
        DefaultListModel listModel = new DefaultListModel();
        ArrayList<Raton> lista = daoRaton.getAllRatones();

        ArrayList<String> listaInfo = new ArrayList<>();
        ArrayList<String> listaRuta = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {
            Raton articulo = lista.get(i);
            int codigoReferencia = articulo.getCodigo_ref();
            String articuloInfo = "  CodRef-" + String.format("%04d", codigoReferencia) + "- \t" + articulo.getModelo() + " \tPrecio € " + articulo.getPrecio() + "    \t[stock: " + articulo.getStock() + "]";
            articuloInfo = articuloInfo.replaceAll("\t", "           ");

            listaInfo.add(articuloInfo);
            if (articulo.getRutaImagen() == null) {
                listaRuta.add("/images/error.png");
            } else {
                listaRuta.add(articulo.getRutaImagen());
            }

            listModel.add(i, articuloInfo);
        }

        client.listaProductos.setModel(listModel);
        client.listaProductos.setCellRenderer(new ListaDinamicaImagen(listaInfo, listaRuta, "Raton"));
    }

    /**
     * Crea una Jlist con informacion de todas las webcam que hay en la base de
     * datos.
     */
    private void cargarListaCam() {
        DefaultListModel listModel = new DefaultListModel();
        ArrayList<WebCam> lista = daoCam.getAllWebCams();

        ArrayList<String> listaInfo = new ArrayList<>();
        ArrayList<String> listaRuta = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {
            WebCam articulo = lista.get(i);
            int codigoReferencia = articulo.getCodigo_ref();
            String articuloInfo = "  CodRef-" + String.format("%04d", codigoReferencia) + "- \t" + articulo.getModelo() + " \tPrecio € " + articulo.getPrecio() + "    \t[stock: " + articulo.getStock() + "]";
            articuloInfo = articuloInfo.replaceAll("\t", "           ");

            listaInfo.add(articuloInfo);
            if (articulo.getRutaImagen() == null) {
                listaRuta.add("/images/error.png");
            } else {
                listaRuta.add(articulo.getRutaImagen());
            }

            listModel.add(i, articuloInfo);
        }

        client.listaProductos.setModel(listModel);
        client.listaProductos.setCellRenderer(new ListaDinamicaImagen(listaInfo, listaRuta, "WebCam"));
    }

    /**
     * Crea una Jlist con informacion de todas las fuentes de alimentacion que
     * hay en la base de datos.
     */
    private void cargarListaFuente() {
        DefaultListModel listModel = new DefaultListModel();
        ArrayList<Fuente_alimentacion> lista = daoFuente.getAllFuentes();

        ArrayList<String> listaInfo = new ArrayList<>();
        ArrayList<String> listaRuta = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {
            Fuente_alimentacion articulo = lista.get(i);
            int codigoReferencia = articulo.getCodigo_ref();
            String articuloInfo = "  CodRef-" + String.format("%04d", codigoReferencia) + "- \t" + articulo.getModelo() + " \tPrecio € " + articulo.getPrecio() + "    \t[stock: " + articulo.getStock() + "]";
            articuloInfo = articuloInfo.replaceAll("\t", "           ");

            listaInfo.add(articuloInfo);
            if (articulo.getRutaImagen() == null) {
                listaRuta.add("/images/error.png");
            } else {
                listaRuta.add(articulo.getRutaImagen());
            }

            listModel.add(i, articuloInfo);
        }

        client.listaProductos.setModel(listModel);
        client.listaProductos.setCellRenderer(new ListaDinamicaImagen(listaInfo, listaRuta, "Fuente_alimentacion"));
    }

    /**
     * Crea una Jlist con informacion de todas las memorias ram que hay en la
     * base de datos.
     */
    private void cargarListaRam() {
        DefaultListModel listModel = new DefaultListModel();
        ArrayList<Memoria_RAM> lista = daoRam.getAllMemoria_RAM();

        ArrayList<String> listaInfo = new ArrayList<>();
        ArrayList<String> listaRuta = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {
            Memoria_RAM articulo = lista.get(i);
            int codigoReferencia = articulo.getCodigo_ref();
            String articuloInfo = "  CodRef-" + String.format("%04d", codigoReferencia) + "- \t" + articulo.getModelo() + " \tPrecio € " + articulo.getPrecio() + "    \t[stock: " + articulo.getStock() + "]";
            articuloInfo = articuloInfo.replaceAll("\t", "           ");

            listaInfo.add(articuloInfo);
            if (articulo.getRutaImagen() == null) {
                listaRuta.add("/images/error.png");
            } else {
                listaRuta.add(articulo.getRutaImagen());
            }

            listModel.add(i, articuloInfo);
        }

        client.listaProductos.setModel(listModel);
        client.listaProductos.setCellRenderer(new ListaDinamicaImagen(listaInfo, listaRuta, "Memoria_RAM"));
    }

    /**
     * Crea una Jlist con informacion de todos los discos duros que hay en la
     * base de datos.
     */
    private void cargarListaDisco() {
        DefaultListModel listModel = new DefaultListModel();
        ArrayList<Disco_duro> lista = daoDisco.getAllDiscos();

        ArrayList<String> listaInfo = new ArrayList<>();
        ArrayList<String> listaRuta = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {
            Disco_duro articulo = lista.get(i);
            int codigoReferencia = articulo.getCodigo_ref();
            String articuloInfo = "  CodRef-" + String.format("%04d", codigoReferencia) + "- \t" + articulo.getModelo() + " \tPrecio € " + articulo.getPrecio() + "    \t[stock: " + articulo.getStock() + "]";
            articuloInfo = articuloInfo.replaceAll("\t", "           ");

            listaInfo.add(articuloInfo);
            if (articulo.getRutaImagen() == null) {
                listaRuta.add("/images/error.png");
            } else {
                listaRuta.add(articulo.getRutaImagen());
            }

            listModel.add(i, articuloInfo);
        }

        client.listaProductos.setModel(listModel);
        client.listaProductos.setCellRenderer(new ListaDinamicaImagen(listaInfo, listaRuta, "Disco_duro"));
    }

    /**
     * Crea una Jlist con informacion de todas las cajas que hay en la base de
     * datos.
     */
    private void cargarListaCaja() {
        DefaultListModel listModel = new DefaultListModel();
        ArrayList<Caja> lista = daoCaja.getAllCajas();

        ArrayList<String> listaInfo = new ArrayList<>();
        ArrayList<String> listaRuta = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {
            Caja articulo = lista.get(i);
            int codigoReferencia = articulo.getCodigo_ref();
            String articuloInfo = "  CodRef-" + String.format("%04d", codigoReferencia) + "- \t" + articulo.getModelo() + " \tPrecio € " + articulo.getPrecio() + "    \t[stock: " + articulo.getStock() + "]";
            articuloInfo = articuloInfo.replaceAll("\t", "           ");

            listaInfo.add(articuloInfo);
            if (articulo.getRutaImagen() == null) {
                listaRuta.add("/images/error.png");
            } else {
                listaRuta.add(articulo.getRutaImagen());
            }

            listModel.add(i, articuloInfo);
        }

        client.listaProductos.setModel(listModel);
        client.listaProductos.setCellRenderer(new ListaDinamicaImagen(listaInfo, listaRuta, "Caja"));
    }

    /**
     * Crea una Jlist con informacion de todos los portatiles que hay en la base
     * de datos.
     */
    private void cargarListaPortatil() {
        DefaultListModel listModel = new DefaultListModel();
        ArrayList<Portatil> lista = daoPortatil.getAllPortatil();

        ArrayList<String> listaInfo = new ArrayList<>();
        ArrayList<String> listaRuta = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {
            Portatil articulo = lista.get(i);
            int codigoReferencia = articulo.getCodigo_ref();
            String articuloInfo = "  CodRef-" + String.format("%04d", codigoReferencia) + "- \t" + articulo.getModelo() + " \tPrecio € " + articulo.getPrecio() + "    \t[stock: " + articulo.getStock() + "]";
            articuloInfo = articuloInfo.replaceAll("\t", "           ");

            listaInfo.add(articuloInfo);
            if (articulo.getRutaImagen() == null) {
                listaRuta.add("/images/error.png");
            } else {
                listaRuta.add(articulo.getRutaImagen());
            }

            listModel.add(i, articuloInfo);
        }

        client.listaProductos.setModel(listModel);
        client.listaProductos.setCellRenderer(new ListaDinamicaImagen(listaInfo, listaRuta, "Portatil"));
    }

    /**
     * Crea una Jlist con informacion de todas las torres pc que hay en la base
     * de datos.
     */
    private void cargarListaPcTorre() {
        DefaultListModel listModel = new DefaultListModel();
        ArrayList<PcTorre> lista = daoPctorre.getAllPcTorre();

        ArrayList<String> listaInfo = new ArrayList<>();
        ArrayList<String> listaRuta = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {
            PcTorre articulo = lista.get(i);
            int codigoReferencia = articulo.getCodigo_ref();
            String articuloInfo = "  CodRef-" + String.format("%04d", codigoReferencia) + "- \t" + articulo.getModelo() + " \tPrecio € " + articulo.getPrecio() + "    \t[stock: " + articulo.getStock() + "]";
            articuloInfo = articuloInfo.replaceAll("\t", "           ");

            listaInfo.add(articuloInfo);
            if (articulo.getRutaImagen() == null) {
                listaRuta.add("/images/error.png");
            } else {
                listaRuta.add(articulo.getRutaImagen());
            }

            listModel.add(i, articuloInfo);
        }

        client.listaProductos.setModel(listModel);
        client.listaProductos.setCellRenderer(new ListaDinamicaImagen(listaInfo, listaRuta, "PcTorre"));
    }

    /**
     * Metodo para comprobar el formulario para cambiar la password en el panel
     * del perfil de cliente.
     *
     * @return Devuelve si la password es o no correcta.
     */
    private boolean comprobarPassword() {
        boolean correcto = false;
        try {
            if (client.nuevaPass.getPassword().length != 0) {
                if ((client.nuevaPass.getPassword().length != 0 && client.repitePass.getPassword().length != 0)) {
                    String passNueva = Arrays.toString(client.nuevaPass.getPassword());
                    String passRepite = Arrays.toString(client.repitePass.getPassword());
                    if (passNueva.equals(passRepite)) {
                        char[] valorContrasenna = client.nuevaPass.getPassword();
                        passNueva = new String(valorContrasenna);
                        String passAnterior = cliente.getPass();
                        if (passNueva.equals(passAnterior)) {
                            JOptionPane.showMessageDialog(null, "ERROR: La nueva contraseña es la misma que la anterior");
                        } else {
                            correcto = true;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "ERROR: Las contraseñas no coinciden");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR: Introduzca la nueva contraseña");
                }
            }
        } catch (Exception ex) {
            Log.log.error("Error en comprobar password " + ex);
        }
        return correcto;
    }

    /**
     * Comprueba si tiene alguna evaluacion el articulo hecha por el cliente y
     * establece los parametros de las etiquetas y botones activandolos y
     * desactivandolos en funcion del resultado de la consulta.
     *
     * @param codigo
     */
    private void comprobarEvaluacionRealizada(int codigo) {

        try {
            // Pone el texto con la media del producto
            String textMedia = String.valueOf(consultaArticulo.mediaEvaluacion(codigo));
            client.datoMediaEvaluacion.setText("Puntuacion media: " + textMedia);
            // Si el cliente no ha evaluado el producto activamos para que lo valore
            if (!consultaArticulo.comprobarEvaluacion(cliente.getEmail(), codigo)) {
                client.puntuacion1.setEnabled(true);
                client.puntuacion2.setEnabled(true);
                client.puntuacion3.setEnabled(true);
                client.puntuacion4.setEnabled(true);
                client.puntuacion5.setEnabled(true);
                client.confirmaPuntuacion.setVisible(false); //Inicia etiqueta puntuacion en no visible
            } else {
                client.puntuacion1.setEnabled(false);
                client.puntuacion2.setEnabled(false);
                client.puntuacion3.setEnabled(false);
                client.puntuacion4.setEnabled(false);
                client.puntuacion5.setEnabled(false);
                client.confirmaPuntuacion.setVisible(true);
            }
        } catch (Exception ex) {
            Log.log.error("Error en comprobar media puntucion " + ex);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
