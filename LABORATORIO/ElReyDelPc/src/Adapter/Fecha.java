
package Adapter;

/**
 * Interfaz que define los métodos de la clase fecha.
 * 
 */
public interface Fecha {
    
    /**
     * Devuelve una cadena con la fecha en formato de España (DD/MM/AAAA).
     *
     * @return Fecha en formato de España (DD/MM/AAAA)
     */
    @Override
    public String toString();

    /**
     * Devuelve el año.
     *
     * @return Entero que representa el año.
     */
    public int getAño();

    /**
     * Devuelve el dia 
     * @return Entero que representa el dia
     */
    
    public int getDia();

    /**
     * Devuelve el Mes
     * @return Entero que representa el mess
     */

    public int getMes();
}
