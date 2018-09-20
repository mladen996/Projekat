/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author jaaaa
 */
@ManagedBean(name="bioskopController")
@RequestScoped
public class BioskopController {
    
    private Map<Integer, String> mapa2 = new LinkedHashMap<Integer, String>();

   
    
    
     public Map<Integer, String> vratiBioskope() {

        try {
            Connection connection = null;
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bioskopi", "root", "");

            PreparedStatement ps = null;
            ps = connection.prepareStatement("select * from bioskop");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                mapa2.put(Integer.parseInt(rs.getString("bioskopID")), rs.getString("ime")+rs.getString("grad"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapa2;

    }
}
