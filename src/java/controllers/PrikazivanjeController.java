/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import bioskopi.entitesDAO.FilmDAO;
import bioskopi.entitesDAO.PrikazivanjeDAO;
import bioskopi.entities.Bioskop;
import bioskopi.entities.Film;
import bioskopi.entities.Jezik;
import bioskopi.entities.Prikazivanje;
import bioskopi.entities.Reziser;
import bioskopi.entities.Sala;
import bioskopi.entities.Sediste;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author jaaaa
 */
@ManagedBean(name = "prikazivanjeController")
@SessionScoped
public class PrikazivanjeController {

    @Inject
    private Prikazivanje prikazivanje;
    @Inject
    private PrikazivanjeDAO dao;

    private Map<String, String> mapa2 = new LinkedHashMap<String, String>();
    private List<Sala> salaLista = new ArrayList<Sala>();
    private int idFilma,idBioskopa;

    public int getIdBioskopa() {
        return idBioskopa;
    }

    public void setIdBioskopa(int idBioskopa) {
        this.idBioskopa = idBioskopa;
    }
    private int idSala;

    public Map<String, String> getMapa2() {
        return mapa2;
    }

    public void setMapa2(Map<String, String> mapa2) {
        this.mapa2 = mapa2;
    }


    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public List<Sala> getSalaLista() {
        return salaLista;
    }

    public void setSalaLista(List<Sala> salaLista) {
        this.salaLista = salaLista;
    }



    public Prikazivanje getPrikazivanje() {
        return prikazivanje;
    }

    public void setPrikazivanje(Prikazivanje prikazivanje) {
        this.prikazivanje = prikazivanje;
    }

    public PrikazivanjeDAO getDao() {
        return dao;
    }

    public void setDao(PrikazivanjeDAO dao) {
        this.dao = dao;
    }

    public int getIdFilma() {
        return idFilma;
    }

    public void setIdFilma(int idFilma) {
        this.idFilma = idFilma;
    }

    public void vratiSale() {
        try {
            Connection connection = null;
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bioskopi", "root", "");

            PreparedStatement ps = null;
            ps = connection.prepareStatement("select * from sala");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                mapa2.put(rs.getString("salaID")+"_"+rs.getString("bioskopID"), "Broj bioskopa:" + rs.getString("bioskopID") + " broj sale:" + rs.getString("grad")
                        + " broj mesta: " + rs.getString("brojMesta"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dodajPrikazivanje(Prikazivanje p) {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory sf = cfg.buildSessionFactory();
        Session s = sf.openSession();
        Transaction tx = s.beginTransaction();
        Query q = s.createQuery("SELECT s FROM Sala s WHERE s.salaPK.bioskopID = :bioskopID AND s.salaPK.salaID = :salaID");
        q.setInteger("bioskopID", idBioskopa);
        q.setInteger("salaID", idSala);
        Sala sala = (Sala) q.uniqueResult();
        p.setSala(sala);
        q = s.createQuery("SELECT f FROM Film f WHERE f.filmID = :filmID");
        q.setInteger("filmID", idFilma);
        Film film = (Film) q.uniqueResult();
        p.setFilmID(film);
        s.flush();
        s.close();

        dao.save(p);
        
        SedisteController sc=new SedisteController();
        Sediste sediste;
        for(int i=0;i<sala.getBrojSedista();i++){
            sediste = new Sediste(idSala, idBioskopa,p.getPrikazivanjeID(), i);
            sediste.setIsFree(Boolean.TRUE);
            sc.ubaciSedista(sediste);
        }

    }
    public void valueChange(ValueChangeEvent e) {
        idBioskopa = Integer.parseInt(e.getNewValue() + "");

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory sf = cfg.buildSessionFactory();
        Session s = sf.openSession();
        Transaction tx = s.beginTransaction();

        Query q = s.createQuery("SELECT s FROM Sala s WHERE s.salaPK.bioskopID = :bioskopID");
        q.setInteger("bioskopID", idBioskopa);

        setSalaLista(q.list());
        s.flush();
        s.close();
    }
}
