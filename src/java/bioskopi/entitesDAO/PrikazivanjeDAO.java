/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bioskopi.entitesDAO;

import bioskopi.entities.Prikazivanje;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 *
 * @author jaaaa
 */
@Named
@Dependent
public interface PrikazivanjeDAO extends GenericDAO<Prikazivanje> {
    
}
