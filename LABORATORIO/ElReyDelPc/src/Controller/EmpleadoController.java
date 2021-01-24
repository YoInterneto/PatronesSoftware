
package Controller;

import DAO.ArticuloDao;
import DAO.PedidoDao;
import DAO.UsuarioDao;
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
import java.util.Formatter;
import javax.swing.DefaultListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class EmpleadoController implements ActionListener{
    
    private InicioEmpleado inicio;
    private Empleado empleado;
    private Tienda tienda;
    
    private UsuarioDao consultaUsuario = new UsuarioDao();
    private PedidoDao consultaPedido = new PedidoDao();
    private ArticuloDao consultaArticulo = new ArticuloDao();
    
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
                
                inicio.panelAnadir.setVisible(true);
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
                
                inicio.panelEditarPerfil.setVisible(true);
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
        
        //Evento para cuando se clicka en un pedido
        this.inicio.listaProductos.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting()) {
                    //Meter posiblement una interfaz con la info del pedido
                    System.out.println("ARTICULO SELECCIONADO "+ inicio.listaProductos.getSelectedIndex()+1);
                }
            }
        });
    }
    
    /*public void cargarListaProductos(){
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
        
        inicio.listaPedidos.setModel(listModel);
        inicio.listaPedidos.setCellRenderer(new ListaDinamicaImagen(lista, listaRuta, "Pedido"));
    }*/
    
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
            
            listModel.add(codigoReferencia-1, articuloInfo);
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
    
    @Override
    public void actionPerformed(ActionEvent boton){
        
    }
}
