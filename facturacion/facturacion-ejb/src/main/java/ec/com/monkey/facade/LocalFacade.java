/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.monkey.facade;

import ec.com.monkey.enumeradores.EstadoEnum;
import ec.com.monkey.facade.local.LocalFacadeLocal;
import ec.com.monkey.modelo.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author luisp.araujo
 */
@Stateless
public class LocalFacade extends AbstractFacade<Local> implements LocalFacadeLocal {

    @PersistenceContext(unitName = "facturacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LocalFacade() {
        super(Local.class);
    }
    
    @Override
    public Local obtenerLocalXCodigo(Integer codigo)
    {
    	try {
    		Query q = em.createQuery("Select l from Local l left join fetch l.codigoCi c left join fetch l.codigoEm e where l.codigoLo = :codigo and l.estadoLo = :estado");
        	q.setParameter("codigo", codigo);
        	q.setParameter("estado", EstadoEnum.ACTIVO.getValor());
        	return (Local) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
    	
    	
    }
    
}
