/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.com.monkey.facade.local;

import ec.com.monkey.modelo.Impuestos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author luisp.araujo
 */
@Local
public interface ImpuestosFacadeLocal {

    void create(Impuestos impuestos);

    void edit(Impuestos impuestos);

    void remove(Impuestos impuestos);

    Impuestos find(Object id);

    List<Impuestos> findAll();

    List<Impuestos> findRange(int[] range);

    int count();
    
}
