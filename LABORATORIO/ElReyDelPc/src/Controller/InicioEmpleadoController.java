/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Usuario.Empleado;
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
    
    public InicioEmpleadoController(InicioEmpleado inicioVista, Empleado usuario){
        this.inicio = inicioVista;
        this.empleado = usuario;
    }
    
    public void iniciar(){
        inicio.setTitle("INICIO - EMPLEADOS");
        inicio.setLocationRelativeTo(null);
        inicio.setResizable(false);
        
        inicio.nombreUsuario.setText(empleado.getNombre() +" "+ empleado.getApellido());
    }
    
    @Override
    public void actionPerformed(ActionEvent boton){
        
    }
}
