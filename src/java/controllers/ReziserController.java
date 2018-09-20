/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

/**
 *
 * @author UNDP
 */
import bioskopi.entitesDAO.FilmDAO;
import bioskopi.entitesDAO.FilmDAOImpl;
import bioskopi.entitesDAO.JezikDAO;
import bioskopi.entitesDAO.JezikDAOImpl;
import bioskopi.entitesDAO.ReziserDAO;
import bioskopi.entitesDAO.ReziserDAOImpl;
import bioskopi.entities.Film;
import bioskopi.entities.Jezik;
import bioskopi.entities.Reziser;
import bioskopi.hibernate.util.HibernateUtil;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author UNDP
 */
@ManagedBean(name = "reziserController")
@RequestScoped
public class ReziserController {

    private Map<Integer, String> mapa = new LinkedHashMap<Integer, String>();

    public Map<Integer, String> idimenaReziser() {

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory sf = cfg.buildSessionFactory();
        Session s = sf.openSession();
        Transaction tx = s.beginTransaction();

        Query q = s.createQuery("SELECT j FROM Reziser j");
        List<Reziser> listaSala = q.list();

        Iterator<Reziser> iterator = listaSala.iterator();

        while (iterator.hasNext()) {
            Reziser reziser = iterator.next();

            mapa.put(reziser.getReziserID(), reziser.getIme() + "" + reziser.getPrezime());

        }
        return mapa;
    }

}
