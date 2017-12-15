package ec.com.monkey.servicios.local;

import javax.ejb.Local;

import ec.com.monkey.modelo.TipoFactura;

@Local
public interface TipoFacturaServiceLocal {

	/**
	 * Metodo que obtiene el tipo de factura por el codigo
	 * @param codigo
	 * @return
	 */
	TipoFactura findById(Integer codigo);

}
