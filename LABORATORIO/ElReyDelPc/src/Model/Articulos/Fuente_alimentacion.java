
package Model.Articulos;

/**
 * Objeto fuente de alimetación.
 * 
 */
public class Fuente_alimentacion extends Articulo{
    
    private int Potencia;
    private String Certificacion;

    public Fuente_alimentacion() {
        super();
    }

    /**
     * Obtiene la potencia de la fuente de alimentacion
     *
     * @return Devuelve un Integer que representa la potencia de la 
     * fuente de alimentacion
     */
    public int getPotencia() {
        return Potencia;
    }

    /**
     * Establece una nueva potencia de la fuente de alimentacion
     *
     * @param Potencia int
     */
    public void setPotencia(int Potencia) {
        this.Potencia = Potencia;
    }

    /**
     * Obtiene la certificacion de la fuente de alimentacion
     *
     * @return Devuelve un String que representa la certificacion de la 
     * fuente de alimentacion
     */
    public String getCertificacion() {
        return Certificacion;
    }

    /**
     * Establece una nueva certificacion de la fuente de alimentacion
     *
     * @param Certificacion String
     */
    public void setCertificacion(String Certificacion) {
        this.Certificacion = Certificacion;
    }
       
}
