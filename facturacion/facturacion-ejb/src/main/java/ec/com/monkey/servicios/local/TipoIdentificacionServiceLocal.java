package ec.com.monkey.servicios.local;

import java.util.List;

import javax.ejb.Local;

import ec.com.monkey.modelo.TipoIdentificacion;

@Local
public interface TipoIdentificacionServiceLocal {

	/**
	 * Metodo que obtiene todos los tipos de identificaciones
	 * @return
	 */
	List<TipoIdentificacion> obtenerTodos();

	/**
	 * Metodo que obtiene el tipo de identificacion por codigo
	 * @param codigo
	 * @return
	 */
	TipoIdentificacion findById(Integer codigo);

}
