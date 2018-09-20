/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import bioskopi.entitesDAO.KorisnikDAO;
import bioskopi.entitesDAO.KorisnikDAOImpl;
import bioskopi.entities.Korisnik;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import javax.faces.context.FacesContext;

/**
 *
 * @author profesor
 */
@Named
@RequestScoped
public class KorisnikController {

    @Inject
    private Korisnik korisnik;

    @Inject
    private KorisnikDAO dao;

    public KorisnikController() {
        dao = new KorisnikDAOImpl();
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public void navigateToConfirmation(Korisnik p) {
        dao.save(p);
        String uri = "login.xhtml";
        try{
        FacesContext.getCurrentInstance().getExternalContext().dispatch(uri);
        }catch (IOException ex){
            ex.printStackTrace();
        }
        
            
        
    }
    

}
