package Model.Usuario;

/**
 * Objeto empleado.
 * 
 */
public class Empleado extends Usuario{
    
    private String DNI;
    private String Cargo;

    public Empleado() {
        super();
    }

    /**
     * Get the value of DNI
     *
     * @return the value of DNI
     */
    public String getDNI() {
        return DNI;
    }

    /**
     * Set the value of DNI
     *
     * @param DNI new value of DNI
     */
    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    /**
     * Get the value of Cargo
     *
     * @return the value of Cargo
     */
    public String getCargo() {
        return Cargo;
    }

    /**
     * Set the value of Cargo
     *
     * @param Cargo new value of Cargo
     */
    public void setCargo(String Cargo) {
        this.Cargo = Cargo;
    }

    @Override
    public String toString() {
        return "Empleado{"+ super.toString() + ", DNI=" + DNI + ", Cargo=" + Cargo + '}';
    }
}
