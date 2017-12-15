package ec.com.monkey.servicios.local;

import javax.ejb.Local;

import ec.com.monkey.modelo.Empleado;

@Local
public interface EmpleadoServiceLocal {

	/**
	 * Metodo que obtiene empleado por codigo
	 * @param id
	 * @return
	 */
	Empleado findById(Integer id);

}
