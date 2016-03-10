/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensode.jsf.doa;

/**
 *
 * @author Sammy
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
import com.ensode.jsf.util.DataConnect;
 
public class LoginDAO {
 
    public static boolean validate(String email, String password) {
        Connection con = null;
        PreparedStatement ps = null;
 
        try {
            con = DataConnect.getConnection();
            ps = con.prepareStatement("Select EMAIL_ADDRESS,PASSWORD from MPP_USERS where EMAIL_ADDRESS = ? and PASSWORD = ? ");
            ps.setString(1, email);
            ps.setString(2, password);
            
 
            ResultSet rs = ps.executeQuery();
 
            if (rs.next()) {
               
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return false;
        } finally {
            DataConnect.close(con);
        }
        return false;
    }
}
