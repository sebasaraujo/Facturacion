/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.monkey.facade;

import ec.com.monkey.facade.local.FacturaFacadeLocal;
import ec.com.monkey.modelo.Factura;

import java.math.BigDecimal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author luisp.araujo
 */
@Stateless
public class FacturaFacade extends AbstractFacade<Factura> implements FacturaFacadeLocal {

    @PersistenceContext(unitName = "facturacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FacturaFacade() {
        super(Factura.class);
    }
    
    @Override
    public Integer obtenerNumeroFactura()
    {
    	try {
    		Query q = em.createNativeQuery("select max(to_number(numero_fc,'9999999')) as numero from factura");
        	return ((BigDecimal) q.getSingleResult()).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
    	
    }
    
}
