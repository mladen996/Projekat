/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bioskopi.entitesDAO;

import java.util.List;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 *
 * @author profesor
 */
@Named
@Dependent
public interface GenericDAO<T>
{
    
    T save(T obj);
    void persist(T obj);
    void update(T obj);
    T merge(T obj);
    void delete(T obj);
    void saveOrUpdate(T obj);
    List<T> readAll(Class<T> c);
    T readById(Class<T> c, String nameColumn, Long valueColumn);

}
