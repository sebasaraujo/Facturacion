package ec.com.monkey.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.com.monkey.facade.local.ProductosFacadeLocal;
import ec.com.monkey.modelo.Productos;
import ec.com.monkey.servicios.local.ProductosServiceLocal;

@Stateless
public class ProductosService implements ProductosServiceLocal {

	@EJB
	private ProductosFacadeLocal productosFacadeLocal;
	
	@Override
    public Productos obtenerProductoXCodigoRef(String codigoRef)
    {
		return productosFacadeLocal.obtenerProductoXCodigoRef(codigoRef);
    }
	
	@Override
	public void crear(Productos producto)
	{
		productosFacadeLocal.create(producto);
	}
	
	@Override
    public List<Productos> obtenerTodos()
    {
		return productosFacadeLocal.obtenerTodos();
    }
	
	@Override 
	public void actualizar(Productos productos)
	{
		productosFacadeLocal.edit(productos);
	}
	
}
