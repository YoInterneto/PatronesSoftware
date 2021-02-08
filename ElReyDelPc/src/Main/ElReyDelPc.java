
package Main;

import Facade.FachadaLogin;
import Views.Login;

/**
 * Main para empezar la ejecución del programa.
 * 
 */
public class ElReyDelPc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Login loginVista = new Login();
        
        FachadaLogin login = new FachadaLogin(loginVista);
        
        login.iniciar();
        loginVista.setVisible(true);
    }
    
}
