
package Controller;

import DAO.*;
import Model.Articulos.Articulo;
import Model.Negocio.Pedido;
import Model.Usuario.Empleado;
import Model.Usuario.Tienda;
import Util.ListaDinamica;
import Util.ListaDinamicaImagen;
import Views.InicioEmpleado;
import Views.Login;
import java.awt.Cursor;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import util.Log;

public class EmpleadoController implements ActionListener{
    
    private final InicioEmpleado inicio;
    private final Empleado empleado;
    private final Tienda tienda;
    
    private final UsuarioDao consultaUsuario = new UsuarioDao();
    
    private final PedidoDao consultaPedido = new PedidoDao();
    private final ArticuloDao consultaArticulo = new ArticuloDao();
    private final CajaDao consultaCaja = new CajaDao();
    private final DiscoDuroDao consultaDiscoDuro = new DiscoDuroDao();
    private final FuenteDao consultaFuente = new FuenteDao();
    private final GraficaDao consultaGrafica = new GraficaDao();
    private final MemoriaRAMDao consultaRAM = new MemoriaRAMDao();
    private final MonitorDao consultaMonitor = new MonitorDao();
    private final PcTorreDao consultaTorre = new PcTorreDao();
    private final Placa_baseDao consultaPlacaBase = new Placa_baseDao();
    private final PortatilDao consultaPortatil = new PortatilDao();
    private final ProcesadorDao consultaProcesador = new ProcesadorDao();
    private final RatonDao consultaRaton = new RatonDao();
    private final TecladoDao consultaTeclado = new TecladoDao();
    private final WebCamDao consultaWebCam = new WebCamDao();
    
    private String claveBusqueda;
    
    public EmpleadoController(InicioEmpleado inicioVista, Empleado usuario){
        this.inicio = inicioVista;
        this.empleado = usuario;
        this.tienda = consultaUsuario.getTienda(empleado.getID_Tienda());
        this.claveBusqueda = "";
    }
    
