/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.monkey.facade;

import ec.com.monkey.enumeradores.EstadoEnum;
import ec.com.monkey.facade.local.ProductosFacadeLocal;
import ec.com.monkey.modelo.Productos;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author luisp.araujo
 */
@Stateless
public class ProductosFacade extends AbstractFacade<Productos> implements ProductosFacadeLocal {

    @PersistenceContext(unitName = "facturacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductosFacade() {
        super(Productos.class);
    }
    
    @Override
    public Productos obtenerProductoXCodigoRef(String codigoRef)
    {
    	try {
			Query q = em.createQuery("Select p from Productos p left join fetch p.codigoTp tp where p.codigoRefPr = :codigoRef and p.estadoPr = :estado");
			q.setParameter("codigoRef", codigoRef);
			q.setParameter("estado", EstadoEnum.ACTIVO.getValor());
			return (Productos) q.getSingleResult();
    	} catch (Exception e) {
			return null;
		}
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public List<Productos> obtenerTodos()
    {
    	Query q = em.createQuery("Select p from Productos p left join fetch p.codigoTp tp where p.estadoPr = :estado");
    	q.setParameter("estado", EstadoEnum.ACTIVO.getValor());
    	return q.getResultList();
    }
    
}
