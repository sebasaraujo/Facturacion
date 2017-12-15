/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.monkey.facade.local;

import ec.com.monkey.modelo.Cliente;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author luisp.araujo
 */
@Local
public interface ClienteFacadeLocal {

    void create(Cliente cliente);

    void edit(Cliente cliente);

    void remove(Cliente cliente);

    Cliente find(Object id);

    List<Cliente> findAll();

    List<Cliente> findRange(int[] range);

    int count();

    /**
     * Metodo que obtiene cliente por identificacion
     * @param identificacion
     * @return
     */
	Cliente buscarClienteXIdentificacion(String identificacion);
    
}
