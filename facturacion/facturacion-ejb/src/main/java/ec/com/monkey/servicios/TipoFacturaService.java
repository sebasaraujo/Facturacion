package ec.com.monkey.servicios;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.com.monkey.facade.local.TipoFacturaFacadeLocal;
import ec.com.monkey.modelo.TipoFactura;
import ec.com.monkey.servicios.local.TipoFacturaServiceLocal;

@Stateless
public class TipoFacturaService implements TipoFacturaServiceLocal{

	@EJB
	private TipoFacturaFacadeLocal tipoFacturaFacade;
	
	@Override
	public TipoFactura findById(Integer codigo)
	{
		return tipoFacturaFacade.find(codigo);
	}
}
