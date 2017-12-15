/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.monkey.facade;

import ec.com.monkey.enumeradores.EstadoEnum;
import ec.com.monkey.facade.local.TipoProductoFacadeLocal;
import ec.com.monkey.modelo.TipoProducto;

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
public class TipoProductoFacade extends AbstractFacade<TipoProducto> implements TipoProductoFacadeLocal {

    @PersistenceContext(unitName = "facturacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoProductoFacade() {
        super(TipoProducto.class);
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public List<TipoProducto> obtenerTodos()
    {
    	Query q = em.createQuery("Select tp from TipoProducto tp where tp.estadoTp = :estado");
    	q.setParameter("estado", EstadoEnum.ACTIVO.getValor());
    	return q.getResultList();
    }
    
}
