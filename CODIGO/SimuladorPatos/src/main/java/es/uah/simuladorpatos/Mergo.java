package es.uah.simuladorpatos;

public class Mergo extends Pato {

    public Mergo() {
        super(new VolarAlto(), new GraznarAlto());
    }

    public Mergo(IVuelo comportamientoVolar, IGraznido comportamientoGraznar) {
        super(comportamientoVolar, comportamientoGraznar);
    }

    @Override
    public String dibujar() {
        return "- Soy un pato Mergo -";
    }
}
