/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Adapter;

import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

/**
 *
 * @author Mario
 */
public class AdapterFecha implements Fecha {
    
    private Date fecha;
    
    /**
     * Constructor
     *
     * @param fecha Fecha en formato USA.
     */
    public AdapterFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    @Override
    public String toString() {
        StringTokenizer st = new StringTokenizer(fecha.toString(), "-");
        String aaaa = st.nextToken();
        String mm = st.nextToken();
        String dd = st.nextToken();

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
