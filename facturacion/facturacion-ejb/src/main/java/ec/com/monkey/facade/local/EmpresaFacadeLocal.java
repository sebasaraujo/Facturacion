/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.monkey.facade.local;

import ec.com.monkey.modelo.Empresa;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author luisp.araujo
 */
@Local
public interface EmpresaFacadeLocal {

    void create(Empresa empresa);

    void edit(Empresa empresa);

    void remove(Empresa empresa);

    Empresa find(Object id);

    List<Empresa> findAll();

    List<Empresa> findRange(int[] range);

    int count();
    
}
