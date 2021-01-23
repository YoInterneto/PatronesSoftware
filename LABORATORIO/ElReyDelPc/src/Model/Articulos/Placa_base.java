
package Model.Articulos;

public class Placa_base extends Articulo{
    
    private String Socket;

    public Placa_base() {
        super();
    }

    /**
     * Obtiene el socket de la placa base
     *
     * @return Devuelve un String que representa el socket de la placa base
     */
    public String getSocket() {
        return Socket;
    }

    /**
     * Establece un socket de la placa base
     *
     * @param Socket
     */
    public void setSocket(String Socket) {
        this.Socket = Socket;
    }
    
}
