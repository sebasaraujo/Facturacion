/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.monkey.facade;

import ec.com.monkey.enumeradores.EstadoEnum;
import ec.com.monkey.facade.local.TipoIdentificacionFacadeLocal;
import ec.com.monkey.modelo.TipoIdentificacion;

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
public class TipoIdentificacionFacade extends AbstractFacade<TipoIdentificacion> implements TipoIdentificacionFacadeLocal {

    @PersistenceContext(unitName = "facturacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoIdentificacionFacade() {
        super(TipoIdentificacion.class);
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public List<TipoIdentificacion> obtenerTodos()
    {
    	Query q = em.createQuery("Select ti from TipoIdentificacion ti where ti.estadoTi = :estado");
    	q.setParameter("estado", EstadoEnum.ACTIVO.getValor());
    	return q.getResultList();
    }
    
}
