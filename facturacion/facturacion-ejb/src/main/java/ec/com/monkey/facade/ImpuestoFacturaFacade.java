/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.monkey.facade;

import ec.com.monkey.enumeradores.EstadoEnum;
import ec.com.monkey.facade.local.ImpuestoFacturaFacadeLocal;
import ec.com.monkey.modelo.ImpuestoFactura;

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
public class ImpuestoFacturaFacade extends AbstractFacade<ImpuestoFactura> implements ImpuestoFacturaFacadeLocal {

    @PersistenceContext(unitName = "facturacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ImpuestoFacturaFacade() {
        super(ImpuestoFactura.class);
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public List<ImpuestoFactura> obtenerImpuestosXTipoFactura(Integer codigoTipoFactura)
    {
    	Query q = em.createQuery("Select if from ImpuestoFactura if left join fetch if. codigoIp i where if.codigoTf.codigoTf = :codigoTipoFactura and if.estadoIf = :estado");
    	q.setParameter("codigoTipoFactura", codigoTipoFactura);
    	q.setParameter("estado", EstadoEnum.ACTIVO.getValor());
    	return q.getResultList();
    }
    
}
