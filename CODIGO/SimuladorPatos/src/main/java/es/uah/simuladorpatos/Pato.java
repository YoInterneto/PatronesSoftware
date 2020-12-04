package es.uah.simuladorpatos;

public abstract class Pato {

    //comportamientos
    private IVuelo comportamientoVolar;
    private IGraznido comportamientoGraznar;

    public Pato(IVuelo comportamientoVolar, IGraznido comportamientoGraznar) {
        this.comportamientoVolar = comportamientoVolar;
        this.comportamientoGraznar = comportamientoGraznar;
    }

    //volamos
    public String realizarVuelo() {
        return comportamientoVolar.volar();
    }

    //graznamos
    public String realizarGraznido() {
        return comportamientoGraznar.graznar();
    }

    //establecemos un nuevo comportamiento
    public void setComportamientoVolar(IVuelo compv) {
        comportamientoVolar = compv;
    }

    public void setComportamientoGraznar(IGraznido compg) {
        comportamientoGraznar = compg;
    }

    public String nadar() {
        return "* Nado *";
    }

    public abstract String dibujar();
}
