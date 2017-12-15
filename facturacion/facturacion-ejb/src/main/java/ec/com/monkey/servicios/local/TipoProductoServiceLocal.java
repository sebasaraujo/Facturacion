package ec.com.monkey.servicios.local;

import java.util.List;

import javax.ejb.Local;

import ec.com.monkey.modelo.TipoProducto;

@Local
public interface TipoProductoServiceLocal {

	/**
	 * Metodo que obtiene todos los tipos de productos
	 * @return
	 */
	List<TipoProducto> obtenerTodos();

	/**
	 * Metodo que obtiene el tipo de producto segun su codigo
	 * @param id
	 * @return
	 */
	TipoProducto findById(Integer id);

}
