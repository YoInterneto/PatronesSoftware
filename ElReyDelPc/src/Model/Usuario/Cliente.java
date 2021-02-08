package Model.Usuario;

/**
 * Objeto cliente.
 * 
 */
public class Cliente extends Usuario{
    
    private String Tarjeta;
    
    public Cliente(){
        super();
    }

    /**
     * Get the value of Tarjeta
     *
     * @return the value of Tarjeta
     */
    public String getTarjeta() {
        return Tarjeta;
    }

    /**
     * Set the value of Tarjeta
     *
     * @param Tarjeta new value of Tarjeta
     */
    public void setTarjeta(String Tarjeta) {
        this.Tarjeta = Tarjeta;
    }

    @Override
    public String toString() {
        return "Cliente{" + super.toString() + ", Tarjeta=" + Tarjeta + '}';
    }
}
