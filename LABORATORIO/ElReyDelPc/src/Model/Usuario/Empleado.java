package Model.Usuario;

public class Empleado extends Usuario{
    
    private String DNI;
    private String Cargo;

    public Empleado() {
        super();
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getCargo() {
        return Cargo;
    }

    public void setCargo(String Cargo) {
        this.Cargo = Cargo;
    }
       
}
