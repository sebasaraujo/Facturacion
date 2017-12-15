package ec.com.monkey.servicios;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.com.monkey.facade.local.FacturaFacadeLocal;
import ec.com.monkey.modelo.Factura;
import ec.com.monkey.servicios.local.FacturaServiceLocal;

@Stateless
public class FacturaService implements FacturaServiceLocal{

	@EJB
	private FacturaFacadeLocal facturaFacade;
	
	@Override
	public void crear(Factura factura)
	{
		facturaFacade.create(factura);
	}
	
	@Override
    public Integer obtenerNumeroFactura()
    {
		return facturaFacade.obtenerNumeroFactura();
    }
	
}
