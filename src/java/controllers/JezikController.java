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
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
    

    public Map<Integer,String> idimenaJezika() {
        
          try {
            Connection connection = null;
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bioskopi","root","");
            
            
            PreparedStatement ps = null;
            ps=connection.prepareStatement("select * from jezik");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                mapa2.put(Integer.parseInt(rs.getString("jezikID")), rs.getString("ime"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapa2;

    }

}
