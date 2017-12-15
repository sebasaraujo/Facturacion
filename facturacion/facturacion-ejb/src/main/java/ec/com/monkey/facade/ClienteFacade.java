/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.monkey.facade;

import ec.com.monkey.enumeradores.EstadoEnum;
import ec.com.monkey.facade.local.ClienteFacadeLocal;
import ec.com.monkey.modelo.Cliente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author luisp.araujo
 */
@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> implements ClienteFacadeLocal {

    @PersistenceContext(unitName = "facturacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }
    
    @Override
    public Cliente buscarClienteXIdentificacion(String identificacion)
    {
    	try {
			Query q = em.createQuery("Select c from Cliente c where c.identificacionCl = :identificacion and c.estadoCl = :estado");
			q.setParameter("identificacion", identificacion);
			q.setParameter("estado", EstadoEnum.ACTIVO.getValor());
			return (Cliente) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
    }
    
}
