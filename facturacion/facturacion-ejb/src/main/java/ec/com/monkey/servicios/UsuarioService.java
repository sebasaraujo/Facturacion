/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.monkey.servicios;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ec.com.monkey.facade.local.UsuarioFacadeLocal;
import ec.com.monkey.modelo.Usuario;
import ec.com.monkey.servicios.local.UsuarioServiceLocal;

/**
 *
 * @author luisp.araujo
 */
@Stateless
public class UsuarioService implements UsuarioServiceLocal {

    @EJB
    private UsuarioFacadeLocal usuarioFacade;

    @Override
    public Usuario obtenerPorNombre(String nombre) {
        return usuarioFacade.obtenerPorNombre(nombre);
    }
    
    public Usuario obtenerPorIdentificacion(String identificacion, String estado) {
    	return usuarioFacade.obtenerPorIdentificacion(identificacion, estado);
    }
}
