/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.monkey.facade.local;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author luisp.araujo
 */
@Local
public interface LocalFacadeLocal {

    void create(ec.com.monkey.modelo.Local local);

    void edit(ec.com.monkey.modelo.Local local);

    void remove(ec.com.monkey.modelo.Local local);

    ec.com.monkey.modelo.Local find(Object id);

    List<ec.com.monkey.modelo.Local> findAll();

    List<ec.com.monkey.modelo.Local> findRange(int[] range);

    int count();

    /**
     * Metodo que obtiene el local por codigo
     * @param codigo
     * @return
     */
	ec.com.monkey.modelo.Local obtenerLocalXCodigo(Integer codigo);
    
}
