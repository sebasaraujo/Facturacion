package ec.com.monkey.servicios.local;

import javax.ejb.Local;

@Local
public interface LocalServiceLocal {

	/**
	 * Metodo que obtiene el local por codigo
	 * @param codigo
	 * @return
	 */
	ec.com.monkey.modelo.Local obtenerLocalXCodigo(Integer codigo);

}
