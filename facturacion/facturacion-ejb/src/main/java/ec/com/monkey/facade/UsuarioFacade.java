/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.monkey.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.com.monkey.enumeradores.EstadoEnum;
import ec.com.monkey.facade.local.UsuarioFacadeLocal;
import ec.com.monkey.modelo.Usuario;

/**
 *
 * @author luisp.araujo
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "facturacionPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public Usuario obtenerPorNombre(String nombre) {
        Query q = em.createQuery("Select u from Usuario u where u.nombreUs = :nombre and u.estadoUs = :estado");
        q.setParameter("nombre", nombre);
        q.setParameter("estado", EstadoEnum.ACTIVO.getValor());
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return (Usuario) q.getResultList().get(0);
        }
    }
    
    public Usuario obtenerPorIdentificacion(String identificacion, String estado) {
    	Usuario usuario = null;
    	try {
    		Query q = em.createQuery("Select u from Usuario u left join fetch u.codigoEmp e where u.identificacionUs = :identificacion and u.estadoUs = :estado");
            q.setParameter("identificacion", identificacion);
            q.setParameter("estado", estado);
            usuario = (Usuario) q.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			usuario = null;
		}
    	
    	return usuario;
    }

}
