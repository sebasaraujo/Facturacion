/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.monkey.facade.local;

import ec.com.monkey.modelo.Productos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author luisp.araujo
 */
@Local
public interface ProductosFacadeLocal {

    void create(Productos productos);

    void edit(Productos productos);

    void remove(Productos productos);

    Productos find(Object id);

    List<Productos> findAll();

    List<Productos> findRange(int[] range);

    int count();

    /**
     * Metodo que obtiene el producto segun su codigo referencial
     * @param codigoRef
     * @return
     */
	Productos obtenerProductoXCodigoRef(String codigoRef);

	/**
	 * Metodo que obtiene todos los productos
	 * @return
	 */
	List<Productos> obtenerTodos();
    
}
