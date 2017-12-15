package ec.com.monkey.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.com.monkey.facade.local.ImpuestoFacturaFacadeLocal;
import ec.com.monkey.modelo.ImpuestoFactura;
import ec.com.monkey.servicios.local.ImpuestoFacturaServiceLocal;

@Stateless
public class ImpuestoFacturaService implements ImpuestoFacturaServiceLocal {

	@EJB
	private ImpuestoFacturaFacadeLocal impuestoFacturaFacade;
	
	@Override
    public List<ImpuestoFactura> obtenerImpuestosXTipoFactura(Integer codigoTipoFactura)
    {
		return impuestoFacturaFacade.obtenerImpuestosXTipoFactura(codigoTipoFactura);
    }
	
}
