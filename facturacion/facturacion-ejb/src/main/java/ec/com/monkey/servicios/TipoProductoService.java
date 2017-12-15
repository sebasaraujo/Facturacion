package ec.com.monkey.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.com.monkey.facade.local.TipoProductoFacadeLocal;
import ec.com.monkey.modelo.TipoProducto;
import ec.com.monkey.servicios.local.TipoProductoServiceLocal;

@Stateless
public class TipoProductoService implements TipoProductoServiceLocal{
	
	@EJB
	private TipoProductoFacadeLocal tipoProductoFacade;
	
	@Override
    public List<TipoProducto> obtenerTodos()
    {
		return tipoProductoFacade.obtenerTodos();
    }
	
	@Override
	public TipoProducto findById(Integer id)
	{
		return tipoProductoFacade.find(id);
	}

}
