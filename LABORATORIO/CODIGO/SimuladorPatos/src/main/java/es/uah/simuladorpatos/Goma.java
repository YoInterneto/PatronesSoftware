package es.uah.simuladorpatos;

public class Goma extends Pato {

    public Goma() {
        super(new NoVolar(), new NoGraznar());
    }

    public Goma(IVuelo comportamientoVolar, IGraznido comportamientoGraznar) {
        super(comportamientoVolar, comportamientoGraznar);
    }

    @Override
    public String dibujar() {
        return "- Soy un pato de Goma -";
    }
}
