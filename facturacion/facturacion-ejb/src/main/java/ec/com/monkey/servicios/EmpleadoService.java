package ec.com.monkey.servicios;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.com.monkey.facade.local.EmpleadoFacadeLocal;
import ec.com.monkey.modelo.Empleado;
import ec.com.monkey.servicios.local.EmpleadoServiceLocal;

@Stateless
public class EmpleadoService implements EmpleadoServiceLocal{
	
	@EJB
	private EmpleadoFacadeLocal empleadoFacadeLocal;
	
	@Override
	public Empleado findById(Integer id)
	{
		return empleadoFacadeLocal.find(id);
	}

}
