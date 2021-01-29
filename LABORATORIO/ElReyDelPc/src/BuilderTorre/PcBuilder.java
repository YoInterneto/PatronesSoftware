
package BuilderTorre;

import BuilderTorre.IBuilder;
import Model.Articulos.Caja;
import Model.Articulos.Disco_duro;
import Model.Articulos.Fuente_alimentacion;
import Model.Articulos.Grafica;
import Model.Articulos.Memoria_RAM;
import Model.Articulos.PcTorre;
import Model.Articulos.Placa_base;
import Model.Articulos.Procesador;

public class PcBuilder implements IBuilder{

    private Procesador cpu;
    private Placa_base placa;
    private Memoria_RAM ram;
    private Grafica grafica;
    private Disco_duro disco;
    private Fuente_alimentacion fuente;
    private Caja caja;
    private String nombre;
    private boolean creado;
    private float precio;
    private int idTienda;
    private int codigo;
    
    public PcBuilder(boolean creado,int codigo,int idTienda){
        this.creado = creado;
        this.codigo = codigo;
        this.idTienda = idTienda;
    }
    
    public PcBuilder withCpu(Procesador cpu){
        this.cpu = cpu;
        return this;
    }
    
    public PcBuilder withPlaca(Placa_base placa){
        this.placa = placa;
        return this;
    }
    
    public PcBuilder withFuente(Fuente_alimentacion fuente){
        this.fuente = fuente;
        return this;
    }
    
    public PcBuilder withRam(Memoria_RAM ram){
        this.ram = ram;
        return this;
    }
    
    public PcBuilder withGrafica(Grafica grafica){
        this.grafica = grafica;
        return this;
    }
    
    public PcBuilder withDisco(Disco_duro disco){
        this.disco = disco;
        return this;
    }
    
    public PcBuilder withCaja(Caja caja){
        this.caja = caja;
        return this;
    }
    
    public PcBuilder withNombre(String name){
        this.nombre = name;
        return this;
    }
    
    public PcBuilder withCreado(boolean creado){
        this.creado = creado;
        return this;
    }
    
    public PcBuilder withPrecio(float precio){
        this.precio = precio;
        return this;
    }
    
    @Override
    public PcTorre build() {
        
        PcTorre pc = new PcTorre();
        pc.setNombre(this.nombre);
        pc.setID_Tienda(this.idTienda);
        pc.setCreado(this.creado);
        pc.setPrecio(this.precio);
        pc.setModelo("Custom-"+this.codigo);
        pc.setCodigo_ref(this.codigo);
        pc.setDescripcion(this.cpu.getModelo() + "\n" + this.placa.getModelo() + 
                "\n" + this.ram.getModelo() + "\n" + this.grafica.getModelo() + 
                "\n" + this.disco.getModelo() + "\n" + this.fuente.getModelo() + 
                "\n" +this.caja.getModelo());
        
        return pc;
    }
    
    
    
    
}
