package Observer;

import Model.Articulos.Articulo;
import java.util.Observable;

/**
 * Gestiona a sus observadores. 
 * Notifica cambios en el precio de un componente.
 *
 */
public class SujetoConcreto extends Observable {

    // Componente monitorizado
    private Articulo c;

    /**
     * Establece componente a monitorizar.
     *
     * @param c Componente a monitorizar.
     */
    public void setComponente(Articulo c) {
        this.c = c;
    }

    /**
     * Modifica precio del componente.
     *
     * @param p Nuevo precio del componente.
     */
    public void cambiaPrecio(float p) {
        c.setPrecio(p);
        setChanged();
        notifyObservers();
    }

    /**
     * Devuelve componente monitorizado.
     *
     * @return Componente monitorizado.
     */
    public Articulo getComponente() {
        return this.c;
    }
}
