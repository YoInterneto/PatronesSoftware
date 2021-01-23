
package Util.Articulos;

public class Fuente_alimentacion extends Articulo{
    
    private int Potencia;
    private int Certificacion;

    public Fuente_alimentacion() {
        super();
    }

    public int getPotencia() {
        return Potencia;
    }

    public void setPotencia(int Potencia) {
        this.Potencia = Potencia;
    }

    public int getCertificacion() {
        return Certificacion;
    }

    public void setCertificacion(int Certificacion) {
        this.Certificacion = Certificacion;
    }
       
}
