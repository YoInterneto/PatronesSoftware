
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
    }
    
    public void cargarProductoEdit(int codigoReferencia){
        inicio.idProductoEdit.setText("PRODUCTO - COD.REF "+ (codigoReferencia+1));
        Articulo articulo = consultaArticulo.getArticulo(codigoReferencia+1);
        
        inicio.modeloEdit.setText(articulo.getModelo());
        inicio.descripcionEdit.setText(articulo.getDescripcion());
        inicio.precioEdit.setText(""+articulo.getPrecio());
        inicio.stockEdit.setText(""+articulo.getStock());
        
        if(articulo.getRutaImagen() == null){
            inicio.imgProductoEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fotopc.png")));
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
    
    public boolean comprobarFormularioEditarEmpleado(){
        boolean correcto = false;
        
        if(inicio.nombreEdit.getText().equalsIgnoreCase("") || inicio.apellidoEdit.getText().equalsIgnoreCase("") 
                || inicio.direccionEdit.getText().equalsIgnoreCase("") || inicio.telefonoEdit.getText().equalsIgnoreCase("")){
            JOptionPane.showMessageDialog(null, "ERROR: Rellene los campos Nombre, Apellido, Direccion, Telefono");
        }
        else{
            if(inicio.passActualEdit.getPassword().length != 0){
                if((inicio.passNuevaEdit.getPassword().length != 0 && inicio.passRepitaEdit.getPassword().length != 0)){
                    String passNueva= Arrays.toString(inicio.passNuevaEdit.getPassword());
                    String passRepite= Arrays.toString(inicio.passRepitaEdit.getPassword());
                    
                    if(passNueva.equals(passRepite)){
                        String passAnterior= Arrays.toString(inicio.passActualEdit.getPassword());
                        if(passNueva.equals(passAnterior)){
                            JOptionPane.showMessageDialog(null, "ERROR: La nueva contraseña es la misma que la anterior");
                        }
                        else{
                            correcto = true;
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "ERROR: Las contraseñas no coinciden");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "ERROR: Introduzca la nueva contraseña");
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
    
    @Override
    public void actionPerformed(ActionEvent boton){
        if(boton.getSource() == inicio.btnEditarDatos){
            Log.log.info("Vista Inicio - evento editarDatos");
            
            if(comprobarFormularioEditarEmpleado()){
                String correo = empleado.getEmail();
                String nombre = inicio.nombreEdit.getText();
                String apellido = inicio.apellidoEdit.getText();
                String direccion = inicio.direccionEdit.getText();
                int telefono = Integer.parseInt(inicio.telefonoEdit.getText());
                
                boolean exito = false;
                
                //Si quiere cambiar la contraseña cogemos el valor
                if(inicio.passNuevaEdit.getPassword().length != 0){
                    char[] valorContrasenna = inicio.passNuevaEdit.getPassword();
                    String contrasenna = new String(valorContrasenna);
                    exito = consultaUsuario.editarUsuarioPass(correo, nombre, apellido, direccion, telefono, contrasenna);
                }
                else{
                    exito = consultaUsuario.editarUsuario(correo, nombre, apellido, direccion, telefono);
                }
                
                if(exito){
                    JOptionPane.showMessageDialog(null, "Tus datos se han modificado con éxito.");
                }
                else{
                    JOptionPane.showMessageDialog(null, "ERROR: No se pudieron modificar tus datos");
                }
            }
        }
        else if(boton.getSource() == inicio.btnEditarProducto){
            Log.log.info("Vista Inicio - evento editarProducto");
            
            if(comprobarFormularioEditarProducto()){
                //TODO - AQUI TENEMOS QUE HACER Y PONER UNA CONSULTA PARA EDITAR EL ARTICULO
            }
        }
    }
}
