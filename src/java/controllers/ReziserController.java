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

private Map<Integer,String> mapa = new LinkedHashMap<Integer,String>();
     public Map<Integer,String> idimenaReziser() {
        
        try {
            Connection connection = null;
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bioskopi","root","");
            
            
            PreparedStatement ps = null;
            ps=connection.prepareStatement("select * from reziser");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                mapa.put(Integer.parseInt(rs.getString("reziserID")), rs.getString("ime") + " " + rs.getString("prezime"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapa;
    }
 
}
