package Util.Usuario;

public class Cliente extends Usuario{
    
    private String Tarjeta;
    
    public Cliente(){
        super();
    }

    public String getTarjeta() {
        return Tarjeta;
    }

    public void setTarjeta(String Tarjeta) {
        this.Tarjeta = Tarjeta;
    }
       
}
