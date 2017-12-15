package ec.com.monkey.servicios;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.com.monkey.facade.local.ClienteFacadeLocal;
import ec.com.monkey.modelo.Cliente;
import ec.com.monkey.servicios.local.ClienteServiceLocal;

@Stateless
public class ClienteService implements ClienteServiceLocal{

	@EJB
	private ClienteFacadeLocal clienteFacade;
	
	@Override
    public Cliente buscarClienteXIdentificacion(String identificacion)
    {
		return clienteFacade.buscarClienteXIdentificacion(identificacion);
    }
	
	@Override
	public void crear(Cliente cliente)
	{
		clienteFacade.create(cliente);
	}
	
}
