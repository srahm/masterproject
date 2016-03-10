/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensode.jsf.namedbeans;

import java.io.Serializable;
 
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
 
import com.ensode.jsf.doa.LoginDAO;

/**
 *
 * @author Sammy
 */
@ManagedBean(name="lgbean")
@SessionScoped
public class Login implements Serializable{
    
     
    private static final long serialVersionUID = 1094801825228386363L;
     
    private String password;
    private String msg;
    private String email;
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getMsg() {
        return msg;
    }
 
    public void setMsg(String msg) {
        this.msg = msg;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
   
    public String validateEmailPassword() {
        boolean valid = LoginDAO.validate(email, password);
        if (valid) {
            HttpSession session = SessionBean.getSession();
            session.setAttribute("email", email);
            return "admin";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Email and Password",
                            "Please enter correct username and Password"));
            return "indexFail" ;
        }
    }
 
   
    public String logout() {
        HttpSession session = SessionBean.getSession();
        session.invalidate();
        return "indexhlogout";
    }
    
}
