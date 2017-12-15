package ec.com.monkey.servicios;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.com.monkey.facade.local.DetalleFacturaFacadeLocal;
import ec.com.monkey.modelo.DetalleFactura;
import ec.com.monkey.servicios.local.DetalleFacturaServiceLocal;

@Stateless
public class DetalleFacturaService implements DetalleFacturaServiceLocal{
	
	@EJB
	private DetalleFacturaFacadeLocal detalleFacturaFacade;
	
	@Override
	public void crear(DetalleFactura detalleFactura)
	{
		detalleFacturaFacade.create(detalleFactura);
	}

}
