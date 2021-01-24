
package Model.Articulos;

public class Raton extends Articulo{
    
    private int Peso;
    private int DPI;
    private String tipo;

    public Raton() {
        super();
    }

    /**
     * Obtiene el peso del raton
     *
     * @return Devuelve un integer que representa el peso del raton
     */
    public int getPeso() {
        return Peso;
    }

    /**
     * Establece un nuevo Peso del raton
     *
     * @param Peso int
     */
    public void setPeso(int Peso) {
        this.Peso = Peso;
    }
    
    

    /**
     * Get the value of DPI
     *
     * @return the value of DPI
     */
    public int getDPI() {
        return DPI;
    }

    /**
     * Set the value of DPI
     *
     * @param DPI new value of DPI
     */
    public void setDPI(int DPI) {
        this.DPI = DPI;
    }


    /**
     * Get the value of tipo
     *
     * @return the value of tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Set the value of tipo
     *
     * @param tipo new value of tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    
    
}