    /**
     * Funcion que inicializa toda la vista InicioEmpleado y crea todos los listeners para 
     * todos los botones de la interfaz
     *
     */
    public void iniciar(){
        inicio.setTitle("INICIO - EMPLEADOS");
        inicio.setLocationRelativeTo(null);
        inicio.setResizable(false);
        inicio.setSize(1200, 750);
        
        iniciarPanelInicio();
        
        //Boton para editar los datos del empleado
        this.inicio.btnEditarDatos.addActionListener(this);
        
        //Boton para editar los datos de un producto
        this.inicio.btnEditarProducto.addActionListener(this);
        
        //Boton para borrar un producto
        this.inicio.btnBorrarProducto.addActionListener(this);
        
        //Boton para añadir un nuevo producto al catalogo
        this.inicio.btnAnadirNuevoArticulo.addActionListener(this);
        
        //Boton para cerrar sesion
        this.inicio.btnCerrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Login loginVista = new Login();
        
                LoginController login = new LoginController(loginVista);
        
                login.iniciar();
                inicio.setVisible(false);
                loginVista.setVisible(true);
            }
        });
        
        //Boton para buscar por palabra clave
        this.inicio.btnBuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!inicio.barraBusqueda.getText().equalsIgnoreCase("")){
                    String clave = inicio.barraBusqueda.getText();
                    ArrayList<Articulo> listaArticulos = consultaArticulo.buscarArticuloPalabraClave(clave);
                    
                    //Si la lista de los articulos con la clave de busqueda no es vacia, es decir, se ha encontrado algun
                    //articulo, cargamos la lista de los articulos
                    if(listaArticulos.size() > 0){
                        setClaveBusqueda(clave);
                        cargarListaProductos(listaArticulos);
                    
                        inicio.panelInicio.setVisible(false);
                        inicio.panelAnadir.setVisible(false);
                        inicio.panelEditarPerfil.setVisible(false);
                        inicio.panelCompras.setVisible(false);
                        inicio.panelEditarProducto.setVisible(false);
                        inicio.panelInfoPedido.setVisible(false);

                        inicio.panelElegirProducto.setVisible(true);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "ERROR: No se ha encontrado nigún artículo con la clave - "+ clave +".");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "ERROR: Introduzca la clave de búsqueda.");
                }
            }
        });
        
        //Boton para ir al perfil
        this.inicio.btnPerfil.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                inicio.panelAnadir.setVisible(false);
                inicio.panelEditarPerfil.setVisible(false);
                inicio.panelCompras.setVisible(false);
                inicio.panelElegirProducto.setVisible(false);
                inicio.panelEditarProducto.setVisible(false);
                inicio.panelInfoPedido.setVisible(false);
                
                inicio.panelInicio.setVisible(true);
            }
        });
        
        //Boton para añadir un nuevo producto
        this.inicio.btnAnnadir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                inicio.panelInicio.setVisible(false);
                inicio.panelEditarPerfil.setVisible(false);
                inicio.panelCompras.setVisible(false);
                inicio.panelElegirProducto.setVisible(false);
                inicio.panelEditarProducto.setVisible(false);
                inicio.panelInfoPedido.setVisible(false);
                
                inicio.panelAnadir.setVisible(true);
                
                iniciarPanelAnadir();
            }
        });
        
        //Boton para editar el perfil de usuario
        this.inicio.btnEditarPerfil.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                inicio.panelInicio.setVisible(false);
                inicio.panelAnadir.setVisible(false);
                inicio.panelCompras.setVisible(false);
                inicio.panelElegirProducto.setVisible(false);
                inicio.panelEditarProducto.setVisible(false);
                inicio.panelInfoPedido.setVisible(false);
                
                inicio.panelEditarPerfil.setVisible(true);
                
                iniciarPanelEditarUsuario();
            }
        });
        
        //Boton para ver todos las compras
        this.inicio.btnCompras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                inicio.panelInicio.setVisible(false);
                inicio.panelAnadir.setVisible(false);
                inicio.panelEditarPerfil.setVisible(false);
                inicio.panelElegirProducto.setVisible(false);
                inicio.panelEditarProducto.setVisible(false);
                inicio.panelInfoPedido.setVisible(false);                
                
                inicio.panelCompras.setVisible(true);
                
                cargarListaPedidos();
            }
        });
        
        //Boton para elegir el articulo a editar
        this.inicio.btnEditarArticulo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                inicio.panelInicio.setVisible(false);
                inicio.panelAnadir.setVisible(false);
                inicio.panelEditarPerfil.setVisible(false);
                inicio.panelCompras.setVisible(false);
                inicio.panelEditarProducto.setVisible(false);
                inicio.panelInfoPedido.setVisible(false);
                
                inicio.panelElegirProducto.setVisible(true);
                
                ArrayList<Articulo> listaArticulos = consultaArticulo.getAllArticulos();
                cargarListaProductos(listaArticulos);
                
                setClaveBusqueda("");
            }
        });
        
        //Evento para cuando se clicka en un pedido
        this.inicio.listaPedidos.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting()) {
                    inicio.panelInicio.setVisible(false);
                    inicio.panelAnadir.setVisible(false);
                    inicio.panelEditarPerfil.setVisible(false);
                    inicio.panelCompras.setVisible(false);
                    inicio.panelElegirProducto.setVisible(false);
                    inicio.panelEditarProducto.setVisible(false);
                    
                    inicio.panelInfoPedido.setVisible(true);
                    
                    Pedido pedido = consultaPedido.getAllPedidos().get(inicio.listaPedidos.getSelectedIndex());
                    
                    inicio.correoInfoPedido.setText(pedido.getEmail_cliente());
                    inicio.nPedidoInfo.setText(""+ pedido.getIdPedido() +"     ["+ pedido.getFecha() +"]");
                    
                    cargarPedidoInfo(pedido);
                }
            }
        });
        
        //Evento para cuando se clicka en un producto y nos manda al panel para editarlo
        this.inicio.listaProductos.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting()) {
                    inicio.panelInicio.setVisible(false);
                    inicio.panelAnadir.setVisible(false);
                    inicio.panelEditarPerfil.setVisible(false);
                    inicio.panelCompras.setVisible(false);
                    inicio.panelElegirProducto.setVisible(false);
                    inicio.panelInfoPedido.setVisible(false);
                    
                    inicio.panelEditarProducto.setVisible(true);
                    
                    ArrayList<Articulo> listaArticulos;
                    int eleccion = inicio.listaProductos.getSelectedIndex();
                    
                    //Si no hay niguna clave de búsqueda quiere decir que el usuario quiere ver todos los artículos
                    if(getClaveBusqueda().equalsIgnoreCase("")){
                        listaArticulos = consultaArticulo.getAllArticulos();
                        cargarProductoEdit(eleccion, listaArticulos);
                    }
                    //Si hay un clave solo quiere ver los articulos que contegan la clave
                    else{
                        listaArticulos = consultaArticulo.buscarArticuloPalabraClave(getClaveBusqueda());
                        cargarProductoEdit(eleccion, listaArticulos);
                    }
                    
                    //Volvemos a poner la clave de búsqueda vacia
                    setClaveBusqueda("");
                }
            }
        });
        
        //Evento para generar atributos dinamicos en funcion del artículo que se quiera añadir
        this.inicio.tipoArticuloAnadir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                esconderAtributos();
                String eleccion = inicio.tipoArticuloAnadir.getSelectedItem().toString();
                if(!eleccion.equalsIgnoreCase("Seleccione")){
                    switch(eleccion){
                        case "Caja":
                            iniciarAtributo1("Cristal");
                            break;
                        case "Disco duro":
                            iniciarAtributo1("Tipo");
                            break;
                        case "Fuente alimentacion":
                            iniciarAtributo1("Potencia");
                            iniciarAtributo2("Certificacion");
                            break;
                        case "Grafica":
                            iniciarAtributo1("Generacion");
                            break;
                        case "RAM":
                            iniciarAtributo1("PN");
                            break;
                        case "Monitor":
                            iniciarAtributo1("Pulgadas");
                            iniciarAtributo2("Panel");
                            iniciarAtributo3("Hz");
                            break;
                        case "Torre":
                            iniciarAtributo1("Nombre");
                            break;
                        case "Placa base":
                            iniciarAtributo1("Socket");
                            break;
                        case "Portatil":
                            iniciarAtributo1("Panel");
                            iniciarAtributo2("Peso");
                            break;
                        case "Procesador":
                            iniciarAtributo1("Socket");
                            break;
                        case "Raton":
                            iniciarAtributo1("DPI");
                            iniciarAtributo2("Tipo");
                            iniciarAtributo3("Peso");
                            break;
                        case "Teclado":
                            iniciarAtributo1("Tipo");
                            break;
                        case "WebCam":
                            iniciarAtributo1("Calidad");
                            break;
                        default:
                            break;
                    }
                }
            }
        });
    }
    
    //****************************************************************************************************************************************
    //**                                                                                                                                    **
    //****************************************************************************************************************************************
    /**
     * Carga la información e imagen referente a un artículo seleccionado previamente por el
     * usuario
     *
     * @param codigoReferencia código del artículo seleccionado
     * @param listaArticulos lista con todos los artículos de la tienda
     */
    public void cargarProductoEdit(int codigoReferencia, ArrayList<Articulo> listaArticulos){
        Articulo articulo = listaArticulos.get(codigoReferencia);
        
        inicio.idProductoEdit.setText(""+articulo.getCodigo_ref());
        
        inicio.modeloEdit.setText(articulo.getModelo());
        inicio.descripcionEdit.setText(articulo.getDescripcion());
        inicio.precioEdit.setText(""+articulo.getPrecio());
        inicio.stockEdit.setText(""+articulo.getStock());
        
        if(articulo.getRutaImagen() == null){
            inicio.imgProductoEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fotopc.png")));
        }
        else if(getClass().getResource(articulo.getRutaImagen()) == null){
            inicio.imgProductoEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/error.png")));
        }
        else{
            inicio.imgProductoEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource(articulo.getRutaImagen())));
        }
    }
    
    /**
     * Carga la información e imagen referente a un artículo seleccionado en el jList tras realizar una 
     * búsqueda en la base de datos
     *
     * @param codigoReferencia codigo del artículo seleccionado
     */
    public void cargarProductoBusquedaEdit(int codigoReferencia){
        ArrayList<Articulo> listaArticulos = consultaArticulo.getAllArticulos();
        Articulo articulo = listaArticulos.get(codigoReferencia);
        
        inicio.idProductoEdit.setText(""+articulo.getCodigo_ref());
        
        inicio.modeloEdit.setText(articulo.getModelo());
        inicio.descripcionEdit.setText(articulo.getDescripcion());
        inicio.precioEdit.setText(""+articulo.getPrecio());
        inicio.stockEdit.setText(""+articulo.getStock());
        
        if(articulo.getRutaImagen() == null){
            inicio.imgProductoEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fotopc.png")));
        }
        else if(getClass().getResource(articulo.getRutaImagen()) == null){
            inicio.imgProductoEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/error.png")));
        }
        else{
            inicio.imgProductoEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource(articulo.getRutaImagen())));
        }
    }
    
    /**
     * Introduce de forma dinámica la información referente a los artículos de la base de datos
     * para mostrarla en un componente jList, con la imagen e información
     * 
     * @param lista lista de artículos
     */
    public void cargarListaProductos(ArrayList<Articulo> lista){
        DefaultListModel listModel = new DefaultListModel();
        ArrayList<String> listaInfo = new ArrayList<>();
        ArrayList<String> listaRuta = new ArrayList<>();

        for(int i=0; i<lista.size(); i++){
            Articulo articulo = lista.get(i);
            int codigoReferencia = articulo.getCodigo_ref();
            String articuloInfo = "  CodRef-"+ String.format("%04d", codigoReferencia) +" \t"+ articulo.getModelo() +" \tPrecio-"+ articulo.getPrecio() +"    \t[stock: "+ articulo.getStock() +"]";
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

        inicio.listaProductos.setModel(listModel);
        inicio.listaProductos.setCellRenderer(new ListaDinamicaImagen(listaInfo, listaRuta, "Articulo"));
    }
    
    /**
     * Introduce de forma dinámica la información referente a los pedidos realizados por los clientes, mostrando de
     * cada uno, una breve descripción
     *
     */
    public void cargarListaPedidos(){
        DefaultListModel listModel = new DefaultListModel();
        ArrayList<Pedido> listaPedidos = consultaPedido.getAllPedidos();
        for(int i=0; i<listaPedidos.size(); i++){
            Pedido pedido = listaPedidos.get(i);
            int idPedido = pedido.getIdPedido();
            String pedidoInfo = "  ID-"+ String.format("%04d", idPedido) +" \tFecha-"+ pedido.getFecha() +" \tPrecio pedido-"+ pedido.getPrecio_total() +"€ \tUsuario-"+ pedido.getEmail_cliente();
            pedidoInfo = pedidoInfo.replaceAll("\t", "         ");
            
            listModel.add(i, pedidoInfo);
        }
        
        inicio.listaPedidos.setModel(listModel);
        inicio.listaPedidos.setCellRenderer(new ListaDinamica("Pedido"));
    }
    
    /**
     * Carga la información referente todos los arículos de un pedido en concreto
     *
     * @param pedido
     */
    public void cargarPedidoInfo(Pedido pedido){
        DefaultListModel listModel = new DefaultListModel();
        
        ArrayList<String> listaInfo = new ArrayList<>();
        ArrayList<String> listaRuta = new ArrayList<>();
        
        ArrayList<Integer> listaCodigos = pedido.getListaArticulos();
        
        ArrayList<Articulo> listaCompras = new ArrayList<>();
        for(int i=0; i< listaCodigos.size(); i++){
            listaCompras.add(consultaArticulo.getArticulo(listaCodigos.get(i)));
        }
        
        for(int i=0; i<listaCompras.size(); i++){
            Articulo articulo = listaCompras.get(i);
            int codigoReferencia = articulo.getCodigo_ref();
            String articuloInfo = "  CodRef-"+ String.format("%04d", codigoReferencia) +" \t"+ articulo.getModelo() +" \tPrecio-"+ articulo.getPrecio();
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

        inicio.listaInfoPedido.setModel(listModel);
        inicio.listaInfoPedido.setCellRenderer(new ListaDinamicaImagen(listaInfo, listaRuta, "Articulo"));
    }
    
    
    //****************************************************************************************************************************************
    //**                                                                                                                                    **
    //****************************************************************************************************************************************
    /**
     * Inicializa todos los elementos del panel de inicio, el cual tiene la información sobre el
     * empleado que inicia sesión en la aplicación
     *
     */
    public void iniciarPanelInicio(){
        inicio.nombreUsuario.setText("Bienvenido "+ empleado.getNombre() +" "+ empleado.getApellido());
        inicio.datoNombre.setText(empleado.getNombre());
        inicio.datoApellido.setText(empleado.getApellido());
        inicio.datoEmail.setText(empleado.getEmail());
        inicio.datoDireccion.setText(empleado.getDireccion());
        inicio.datoTelefono.setText(""+empleado.getTelefono());
        inicio.datoDni.setText(empleado.getDNI());
        
        inicio.idTienda.setText(""+tienda.getId_tienda());
        inicio.nombreTienda.setText(tienda.getNombre());
        inicio.direccionTienda.setText(tienda.getDireccion());
        inicio.codTienda.setText(""+tienda.getCodigo_postal());
        inicio.ciudadTienda.setText(tienda.getCuidad());
        inicio.provinciaTienda.setText(tienda.getProvincia());
        
        inicio.barraBusqueda.setEditable(true);
        
        inicio.panelEditarPerfil.setVisible(false);
        inicio.panelCompras.setVisible(false);
        inicio.panelElegirProducto.setVisible(false);
        inicio.panelEditarProducto.setVisible(false);
        inicio.panelInfoPedido.setVisible(false);
        inicio.panelAnadir.setVisible(false);
        
        inicio.panelInicio.setVisible(true);
    }
    
    /**
     * Inicializa todos los elementos del panel de editar información personal
     *
     */
    public void iniciarPanelEditarUsuario(){
        inicio.tituloUsuarioId.setText("USUARIO "+ empleado.getEmail());
        inicio.nombreEdit.setText(empleado.getNombre());
        inicio.apellidoEdit.setText(empleado.getApellido());
        inicio.direccionEdit.setText(empleado.getDireccion());
        inicio.telefonoEdit.setText(""+empleado.getTelefono());
        inicio.passActualEdit.setText("");
        inicio.passNuevaEdit.setText("");
        inicio.passRepitaEdit.setText("");
    }
    
    /**
     * Inicializa todos los elemento del panel de añadir un nuevo artículo al catálogo de la tienda
     *
     */
    public void iniciarPanelAnadir(){
        String listaTipos[] = {"Seleccione", "Caja", "Disco duro", "Fuente alimentacion", "Grafica", "RAM", "Monitor", 
            "Torre", "Placa base", "Portatil", "Procesador", "Raton", "Teclado", "WebCam"};
        
        for (String item : listaTipos) {
            inicio.tipoArticuloAnadir.addItem(item);
        }
        
        reiniciarPanelAnadir();
        inicio.codRefAnadir.setText(""+ consultaArticulo.getCodRefMax());
    }
    
    /**
     * Inicializa el atributo opcional 1 para el panel de añadir un nuevo artículo
     *
     * @param nombre del atributo que se mostrará en el jLabel
     */
    public void iniciarAtributo1(String nombre){
        inicio.atributo1Label.setText(nombre);
        inicio.atributo1Label.setVisible(true);
        inicio.atributo1Anadir.setVisible(true);
        inicio.atributo1Anadir.setText("");
    }
    
    /**
     * Inicializa el atributo opcional 2 para el panel de añadir un nuevo artículo
     *
     * @param nombre del atributo que se mostrará en el jLabel
     */
    public void iniciarAtributo2(String nombre){
        inicio.atributo2Label.setText(nombre);
        inicio.atributo2Label.setVisible(true);
        inicio.atributo2Anadir.setVisible(true);
        inicio.atributo2Anadir.setText("");
    }
    
    /**
     * Inicializa el atributo opcional 3 para el panel de añadir un nuevo artículo
     *
     * @param nombre del atributo que se mostrará en el jLabel
     */
    public void iniciarAtributo3(String nombre){
        inicio.atributo3Label.setText(nombre);
        inicio.atributo3Label.setVisible(true);
        inicio.atributo3Anadir.setVisible(true);
        inicio.atributo3Anadir.setText("");
    }
    
    /**
     * Esconde y reinicia los jLabel y jTextField de los atributos opcionales del panel de añadir un 
     * nuevo artículo
     *
     */
    public void esconderAtributos(){
        inicio.atributo1Label.setVisible(false);
        inicio.atributo1Anadir.setVisible(false);
        inicio.atributo1Anadir.setText("nadaDeNada");
        inicio.atributo2Label.setVisible(false);
        inicio.atributo2Anadir.setVisible(false);
        inicio.atributo2Anadir.setText("nadaDeNada");
        inicio.atributo3Label.setVisible(false);
        inicio.atributo3Anadir.setVisible(false);
        inicio.atributo3Anadir.setText("nadaDeNada");
    }
    
    /**
     * Vacia todos los jTextField del panel de añadir un nuevo artículo
     *
     */
    public void reiniciarPanelAnadir(){
        inicio.modeloAnadir.setText("");
        inicio.precioAnadir.setText("");
        inicio.descripcionAnadir.setText("");
        inicio.stockAnadir.setText("");
        inicio.rutaImagenAnadir.setText("");
    }
    
    
    //****************************************************************************************************************************************
    //**                                                                                                                                    **
    //****************************************************************************************************************************************
    /**
     * Comprueba que todos los jTextField del formulario para editar los datos de un empleado, estén
     * rellenados de forma correcta y controla el cambio de contraseña
     *
     * @return Devuelve si se el formulario esta bien rellenado
     */
    public boolean comprobarFormularioEditarEmpleado(){
        boolean correcto = false;
        
        if(inicio.nombreEdit.getText().equalsIgnoreCase("") || inicio.apellidoEdit.getText().equalsIgnoreCase("") 
                || inicio.direccionEdit.getText().equalsIgnoreCase("") || inicio.telefonoEdit.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "ERROR: Rellene los campos Nombre, Apellido, Direccion, Telefono.");
        }
        else{
            if(inicio.passActualEdit.getPassword().length != 0){
                if((inicio.passNuevaEdit.getPassword().length != 0 && inicio.passRepitaEdit.getPassword().length != 0)){
                    char[] valorPassNueva = inicio.passNuevaEdit.getPassword();
                    String passNueva = new String(valorPassNueva);
                    char[] valorPassRepite = inicio.passRepitaEdit.getPassword();
                    String passRepite = new String(valorPassRepite);
                    
                    if(passNueva.equals(passRepite)){
                        char[] valorPassAnterior = inicio.passActualEdit.getPassword();
                        String passAnterior = new String(valorPassAnterior);
                        if(passNueva.equals(passAnterior)){
                            JOptionPane.showMessageDialog(null, "ERROR: La nueva contraseña es la misma que la anterior.");
                        }
                        else if(!passAnterior.equals(empleado.getPass())){
                            JOptionPane.showMessageDialog(null, "ERROR: Contraseña anterior incorrecta.");
                        }
                        else{
                            correcto = true;
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "ERROR: Las contraseñas no coinciden.");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "ERROR: Introduzca la nueva contraseña.");
                }
            }
            else{
                correcto = true;
            }
        }
        
        return correcto;
    }
    
    /**
     * Comprueba que todos los jTextField del formulario para editar los datos de un producto, estén
     * rellenados de forma correcta
     *
     * @return Devuelve si se el formulario esta bien rellenado
     */
    public boolean comprobarFormularioEditarProducto(){
        boolean correcto = false;
        
        if(inicio.modeloEdit.getText().equalsIgnoreCase("") || inicio.stockEdit.getText().equalsIgnoreCase("") 
                || inicio.precioEdit.getText().equalsIgnoreCase("") || inicio.descripcionEdit.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "ERROR: Rellene los campos Modelo, Stock, Precio, Descripcion");
        }
        else{
            correcto = true;
        }
        
        return correcto;
    }
    
    /**
     * Comprueba que todos los jTextField del formulario para añadir un nuevo producto, estén
     * rellenados de forma correcta
     *
     * @return Devuelve si se el formulario esta bien rellenado
     */
    public boolean comprobarFormularioAnadirProducto(){
        boolean correcto = false;
        
        if(inicio.tipoArticuloAnadir.getSelectedItem().toString().equalsIgnoreCase("Seleccione")){
            JOptionPane.showMessageDialog(null, "ERROR: Seleccione el tipo de producto");
        }
        else{
            if(inicio.modeloAnadir.getText().equalsIgnoreCase("") || inicio.codRefAnadir.getText().equalsIgnoreCase("") || inicio.descripcionAnadir.getText().equalsIgnoreCase("")
                    || inicio.stockAnadir.getText().equalsIgnoreCase("") || inicio.precioAnadir.getText().equalsIgnoreCase("") || inicio.rutaImagenAnadir.getText().equalsIgnoreCase("")
                    || inicio.atributo1Anadir.getText().equalsIgnoreCase("") || inicio.atributo2Anadir.getText().equalsIgnoreCase("") || inicio.atributo3Anadir.getText().equalsIgnoreCase("")){
                JOptionPane.showMessageDialog(null, "ERROR: Rellene todos los campos");
            }
            else{
                correcto = true;
            }
        }
        
        return correcto;
    }
    
    
    //****************************************************************************************************************************************
    //**                                                                                                                                    **
    //****************************************************************************************************************************************
    @Override
    public void actionPerformed(ActionEvent boton){
        if(boton.getSource() == inicio.btnEditarDatos){
            try{
                Log.log.info("Vista Inicio - evento editarDatos");

                if(comprobarFormularioEditarEmpleado()){
                    String correo = empleado.getEmail();
                    String pass = empleado.getPass();
                    String nombre = inicio.nombreEdit.getText();
                    String apellido = inicio.apellidoEdit.getText();
                    String direccion = inicio.direccionEdit.getText();
                    int telefono = Integer.parseInt(inicio.telefonoEdit.getText());

                    boolean exito;

                    //Si quiere cambiar la contraseña cogemos el valor
                    if(inicio.passNuevaEdit.getPassword().length != 0){
                        char[] valorContrasenna = inicio.passNuevaEdit.getPassword();
                        String contrasenna = new String(valorContrasenna);
                        pass = contrasenna;
                        exito = consultaUsuario.editarUsuarioPass(correo, nombre, apellido, direccion, telefono, contrasenna);
                    }
                    else{
                        exito = consultaUsuario.editarUsuario(correo, nombre, apellido, direccion, telefono);
                    }

                    if(exito){
                        JOptionPane.showMessageDialog(null, "Tus datos se han modificado con éxito.");
                        empleado.setEmail(correo);
                        empleado.setNombre(nombre);
                        empleado.setApellido(apellido);
                        empleado.setDireccion(direccion);
                        empleado.setTelefono(telefono);
                        empleado.setPass(pass);
                        iniciarPanelInicio();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "ERROR: No se pudieron modificar sus datos");
                    }
                }
            }catch(NumberFormatException error){
                JOptionPane.showMessageDialog(null, "ERROR "+ error.getMessage() +"\nFormato incorrecto.\nIntroduzca de nuevo");
            }
        }
        else if(boton.getSource() == inicio.btnEditarProducto){
            Log.log.info("Vista Inicio - evento editarProducto");
            try{
                if(comprobarFormularioEditarProducto()){
                    int codigoReferencia = Integer.parseInt(inicio.idProductoEdit.getText());
                    String modelo = inicio.modeloEdit.getText();
                    int stock = Integer.parseInt(inicio.stockEdit.getText());
                    float precio = Float.parseFloat(inicio.precioEdit.getText());
                    String descripcion = inicio.descripcionEdit.getText();

                    boolean exito = consultaArticulo.editarArticulo(codigoReferencia, modelo, descripcion, stock, precio);

                    if(exito){
                        JOptionPane.showMessageDialog(null, "Datos de artículo se han modificado con éxito.");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "ERROR: No se pudieron modificar los datos del artículo");
                    }
                }
            }catch(NumberFormatException error){
                JOptionPane.showMessageDialog(null, "ERROR "+ error.getMessage() +"\nFormato incorrecto.\nIntroduzca de nuevo");
            }
        }
        else if(boton.getSource() == inicio.btnAnadirNuevoArticulo){
            try{
                if(comprobarFormularioAnadirProducto()){
                    boolean exito = false;
                    String eleccion = inicio.tipoArticuloAnadir.getSelectedItem().toString();

                    String modelo = inicio.modeloAnadir.getText();
                    int codigoReferncia = Integer.parseInt(inicio.codRefAnadir.getText());
                    float precio = Float.parseFloat(inicio.precioAnadir.getText());
                    String descripcion = inicio.descripcionAnadir.getText();
                    int stock = Integer.parseInt(inicio.stockAnadir.getText());
                    String rutaImagen = "/images/"+ inicio.rutaImagenAnadir.getText() +".png";
                    int idTienda = empleado.getID_Tienda();

                    switch(eleccion){
                        case "Caja":
                            boolean cristal = !inicio.atributo1Anadir.getText().equalsIgnoreCase("no");

                            exito = consultaCaja.anadirCaja(modelo, codigoReferncia, precio, descripcion, stock, rutaImagen, idTienda, cristal);
                            break;
                        case "Disco duro":
                            String tipoDisco = inicio.atributo1Anadir.getText();

                            exito = consultaDiscoDuro.anadirDiscoDuro(modelo, codigoReferncia, precio, descripcion, stock, rutaImagen, idTienda, tipoDisco);
                            break;
                        case "Fuente alimentacion":
                            int potencia = Integer.parseInt(inicio.atributo1Anadir.getText());
                            String certificacion = inicio.atributo2Anadir.getText();

                            exito = consultaFuente.anadirFuente(modelo, codigoReferncia, precio, descripcion, stock, rutaImagen, idTienda, potencia, certificacion);
                            break;
                        case "Grafica":
                            int generacion = Integer.parseInt(inicio.atributo1Anadir.getText());

                            exito = consultaGrafica.anadirGrafica(modelo, codigoReferncia, precio, descripcion, stock, rutaImagen, idTienda, generacion);
                            break;
                        case "RAM":
                            String pn = inicio.atributo1Anadir.getText();

                            exito = consultaRAM.anadirRAM(modelo, codigoReferncia, precio, descripcion, stock, rutaImagen, idTienda, pn);
                            break;
                        case "Monitor":
                            int pulgadas = Integer.parseInt(inicio.atributo1Anadir.getText());
                            String panelMonitor = inicio.atributo2Anadir.getText();
                            int hz = Integer.parseInt(inicio.atributo3Anadir.getText());

                            exito = consultaMonitor.anadirMonitor(modelo, codigoReferncia, precio, descripcion, stock, rutaImagen, idTienda, pulgadas, panelMonitor, hz);
                            break;
                        case "Torre":
                            String nombre = inicio.atributo1Anadir.getText();

                            exito = consultaTorre.anadirTorre(modelo, codigoReferncia, precio, descripcion, stock, rutaImagen, idTienda, nombre, false);
                            break;
                        case "Placa base":
                            String socketPlaca = inicio.atributo1Anadir.getText();

                            exito = consultaPlacaBase.anadirPlacaBase(modelo, codigoReferncia, precio, descripcion, stock, rutaImagen, idTienda, socketPlaca);
                            break;
                        case "Portatil":
                            String panelPortatil = inicio.atributo1Anadir.getText();
                            int pesoPortatil = Integer.parseInt(inicio.atributo2Anadir.getText());

                            exito = consultaPortatil.anadirPortatil(modelo, codigoReferncia, precio, descripcion, stock, rutaImagen, idTienda, panelPortatil, pesoPortatil);
                            break;
                        case "Procesador":
                            String socketProcesador = inicio.atributo1Anadir.getText();

                            exito = consultaProcesador.anadirProcesador(modelo, codigoReferncia, precio, descripcion, stock, rutaImagen, idTienda, socketProcesador);
                            break;
                        case "Raton":
                            int DPI = Integer.parseInt(inicio.atributo1Anadir.getText());
                            String tipoRaton = inicio.atributo2Anadir.getText();
                            int pesoRaton = Integer.parseInt(inicio.atributo3Anadir.getText());

                            exito = consultaRaton.anadirRaton(modelo, codigoReferncia, precio, descripcion, stock, rutaImagen, idTienda, DPI, tipoRaton, pesoRaton);
                            break;
                        case "Teclado":
                            String tipoTeclado = inicio.atributo1Anadir.getText();

                            exito = consultaTeclado.anadirTeclado(modelo, codigoReferncia, precio, descripcion, stock, rutaImagen, idTienda, tipoTeclado);
                            break;
                        case "WebCam":
                            String calidad = inicio.atributo1Anadir.getText();

                            exito = consultaWebCam.anadirWebCam(modelo, codigoReferncia, precio, descripcion, stock, rutaImagen, idTienda, calidad);
                            break;
                        default:
                            break;
                    }

                    if(exito){
                        JOptionPane.showMessageDialog(null, "Articulo "+ codigoReferncia +" añadido con éxito.");

                        iniciarPanelAnadir();
                        esconderAtributos();
                        inicio.tipoArticuloAnadir.setSelectedIndex(0);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "ERROR: No se pudo añadir el articulo.");
                    }
                }
            }catch(NumberFormatException error){
                JOptionPane.showMessageDialog(null, "ERROR "+ error.getMessage() +"\nFormato incorrecto.\nIntroduzca de nuevo");
            }
        }
        else if(boton.getSource() == inicio.btnBorrarProducto){
            try{
                int codigoReferencia = Integer.parseInt(inicio.idProductoEdit.getText());
                int seleccion = JOptionPane.showConfirmDialog(null, "¿Esta seguro de que desea eliminar el articulo"+ codigoReferencia +"?", "Articulo", JOptionPane.YES_NO_OPTION);

                if (seleccion == 0) {
                    boolean exito = consultaArticulo.eliminarArticulo(codigoReferencia);
                    if(exito){
                        JOptionPane.showMessageDialog(null, "Se ha eliminado el articulo.", "Mensaje", JOptionPane.DEFAULT_OPTION);
                        inicio.panelInicio.setVisible(false);
                        inicio.panelAnadir.setVisible(false);
                        inicio.panelEditarPerfil.setVisible(false);
                        inicio.panelCompras.setVisible(false);
                        inicio.panelEditarProducto.setVisible(false);

                        inicio.panelElegirProducto.setVisible(true);
                        
                        ArrayList<Articulo> listaArticulos = consultaArticulo.getAllArticulos();
                        cargarListaProductos(listaArticulos);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se ha podido eliminar el articulo", "Mensaje", JOptionPane.DEFAULT_OPTION);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Operacion cancelada", "Mensaje", JOptionPane.DEFAULT_OPTION);
                }
            }catch(HeadlessException | NumberFormatException error){
                JOptionPane.showMessageDialog(null, "ERROR: No se pudo borrar el producto.\nInténtelo de nuevo");
            }
        }
    }
    
    
    //****************************************************************************************************************************************
    //**                                                                                                                                    **
    //****************************************************************************************************************************************
    /**
     * Guarda el valor de la clave de búsqueda
     *
     * @param clave palabra clave de la barra de búsqueda
     */
    public void setClaveBusqueda(String clave){
        this.claveBusqueda = clave;
    }
    
    /**
     * Obtiene la clave de búsqueda introducida en la barra de búsqueda
     *
     * @return Devuelve un String con la clave de búsqueda
     */
    public String getClaveBusqueda(){
        return this.claveBusqueda;
    }
}
