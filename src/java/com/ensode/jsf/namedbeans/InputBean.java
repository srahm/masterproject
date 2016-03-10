/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensode.jsf.namedbeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.faces.FacesException;



/**
 *
 * @author Sammy
 */
@SuppressWarnings("unused")
@ManagedBean(name="sgbean")
@RequestScoped

public class InputBean {
    
    PreparedStatement ps = null;
    Connection con = null; 
    Statement stmt= null;
    String url = "jdbc:derby://localhost:1527/MyMarketPlacedb";
    String user = "APP";
    String dbpassword = "helloworld";
    
    int i=0;
    
    private String password;
    private String firstname;
    private String email;
    private String lastname;
    
    

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;   
    }
    
      public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
      public String getEmail() {
        return email;   
    }
    
      public void setEmail(String email) {
        this.email = email;
    }

      
      
    public String add(){
        
         try
            {
                     Class.forName("com.mysql.jdbc.Driver");
                      con = DriverManager.getConnection(url, user, dbpassword);

                

                   
                    String sql = "insert into MPP_USERS "
					+ " (LAST_NAME, FIRST_NAME,EMAIL_ADDRESS,PASSWORD)" + " values (?, ?, ?, ?)";
                        
                        ps= con.prepareStatement(sql); 
                        ps.setString(1, lastname);
			ps.setString(2, firstname);
			ps.setString(3, email);
                        ps.setString(4,password);
                        System.out.println("Insert complete.");
                        ps.executeUpdate();
                        System.out.println("Insert complete.");


            }
         
         catch(Exception e)
            {
                System.out.print("Failure");
                
                
                
             return "registerFail";  
            }

     return "confirmation.xhtml?faces-redirect=true";
    }
    
    


}
