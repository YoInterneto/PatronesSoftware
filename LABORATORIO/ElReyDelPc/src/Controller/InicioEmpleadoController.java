/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UsuarioDao;
import Model.Usuario.Empleado;
import Model.Usuario.Tienda;
import Views.InicioEmpleado;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

/**
 *
 * @author Alberto
 */
public class InicioEmpleadoController implements ActionListener{
    
    private InicioEmpleado inicio;
    private Empleado empleado;
    private Tienda tienda;
    
    private UsuarioDao consulta = new UsuarioDao();
    
    public InicioEmpleadoController(InicioEmpleado inicioVista, Empleado usuario){
        this.inicio = inicioVista;
        this.empleado = usuario;
        this.tienda = consulta.getTienda(empleado.getID_Tienda());
    }
    
    public void iniciar(){
        inicio.setTitle("INICIO - EMPLEADOS");
        inicio.setLocationRelativeTo(null);
        inicio.setResizable(false);
        inicio.setSize(1200, 750);
        
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
