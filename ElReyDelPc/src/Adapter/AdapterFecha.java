
package Adapter;

import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * Cambia el formato de la fecha de US a fecha EU.
 * 
 */
public class AdapterFecha implements Fecha {
    
    private FechaUS fecha;
    
    /**
     * Constructor
     *
     * @param fecha Fecha en formato USA.
     */
    public AdapterFecha(FechaUS fecha) {
        this.fecha = fecha;
    }
    
    @Override
    public String toString() {
        StringTokenizer st = new StringTokenizer(fecha.toString(), "/");
        String mm = st.nextToken();
        String dd = st.nextToken();
        String aaaa = st.nextToken();

        return dd + "/" + mm + "/" + aaaa;
    }
    
    @Override
    public int getAño() {
        return Calendar.YEAR-1900;
    }
    
    @Override
    public int getDia() {
        return Calendar.DAY_OF_MONTH;
    }

    @Override
    public int getMes() {
        return Calendar.MONTH;
    } 
    
}
