
package Model.Articulos;

public class Procesador extends Articulo{
    
    private String Socket;

    public Procesador() {
        super();
    }

    /**
     * Obtiene el socket del procesador
     *
     * @return Devuelve un String que representa el socket del procesador
     */
    public String getSocket() {
        return Socket;
    }

    /**
     * Establece un socket del procesador
     *
     * @param Socket
     */
    public void setSocket(String Socket) {
        this.Socket = Socket;
    }
    
    
}
