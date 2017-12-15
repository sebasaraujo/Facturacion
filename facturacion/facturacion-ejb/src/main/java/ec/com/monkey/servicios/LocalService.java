package ec.com.monkey.servicios;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.com.monkey.facade.local.LocalFacadeLocal;
import ec.com.monkey.modelo.Local;
import ec.com.monkey.servicios.local.LocalServiceLocal;

@Stateless
public class LocalService implements LocalServiceLocal{

	@EJB
	private LocalFacadeLocal localFacade;
	
	@Override
    public Local obtenerLocalXCodigo(Integer codigo)
    {
		return localFacade.obtenerLocalXCodigo(codigo);
    }
	
}
