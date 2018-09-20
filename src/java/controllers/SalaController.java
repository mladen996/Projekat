/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import bioskopi.entitesDAO.GenericDAO;
import bioskopi.entitesDAO.GenericDAOImpl;
import bioskopi.entitesDAO.KorisnikDAO;
import bioskopi.entitesDAO.KorisnikDAOImpl;
import bioskopi.entitesDAO.SalaDAO;
import bioskopi.entitesDAO.SalaDAOImpl;
import bioskopi.entitesDAO.SedisteDAO;
import bioskopi.entitesDAO.SedisteDAOImpl;
import bioskopi.entities.Korisnik;
import bioskopi.entities.Sala;
import bioskopi.entities.SalaPK;
import bioskopi.entities.Sediste;
import bioskopi.entities.SedistePK;
import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jaaaa
 */
@ManagedBean(name = "salaController")
@RequestScoped
public class SalaController {

    private Sala sala;

    private SalaDAO dao;

    private int idBioskopa;

    public int getIdBioskopa() {
        return idBioskopa;
    }

    public void setIdBioskopa(int idBioskopa) {
        this.idBioskopa = idBioskopa;
    }

    public SalaController() {
        dao = new SalaDAOImpl();
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public void dodajSalu(Sala s) {
        SalaPK spk = new SalaPK();
        spk.setBioskopID(idBioskopa);
        s.setSalaPK(spk);
        dao.save(s);

    }
}
