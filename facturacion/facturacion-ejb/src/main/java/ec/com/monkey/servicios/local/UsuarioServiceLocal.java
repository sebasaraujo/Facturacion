/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.monkey.servicios.local;

import javax.ejb.Local;

import ec.com.monkey.modelo.Usuario;

/**
 *
 * @author luisp.araujo
 */
@Local
public interface UsuarioServiceLocal {

    /**
     * Metodo que obtiene un usuario activo por nombre
     * @param nombre
     * @return 
     */
    public Usuario obtenerPorNombre(String nombre);
    
    /**
     * Método que obtiene un usuario por identificacion
     * @param identificacion
     * @param estado
     * @return objeto Usuario
     */
    public Usuario obtenerPorIdentificacion(String identificacion, String estado);
    
}
