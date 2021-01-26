
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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import util.Log;

public class EmpleadoController implements ActionListener{
    
    private InicioEmpleado inicio;
    private Empleado empleado;
    private Tienda tienda;
    
    private UsuarioDao consultaUsuario = new UsuarioDao();
    
    private PedidoDao consultaPedido = new PedidoDao();
    private ArticuloDao consultaArticulo = new ArticuloDao();
    private CajaDao consultaCaja = new CajaDao();
    private DiscoDuroDao consultaDiscoDuro = new DiscoDuroDao();
    private FuenteDao consultaFuente = new FuenteDao();
    private GraficaDao consultaGrafica = new GraficaDao();
    private MemoriaRAMDao consultaRAM = new MemoriaRAMDao();
    private MonitorDao consultaMonitor = new MonitorDao();
    private PcTorreDao consultaTorre = new PcTorreDao();
    private Placa_baseDao consultaPlacaBase = new Placa_baseDao();
    private PortatilDao consultaPortatil = new PortatilDao();
    private ProcesadorDao consultaProcesador = new ProcesadorDao();
    private RatonDao consultaRaton = new RatonDao();
    private TecladoDao consultaTeclado = new TecladoDao();
    private WebCamDao consultaWebCam = new WebCamDao();
    
    public EmpleadoController(InicioEmpleado inicioVista, Empleado usuario){
        this.inicio = inicioVista;
        this.empleado = usuario;
        this.tienda = consultaUsuario.getTienda(empleado.getID_Tienda());
    }
    
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
        
        //Boton para ir al perfil
        this.inicio.btnPerfil.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                inicio.panelAnadir.setVisible(false);
                inicio.panelEditarPerfil.setVisible(false);
                inicio.panelCompras.setVisible(false);
                inicio.panelElegirProducto.setVisible(false);
                inicio.panelEditarProducto.setVisible(false);
                
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
                
                inicio.panelElegirProducto.setVisible(true);
                
