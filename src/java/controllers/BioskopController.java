/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import bioskopi.entities.Bioskop;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author jaaaa
 */
@ManagedBean(name="bioskopController")
@RequestScoped
public class BioskopController {
    
    private Map<Integer, String> mapa2 = new LinkedHashMap<Integer, String>();


    public Map<Integer, String> vratiBioskope() {

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory sf = cfg.buildSessionFactory();
        Session s = sf.openSession();
        Transaction tx = s.beginTransaction();

        Query q = s.createQuery("SELECT j FROM Bioskop j");
        List<Bioskop> listaSala = q.list();

        Iterator<Bioskop> iterator = listaSala.iterator();

        while (iterator.hasNext()) {
            Bioskop bioskop = iterator.next();

            mapa2.put(bioskop.getBioskopID(), bioskop.getIme() + bioskop.getGrad());

        }
        return mapa2;

    }
}
