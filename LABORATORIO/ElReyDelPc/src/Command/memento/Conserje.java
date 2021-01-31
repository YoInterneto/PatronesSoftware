package Command.memento;

import java.util.ArrayList;

/**
 * Es el responsable de la custodia de los recuerdos. 
 * Nunca opera o examina el contenido de un recuerdo.
 * @author Salvador Oton
 */
public class Conserje {

    // Lista de recuerdos.
    private ArrayList<Recuerdo> recuerdos = new ArrayList<>();

    /**
     * Añade un recuerdo al final de la lista de recuerdos.
     * @param recuerdo Recuerdo a añadir.
     */
    public void pushRecuerdo(Recuerdo recuerdo) {
        recuerdos.add(recuerdo);
    }

    /**
     * Recupera el último recuerdo.
     * @return Recuerdo.
     */
    public Recuerdo popRecuerdo() {
        if (recuerdos.size()>0) {
            int index = recuerdos.size()-1;
            Recuerdo r = recuerdos.get(index);
            recuerdos.remove(index);
            return r;
        } else {
            return null;
        }
    }
}