                cargarListaProductos();
            }
        });
        
        //Evento para cuando se clicka en un pedido
        this.inicio.listaPedidos.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting()) {
                    //Meter posiblement una interfaz con la info del pedido
                    System.out.println("PEDIDO SELECCIONADO "+ inicio.listaPedidos.getSelectedIndex());
                }
            }
        });
        
        //Evento para cuando se clicka en un producto y nos manda al panel para editarlo
        this.inicio.listaProductos.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting()) {
                    //************************************************************
                    //************************************************************
                    //ESTO ES PORQUE DE MOMENTO CODIGO DE REFERENCIA EMPIEZA EN 1
                    //************************************************************
                    //************************************************************
                    //Meter posiblement una interfaz con la info del pedido
                    System.out.println("ARTICULO SELECCIONADO "+ (inicio.listaProductos.getSelectedIndex()+1));
                    
                    inicio.panelInicio.setVisible(false);
                    inicio.panelAnadir.setVisible(false);
                    inicio.panelEditarPerfil.setVisible(false);
                    inicio.panelCompras.setVisible(false);
                    inicio.panelElegirProducto.setVisible(false);
                    
                    inicio.panelEditarProducto.setVisible(true);
                    
                    cargarProductoEdit(inicio.listaProductos.getSelectedIndex());
                }
            }
        });
        
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
    
    public void cargarProductoEdit(int codigoReferencia){
        inicio.idProductoEdit.setText(""+(codigoReferencia+1));
        Articulo articulo = consultaArticulo.getArticulo(codigoReferencia+1);
        
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
    
    public void cargarListaProductos(){
        DefaultListModel listModel = new DefaultListModel();
        
        ArrayList<Articulo> lista = consultaArticulo.getAllArticulos();
        
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
            
            //************************************************************
            //************************************************************
            //ESTO ES PORQUE DE MOMENTO CODIGO DE REFERENCIA EMPIEZA EN 1
            //************************************************************
            //************************************************************
            listModel.add(i, articuloInfo);
        }
        
        inicio.listaProductos.setModel(listModel);
        inicio.listaProductos.setCellRenderer(new ListaDinamicaImagen(listaInfo, listaRuta, "Articulo"));
    }
    
    public void cargarListaPedidos(){
        DefaultListModel listModel = new DefaultListModel();
        ArrayList<Pedido> listaPedidos = consultaPedido.getAllPedidos();
        System.out.println(listaPedidos.size());
        for(int i=0; i<listaPedidos.size(); i++){
            Pedido pedido = listaPedidos.get(i);
            int idPedido = pedido.getIdPedido();
            String pedidoInfo = "  ID-"+ String.format("%04d", idPedido) +" \tFecha-"+ pedido.getFecha() +" \tPrecio pedido-"+ pedido.getPrecio_total() +"€ \tUsuario-"+ pedido.getEmail_cliente();
            pedidoInfo = pedidoInfo.replaceAll("\t", "         ");
            
            listModel.add(idPedido, pedidoInfo);
        }
        
        inicio.listaPedidos.setModel(listModel);
        inicio.listaPedidos.setCellRenderer(new ListaDinamica("Pedido"));
    }
    
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
    }
    
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
    
    public void iniciarPanelAnadir(){
        String listaTipos[] = {"Seleccione", "Caja", "Disco duro", "Fuente alimentacion", "Grafica", "RAM", "Monitor", 
            "Torre", "Placa base", "Portatil", "Procesador", "Raton", "Teclado", "WebCam"};
        for (String item : listaTipos) {
            inicio.tipoArticuloAnadir.addItem(item);
        }
        
        inicio.codRefAnadir.setText(""+ consultaArticulo.getCodRefMax());
    }
    
    public void iniciarAtributo1(String nombre){
        inicio.atributo1Label.setText(nombre);
        inicio.atributo1Label.setVisible(true);
        inicio.atributo1Anadir.setVisible(true);
        inicio.atributo1Anadir.setText("");
    }
    
    public void iniciarAtributo2(String nombre){
        inicio.atributo2Label.setText(nombre);
        inicio.atributo2Label.setVisible(true);
        inicio.atributo2Anadir.setVisible(true);
        inicio.atributo2Anadir.setText("");
    }
    
    public void iniciarAtributo3(String nombre){
        inicio.atributo3Label.setText(nombre);
        inicio.atributo3Label.setVisible(true);
        inicio.atributo3Anadir.setVisible(true);
        inicio.atributo3Anadir.setText("");
    }
    
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
    
    @Override
    public void actionPerformed(ActionEvent boton){
        if(boton.getSource() == inicio.btnEditarDatos){
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
                    JOptionPane.showMessageDialog(null, "ERROR: No se pudieron modificar tus datos");
                }
            }
        }
        else if(boton.getSource() == inicio.btnEditarProducto){
            Log.log.info("Vista Inicio - evento editarProducto");
            
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
        }
        else if(boton.getSource() == inicio.btnAnadirNuevoArticulo){
            
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
                        String tipo = inicio.atributo1Anadir.getText();
                        exito = consultaDiscoDuro.anadirDiscoDuro(modelo, codigoReferncia, precio, descripcion, stock, rutaImagen, idTienda, tipo);
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
                        
                        break;
                    case "Monitor":

                        break;
                    case "Torre":

                        break;
                    case "Placa base":
                        
                        break;
                    case "Portatil":
                        
                        break;
                    case "Procesador":
                        
                        break;
                    case "Raton":
                        
                        break;
                    case "Teclado":
                        
                        break;
                    case "WebCam":
                        
                        break;
                    default:
                        break;
                }
                
                if(exito){
                    JOptionPane.showMessageDialog(null, "Articulo "+ codigoReferncia +" añadido con éxito.");
                }
                else{
                    JOptionPane.showMessageDialog(null, "ERROR: No se pudieron añadir el articulo.");
                }
            }
        }
    }
}
