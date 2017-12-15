package ec.com.monkey.servicios.local;

import javax.ejb.Local;

import ec.com.monkey.modelo.Cliente;

@Local
public interface ClienteServiceLocal {

	/**
	 * Metodo que obtiene cliente por identificacion
	 * @param identificacion
	 * @return
	 */
	Cliente buscarClienteXIdentificacion(String identificacion);

	/**
	 * Metodo que crea un cliente
	 * @param cliente
	 */
	void crear(Cliente cliente);

}
