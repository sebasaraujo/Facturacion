package ec.com.monkey.servicios.local;

import java.util.List;

import javax.ejb.Local;

import ec.com.monkey.modelo.Productos;

@Local
public interface ProductosServiceLocal {

	/**
	 * Metodo que obtiene el prodcuto segun su codigo referencial
	 * @param codigoRef
	 * @return
	 */
	Productos obtenerProductoXCodigoRef(String codigoRef);

	/**
	 * Metodo que crea un producto
	 * @param producto
	 */
	void crear(Productos producto);

	/**
	 * Metodo que obtiene todos los productos
	 * @return
	 */
	List<Productos> obtenerTodos();

	/**
	 * Metodo que actualiza un producto
	 * @param productos
	 */
	void actualizar(Productos productos);

}
