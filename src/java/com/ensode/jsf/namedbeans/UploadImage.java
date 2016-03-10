package com.ensode.jsf.namedbeans;

import com.ensode.jsf.util.DataConnect; 
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.UploadedFile;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@ManagedBean(name = "uploadImage")
@SessionScoped
public class UploadImage implements Serializable {

    private static final long serialVersionUID = 1L;
    private UploadedFile file;
    
    private String email;
    private String itemDesc;
    private String price;
    
    

   
      DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	   //get current date time with Date()
	   Date date = new Date();
	   
	  
	   //get current date time with Calendar()
	   Calendar cal = Calendar.getInstance();
	    String time=dateFormat.format(cal.getTime());

 
      public String getPrice(){
        return price;
    }
    
    public void setPrice(String price){
        this.price=price;
        
    }
    
    
    public String getItemDesc(){
        return itemDesc;
    }
    
    public void setItemDesc(String itemDesc){
        this.itemDesc=itemDesc;
        
    }
    
        public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email=email;
        
    }
    
    

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String upload() {
          
        
        if (file!=null ) {
            try {
                System.out.println(file.getFileName());
                InputStream fin2 = file.getInputstream();
                Connection con = DataConnect.getConnection();
                PreparedStatement pre = con.prepareStatement("insert into PICTURE_TABLE (EMAIL_ADDRESS,PRICE,ITEM_DESC,PICTURES,DATE) values(?,?,?,?,?)");
                pre.setString(1, email);
              
                pre.setString(2, price);
                pre.setString(3,itemDesc);
                
                pre.setBinaryStream(4, fin2, file.getSize());
                pre.setString(5,time);
                pre.executeUpdate();
                System.out.println("Inserting Successfully!");
                
               
             

            }
        
            
            catch (Exception e) {
                System.out.println("Exception-File Upload." + e.getMessage());
             return "imageFail";
             
            }
        }
        else{
        FacesMessage msg = new FacesMessage("Please select image!!");
                FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        
        
        file=null;
        email=null;
        price=null;
        itemDesc=null;
        
        
        
        
        
        
       return "confirmationImage.xhtml"; 
    }
}