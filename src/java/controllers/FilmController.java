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
import bioskopi.entities.Film;
import bioskopi.entities.Jezik;
import bioskopi.entities.Reziser;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author UNDP
 */
@ManagedBean(name = "filmController")
@RequestScoped
public class FilmController {

    private Film film;

    private FilmDAO dao;

    private Map<Integer, String> mapa2 = new LinkedHashMap<Integer, String>();

    public FilmDAO getDao() {
        return dao;
    }

    public void setDao(FilmDAO dao) {
        this.dao = dao;
    }

    public Map<Integer, String> getMapa2() {
        return mapa2;
    }

    public void setMapa2(Map<Integer, String> mapa2) {
        this.mapa2 = mapa2;
    }

    private int idJezika, idRezisera;

    public int getIdRezisera() {
        return idRezisera;
    }

    public void setIdRezisera(int idRezisera) {
        this.idRezisera = idRezisera;
    }

    public int getIdJezika() {
        return idJezika;
    }

    public void setIdJezika(int idJezika) {
        this.idJezika = idJezika;
    }

    public FilmController() {
        dao = new FilmDAOImpl();
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film m) {
        this.film = m;
    }

    public void dodajFilm(Film f) {

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory sf = cfg.buildSessionFactory();
        Session s = sf.openSession();
        Transaction tx = s.beginTransaction();
        Query q = s.createQuery("SELECT j FROM Jezik j WHERE j.jezikID = :jezikID");
        q.setInteger("jezikID", idJezika);
        Jezik jezik = (Jezik) q.uniqueResult();
        f.setJezikID(jezik);
        q = s.createQuery("SELECT r FROM Reziser r WHERE r.reziserID = :reziserID");
        q.setInteger("reziserID", idRezisera);
        Reziser reziser = (Reziser) q.uniqueResult();
        f.setReziserID(reziser);
        dao.save(f);
        s.flush();
        s.close();

    }

    public Map<Integer, String> vratiFilmove() {

        try {
            Connection connection = null;
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bioskopi", "root", "");

            PreparedStatement ps = null;
            ps = connection.prepareStatement("select * from film");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                mapa2.put(Integer.parseInt(rs.getString("filmID")), rs.getString("naziv"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapa2;

    }

}
