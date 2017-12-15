/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.monkey.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.com.monkey.facade.local.UsuarioClaveFacadeLocal;
import ec.com.monkey.modelo.UsuarioClave;

/**
 *
 * @author luisp.araujo
 */
@Stateless
public class UsuarioClaveFacade extends AbstractFacade<UsuarioClave> implements UsuarioClaveFacadeLocal {

    @PersistenceContext(unitName = "facturacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioClaveFacade() {
        super(UsuarioClave.class);
    }
    
}
