/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Controller.LoginController;
import Views.Login;

/**
 *
 * @author Mario
 */
public class ElReyDelPc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Login loginVista = new Login();
        
        LoginController login = new LoginController(loginVista);
        
        login.iniciar();
        loginVista.setVisible(true);
    }
    
}
