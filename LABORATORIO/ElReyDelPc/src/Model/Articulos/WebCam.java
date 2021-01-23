
package Model.Articulos;

public class WebCam extends Articulo{
    
    private String Calidad;

    public WebCam() {
        super();
    }

    /**
     * Obtiene la calidad de imagen de la WebCam
     *
     * @return Devuelve un String que representa calidad de imagen de la webcam
     */
    public String getCalidad() {
        return Calidad;
    }

    /**
     * Establece una nueva calidad de imagen de la webcam
     *
     * @param Calidad String
     */
    public void setCalidad(String Calidad) {
        this.Calidad = Calidad;
    }
    
    
    
}
