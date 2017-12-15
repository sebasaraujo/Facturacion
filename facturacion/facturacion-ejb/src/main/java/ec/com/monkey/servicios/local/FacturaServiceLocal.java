package ec.com.monkey.servicios.local;

import javax.ejb.Local;

import ec.com.monkey.modelo.Factura;

@Local
public interface FacturaServiceLocal {

	/**
	 * Metodo que persiste un registro
	 * @param factura
	 */
	void crear(Factura factura);

	/**
	 * Metodo que obtiene el numero de factura actual
	 * @return
	 */
	Integer obtenerNumeroFactura();

}
