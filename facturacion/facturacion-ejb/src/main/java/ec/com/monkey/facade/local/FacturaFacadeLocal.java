/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.monkey.facade.local;

import ec.com.monkey.modelo.Factura;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author luisp.araujo
 */
@Local
public interface FacturaFacadeLocal {

    void create(Factura factura);

    void edit(Factura factura);

    void remove(Factura factura);

    Factura find(Object id);

    List<Factura> findAll();

    List<Factura> findRange(int[] range);

    int count();

    /**
     * Metodo que obtiene el numero de factura
     * @return
     */
	Integer obtenerNumeroFactura();
    
}
