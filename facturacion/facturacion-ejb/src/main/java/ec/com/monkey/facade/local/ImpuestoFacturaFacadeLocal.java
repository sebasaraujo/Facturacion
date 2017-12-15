/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.monkey.facade.local;

import ec.com.monkey.modelo.ImpuestoFactura;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author luisp.araujo
 */
@Local
public interface ImpuestoFacturaFacadeLocal {

    void create(ImpuestoFactura impuestoFactura);

    void edit(ImpuestoFactura impuestoFactura);

    void remove(ImpuestoFactura impuestoFactura);

    ImpuestoFactura find(Object id);

    List<ImpuestoFactura> findAll();

    List<ImpuestoFactura> findRange(int[] range);

    int count();

    /**
     * Metodo que obtiene los impuestos de un tipo de factura
     * @param codigoTipoFactura
     * @return
     */
	List<ImpuestoFactura> obtenerImpuestosXTipoFactura(Integer codigoTipoFactura);
    
}
