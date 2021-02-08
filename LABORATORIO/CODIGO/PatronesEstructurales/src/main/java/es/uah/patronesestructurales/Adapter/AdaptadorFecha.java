package es.uah.patronesestructurales.Adapter;

//
import java.util.StringTokenizer;

/**
 * Clase para adaptar fechas en formato de USA (MM,DD,AAAA) a fechas en formato
 * de España (DD/MM/AAAA)
 */
public class AdaptadorFecha implements Fecha {

    /**
     * Fecha en formato USA que se va a adaptar.
     */
    private FechaUS fus;

    /**
     * Constructor
     *
     * @param fus Fecha en formato USA.
     */
    public AdaptadorFecha(FechaUS fus) {
        this.fus = fus;
    }

    @Override
    public String toString() {
        StringTokenizer st = new StringTokenizer(fus.toString(), ",");
        String mm = st.nextToken();
        String dd = st.nextToken();
        String aaaa = st.nextToken();

        return dd + "/" + mm + "/" + aaaa;
    }

    @Override
    public int getAño() {
        return this.fus.getYear();
    }

    @Override
    public void setAño(int año) {
        this.fus.setYear(año);
    }

    @Override
    public int getDia() {
        return this.fus.getDay();
    }

    @Override
    public void setDia(int dia) {
        this.fus.setDay(dia);
    }

    @Override
    public int getMes() {
        return this.fus.getMonth();
    }

    @Override
    public void setMes(int mes) {
        this.fus.setMonth(mes);
    }
}
