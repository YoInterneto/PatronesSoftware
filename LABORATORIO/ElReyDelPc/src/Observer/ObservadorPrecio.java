/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Mantiene una referencia a un objeto SujetoProcesador
 Implementa la interfaz Observer y define los métodos
 para responder a los mensajes recibidos del sujeto.
 * 
 */
public class ObservadorPrecio implements Observer {
    
    private String nombre;			// Nombre del observador.
    private float precioCompra;                // Precio al que queremos comprar un componente.
    private SujetoConcreto sujeto;		// Sujeto al que observamos.

    /**
     * Constructor.
     * @param nombre Nombre del observador.
     * @param precio Precio al que queremos comprar un componente.
     * @param sujeto Sujeto al que observamos.
     */
    public ObservadorPrecio(String nombre, float precio, SujetoConcreto sujeto) {
        this.nombre = nombre;
        this.precioCompra = precio;
        sujeto.addObserver(this);
    }

    public void update(Observable obs, Object arg) {
        sujeto = (SujetoConcreto) obs;
        float p = sujeto.getComponente().getPrecio();
        String res = "Actualizando con precio a: " + p + ", y precio de compra <= a: " + precioCompra;

        if (p <= precioCompra) {
            res += "\nEl observador " + nombre +
                    " ha comprado el componente: " + sujeto.getComponente().getModelo();
            sujeto.deleteObserver(this);
        } else {
            res += "\nEl observador " + nombre + " no compra el componente.";
        }
        System.out.println(res);
    }
    
}
