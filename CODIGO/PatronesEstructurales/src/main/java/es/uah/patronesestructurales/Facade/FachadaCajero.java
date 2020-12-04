package es.uah.patronesestructurales.Facade;

/**
 * Facade o fachada: simplifica el acceso a cada uno de los subsistemas de un
 * sistema bancario a través del cajero.
 */
public class FachadaCajero {

    /**
     * Subsistema de validación de usuario.
     */
    private ValidacionUsuario val = new ValidacionUsuario();
    /**
     * Subsistema de retirada de dinero.
     */
    private RetirarDinero ret = new RetirarDinero();
    /**
     * Subsistema de pago de recibos.
     */
    private PagoRecibo pr = new PagoRecibo();    
    /**
     * Subsistema de regarga de móvil.
     */
    private RecargaMovil rec = new RecargaMovil();
        
    /**
     * Saca dinero.
     *
     * @return Descripción de los pasos de la operación.
     */
    public String sacarDinero() {
        return val.valida() + ret.retirar();
    }

    /**
     * Pagar recibo.
     *
     * @return Descripción de los pasos de la operación.
     */
    public String pagarRecibo() {
        return val.valida() + pr.pagar();
    }
    
    /**
     * Recarga el móvil.
     *
     * @return Descripción de los pasos de la operación.
     */
    public String recargaMovil() {
        return val.valida() + rec.recarga();
    }
}
