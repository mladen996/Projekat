/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bioskopi.login;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;



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
    public int checkUser() throws SQLException{
        String query;
        String dbUsername, dbPassword;
        boolean dbisAdmin;
        int login = 0;
        int dbID;

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bioskopi", "root", "");
            Statement stmt = (Statement) con.createStatement();
            query = "SELECT username, password, isAdmin, korisnikID FROM korisnik;";
            stmt.executeQuery(query);
            ResultSet rs = stmt.getResultSet();
            
            while(rs.next()){
                dbID =rs.getInt("korisnikID");
                dbUsername = rs.getString("username");
                dbPassword = rs.getString("password");
                dbisAdmin = rs.getBoolean("isAdmin");

                if(dbUsername.equals(username) && dbPassword.equals(password)){
                    id=dbID;
                    login = 1;
                    if(dbisAdmin == true){
                        login = 2;
                    }
                }
                
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(login == 0){
            this.message = "Username ili password nisu validni.";
        }
        return login;
    }
}
    

