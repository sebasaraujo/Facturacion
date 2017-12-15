package ec.com.monkey.servicios.local;

import javax.ejb.Local;

import ec.com.monkey.modelo.DetalleFactura;

@Local
public interface DetalleFacturaServiceLocal {

	/**
	 * Metodo que crea un detalle factura
	 * @param detalleFactura
	 */
	void crear(DetalleFactura detalleFactura);

}
