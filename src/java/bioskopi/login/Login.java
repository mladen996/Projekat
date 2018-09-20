/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bioskopi.login;


import bioskopi.entities.Korisnik;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;



@ManagedBean(name = "login_bean")
@SessionScoped
public class Login {
    private String message = "";
    private String username;
    private String password;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    public Login() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public int checkUser() {
        int login = 0;

        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory sf = cfg.buildSessionFactory();
        Session s = sf.openSession();
        Transaction tx = s.beginTransaction();

        Query q = s.createQuery("SELECT k FROM Korisnik k WHERE k.username = :username");
        q.setString("username", username);
        Korisnik korisnik = (Korisnik) q.uniqueResult();
        if (korisnik != null) {
            if (korisnik.getPassword().equals(password)) {

                login = 1;
                if (korisnik.getIsAdmin() != null) {
                    login = 2;
                }
            } else {
                this.message = "Password nije validan.";
            }
        } else {
            this.message = "Username nije validan";
        }
        return login;
    }
}
    

