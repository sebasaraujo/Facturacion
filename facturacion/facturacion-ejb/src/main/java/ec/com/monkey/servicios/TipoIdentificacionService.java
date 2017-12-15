package ec.com.monkey.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.com.monkey.facade.local.TipoIdentificacionFacadeLocal;
import ec.com.monkey.modelo.TipoIdentificacion;
import ec.com.monkey.servicios.local.TipoIdentificacionServiceLocal;

@Stateless
public class TipoIdentificacionService implements TipoIdentificacionServiceLocal{

	@EJB
	private TipoIdentificacionFacadeLocal tipoIdentificacionFacade;
	
	@Override
    public List<TipoIdentificacion> obtenerTodos()
    {
		return tipoIdentificacionFacade.obtenerTodos();
    }
	
	@Override
	public TipoIdentificacion findById(Integer codigo)
	{
		return tipoIdentificacionFacade.find(codigo);
	}
	
}
