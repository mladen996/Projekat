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
import bioskopi.entitesDAO.JezikDAO;
import bioskopi.entitesDAO.JezikDAOImpl;
import bioskopi.entities.Jezik;
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
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
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
@ManagedBean(name = "jezikController")
@RequestScoped
public class JezikController {

    private Map<Integer, String> mapa2 = new LinkedHashMap<Integer, String>();

    public Map<Integer, String> getMapa2() {
        return mapa2;
    }

    public void setMapa2(Map<Integer, String> mapa2) {
        this.mapa2 = mapa2;
    }

    public Map<Integer, String> idimenaJezika() {

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory sf = cfg.buildSessionFactory();
        Session s = sf.openSession();
        Transaction tx = s.beginTransaction();

        Query q = s.createQuery("SELECT j FROM Jezik j");
        List<Jezik> listaSala = q.list();

        Iterator<Jezik> iterator = listaSala.iterator();

        while (iterator.hasNext()) {
            Jezik jezik = iterator.next();

            mapa2.put(jezik.getJezikID(), jezik.getIme());

        }
        return mapa2;

    }

}
