/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import bioskopi.entitesDAO.KartaDAO;
import bioskopi.entitesDAO.KartaDAOImpl;
import bioskopi.entitesDAO.SedisteDAO;
import bioskopi.entitesDAO.SedisteDAOImpl;
import bioskopi.entities.Karta;
import bioskopi.entities.Korisnik;
import bioskopi.entities.Prikazivanje;
import bioskopi.entities.Sediste;
import bioskopi.entities.SedistePK;
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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author jaaaa
 */
@ManagedBean(name = "kartaController")
@SessionScoped
public class KartaController {

    
    private int idPrikazivanja, idBioskopa, cena,idSedista,korisnikID;
    private List<Prikazivanje> listaPrikazivanja = new ArrayList<Prikazivanje>();
    private List<Sediste> listaSedista = new ArrayList<Sediste>();
    private Map<Integer, String> mapaImena = new LinkedHashMap<Integer, String>();
    
    private KartaDAO dao;
    
    public KartaController(){
        dao =new KartaDAOImpl();
    }

    public int getKorisnikID() {
        return korisnikID;
    }

    public void setKorisnikID(int korisnikID) {
        this.korisnikID = korisnikID;
    }

    
    public int getIdSedista() {
        return idSedista;
    }

    public void setIdSedista(int idSedista) {
        this.idSedista = idSedista;
    }
   

   
    

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public List<Sediste> getListaSedista() {
        return listaSedista;
    }

    public void setListaSedista(List<Sediste> listaSedista) {
        this.listaSedista = listaSedista;
    }
    

    public Map<Integer, String> getMapaImena() {
        return mapaImena;
    }

    public void setMapaImena(Map<Integer, String> mapaImena) {
        this.mapaImena = mapaImena;
    }

    public int getIdBioskopa() {
        return idBioskopa;
    }

    public void setIdBioskopa(int idBioskopa) {
        this.idBioskopa = idBioskopa;
    }

    public List<Prikazivanje> getListaPrikazivanja() {
        return listaPrikazivanja;
    }

    public void setListaPrikazivanja(List<Prikazivanje> listaPrikazivanja) {
        this.listaPrikazivanja = listaPrikazivanja;
    }

    public int getIdPrikazivanja() {
        return idPrikazivanja;
    }

    public void setIdPrikazivanja(int idPrikazivanja) {
        this.idPrikazivanja = idPrikazivanja;
    }



    public void valueChange(ValueChangeEvent e) {
        if(!mapaImena.isEmpty()){
            mapaImena.clear();
        }
        idBioskopa = Integer.parseInt(e.getNewValue() + "");

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory sf = cfg.buildSessionFactory();
        Session s = sf.openSession();
        Transaction tx = s.beginTransaction();

        Query q = s.createQuery("SELECT p FROM Prikazivanje p WHERE p.sala.salaPK.bioskopID = :idBioskopa");
        q.setInteger("idBioskopa", idBioskopa);
        setListaPrikazivanja((List<Prikazivanje>) q.list());
        if(!listaPrikazivanja.isEmpty()){
        Iterator<Prikazivanje> i = listaPrikazivanja.iterator();

        while (i.hasNext()) {
            Prikazivanje p2 = new Prikazivanje();
            p2 = i.next();
            q = s.createQuery("SELECT naziv FROM Film f WHERE f.filmID = :idFilm");
            q.setInteger("idFilm", p2.getFilmID().getFilmID());
            mapaImena.put(p2.getPrikazivanjeID(), q.uniqueResult().toString());
            
        }
        }else{
            mapaImena.clear();
            listaSedista.clear();
            
        }
        s.flush();
        s.close();

    }
    
    public void valueChange2(ValueChangeEvent e2) {
        if(!mapaImena.isEmpty() ){
        idPrikazivanja= Integer.parseInt(e2.getNewValue() + "");
        
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory sf = cfg.buildSessionFactory();
        Session s = sf.openSession();
        Transaction tx = s.beginTransaction();
        
        Query q = s.createQuery("SELECT s FROM Sediste s WHERE s.sedistePK.prikazivanjeID = :idPrikazivanja AND s.isFree = :slobodno");
        q.setInteger("idPrikazivanja", idPrikazivanja);
        q.setBoolean("slobodno", true);
        setListaSedista((List<Sediste>)q.list());
        q = s.createQuery("SELECT cena FROM Prikazivanje p WHERE p.prikazivanjeID = :idPrikazivanja");
        q.setInteger("idPrikazivanja", idPrikazivanja);
        cena=(Integer)q.uniqueResult();

        s.flush();
        s.close();
        }
        else{
            listaSedista.clear();
        }
    }
    public void dodajKartu(){
        Karta k= new Karta();
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory sf = cfg.buildSessionFactory();
        Session s = sf.openSession();
        Transaction tx = s.beginTransaction();
        Query q = s.createQuery("SELECT k FROM Korisnik k WHERE k.korisnikID = :id");
        q.setInteger("id", korisnikID);
        
        k.setKorisnikID((Korisnik)q.uniqueResult());
        q = s.createQuery("SELECT s FROM Sediste s WHERE s.sedistePK.prikazivanjeID = :idPrikazivanja AND s.sedistePK.sedisteID = :idSedista");
        q.setInteger("idPrikazivanja", idPrikazivanja);
        q.setInteger("idSedista", idSedista);
        
        
        
        Sediste sediste=(Sediste)q.uniqueResult();
        sediste.setIsFree(Boolean.FALSE);
        SedisteController sc=new SedisteController();
        s.update((Sediste)sediste);
        k.setSediste(sediste);
        dao.save(k);
        tx.commit();
        s.flush();
        s.close();
         mapaImena.clear();
        listaSedista.clear();
    }
    
}

