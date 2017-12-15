package ec.com.monkey.servicios.local;

import java.util.List;

import javax.ejb.Local;

import ec.com.monkey.modelo.ImpuestoFactura;

@Local
public interface ImpuestoFacturaServiceLocal {

	/**
	 * Metodo que obtiene los impuestos de un tipo de factura
	 * @param codigoTipoFactura
	 * @return
	 */
	List<ImpuestoFactura> obtenerImpuestosXTipoFactura(Integer codigoTipoFactura);

}
