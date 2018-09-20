/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import bioskopi.entitesDAO.KorisnikDAO;
import bioskopi.entitesDAO.KorisnikDAOImpl;
import bioskopi.entitesDAO.SedisteDAO;
import bioskopi.entitesDAO.SedisteDAOImpl;
import bioskopi.entities.Jezik;
import bioskopi.entities.Korisnik;
import bioskopi.entities.Reziser;
import bioskopi.entities.Sala;
import bioskopi.entities.Sediste;
import bioskopi.entities.SedistePK;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author jaaaa
 */
@ManagedBean(name = "sedisteController")
@RequestScoped
public class SedisteController {

    
    private Sediste sediste;

    
    private SedisteDAO dao;

    public SedisteController() {
        dao = new SedisteDAOImpl();
    }

    public Sediste getSediste() {
        return sediste;
    }

    public void setSediste(Sediste Sediste) {
        this.sediste = sediste;
    }

    public void ubaciSedista(Sediste s) {
            
            
            dao.save(s);
        
    }
    
    public void azuzirajSediste(Sediste s){
        dao.saveOrUpdate(s);
    }
}
