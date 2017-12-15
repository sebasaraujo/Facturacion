/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.monkey.facade.local;

import ec.com.monkey.modelo.TipoFactura;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author luisp.araujo
 */
@Local
public interface TipoFacturaFacadeLocal {

    void create(TipoFactura tipoFactura);

    void edit(TipoFactura tipoFactura);

    void remove(TipoFactura tipoFactura);

    TipoFactura find(Object id);

    List<TipoFactura> findAll();

    List<TipoFactura> findRange(int[] range);

    int count();
    
}
