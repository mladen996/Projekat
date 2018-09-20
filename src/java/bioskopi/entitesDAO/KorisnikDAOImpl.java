/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bioskopi.entitesDAO;

import bioskopi.entities.Korisnik;
import bioskopi.hibernate.util.HibernateUtil;
import javax.enterprise.context.Dependent;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
/**
 *
 * @author jaaaa
 */
@Named
@Dependent

public class KorisnikDAOImpl extends GenericDAOImpl<Korisnik>
        implements KorisnikDAO {

    private Session session;
    public void execute() {
        Korisnik k = new Korisnik();
        HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String email = request.getParameter("email:txtProperty");
        //note the difference when getting the parameter
        k.setEmail(email);
        String txtAnotherProperty= request.getParameter("txtAnotherProperty");
        //use the value in txtProperty as you want...
        //Note: don't use System.out.println i
    }
    @Override
    public Korisnik proveraKorisnika(String u, String p) {
        session = HibernateUtil.getSessionFactory().openSession();

        try {
            Korisnik k = (Korisnik) session.createCriteria(Korisnik.class)
                    .add(Restrictions.and(
                            Restrictions.or(
                                    Restrictions.eq("username", u),
                                    Restrictions.eq("email", u)
                            ),
                            Restrictions.eq("password", p)
                    )).uniqueResult();
            if (k != null) {
                return k;
            }
        } catch (HibernateException ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
    
    
}
