/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.monkey.facade.local;

import java.util.List;

import javax.ejb.Local;

import ec.com.monkey.modelo.UsuarioClave;

/**
 *
 * @author luisp.araujo
 */
@Local
public interface UsuarioClaveFacadeLocal {

    void create(UsuarioClave usuarioClave);

    void edit(UsuarioClave usuarioClave);

    void remove(UsuarioClave usuarioClave);

    UsuarioClave find(Object id);

    List<UsuarioClave> findAll();

    List<UsuarioClave> findRange(int[] range);

    int count();
    
}
