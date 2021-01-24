
package Controller;

import DAO.UsuarioDao;
import Model.Usuario.Empleado;
import Model.Usuario.Tienda;
import Views.InicioEmpleado;
import Views.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

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
                iniciarPanelInicio();
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
        
        //Boton para ver todos los productos
        this.inicio.btnCompras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                inicio.panelInicio.setVisible(false);
                inicio.panelAnadir.setVisible(false);
                inicio.panelProductos.setVisible(false);
                inicio.panelCompras.setVisible(true);
            }
        });
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
