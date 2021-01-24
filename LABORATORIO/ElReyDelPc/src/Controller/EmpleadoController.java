
package Controller;

import DAO.UsuarioDao;
import Model.Usuario.Empleado;
import Model.Usuario.Tienda;
import Util.ListaDinamicaImagen;
import Views.InicioEmpleado;
import Views.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class EmpleadoController implements ActionListener{
    
    private InicioEmpleado inicio;
    private Empleado empleado;
    private Tienda tienda;
    
    private UsuarioDao consulta = new UsuarioDao();
    
    public EmpleadoController(InicioEmpleado inicioVista, Empleado usuario){
        this.inicio = inicioVista;
        this.empleado = usuario;
        this.tienda = consulta.getTienda(empleado.getID_Tienda());
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
                inicio.panelProductos.setVisible(false);
                inicio.panelCompras.setVisible(false);
                
                inicio.panelInicio.setVisible(true);
            }
        });
        
        //Boton para añadir un nuevo producto
        this.inicio.btnAnnadir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                inicio.panelInicio.setVisible(false);
                inicio.panelProductos.setVisible(false);
                inicio.panelCompras.setVisible(false);
                
                inicio.panelAnadir.setVisible(true);
            }
        });
        
        //Boton para ver todos los productos
        this.inicio.btnProducto.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                inicio.panelInicio.setVisible(false);
                inicio.panelAnadir.setVisible(false);
                inicio.panelCompras.setVisible(false);
                
                inicio.panelProductos.setVisible(true);
            }
        });
        
        //Boton para ver todos las compras
        this.inicio.btnCompras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                inicio.panelInicio.setVisible(false);
                inicio.panelAnadir.setVisible(false);
                inicio.panelProductos.setVisible(false);
                
                inicio.panelCompras.setVisible(true);
                
                cargarListaPedidos();
            }
        });
        
        //Evento para cuando se clicka en un pedido
        this.inicio.listaPedidos.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(e.getValueIsAdjusting()) {
                    System.out.println("DATO SELECCIONADO "+ inicio.listaPedidos.getSelectedIndex());
                }
            }
        });
    }
    
    public void cargarListaPedidos(){
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
    }
    
    @Override
    public void actionPerformed(ActionEvent boton){
        
    }
}
