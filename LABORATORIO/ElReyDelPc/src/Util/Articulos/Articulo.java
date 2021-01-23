
package Util.Articulos;

public class Articulo {
    
    private String Modelo;
    private int Codigo_ref;
    private int Precio;
    private String Descripcion;
    private int Stock;
    private int ID_Tienda;

    public Articulo() {
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String Modelo) {
        this.Modelo = Modelo;
    }

    public int getCodigo_ref() {
        return Codigo_ref;
    }

    public void setCodigo_ref(int Codigo_ref) {
        this.Codigo_ref = Codigo_ref;
    }

    public int getPrecio() {
        return Precio;
    }

    public void setPrecio(int Precio) {
        this.Precio = Precio;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }

    public int getID_Tienda() {
        return ID_Tienda;
    }

    public void setID_Tienda(int ID_Tienda) {
        this.ID_Tienda = ID_Tienda;
    }
    
    
    
}
