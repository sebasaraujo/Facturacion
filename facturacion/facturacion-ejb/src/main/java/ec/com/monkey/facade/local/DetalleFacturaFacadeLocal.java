/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.monkey.facade.local;

import ec.com.monkey.modelo.DetalleFactura;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author luisp.araujo
 */
@Local
public interface DetalleFacturaFacadeLocal {

    void create(DetalleFactura detalleFactura);

    void edit(DetalleFactura detalleFactura);

    void remove(DetalleFactura detalleFactura);

    DetalleFactura find(Object id);

    List<DetalleFactura> findAll();

    List<DetalleFactura> findRange(int[] range);

    int count();
    
}
