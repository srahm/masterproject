/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ensode.jsf.namedbeans;



import com.ensode.jsf.util.DataConnect;
import java.sql.*;
import java.util.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

 
@ManagedBean(name = "ImageBean")
@SessionScoped
 
public class ImageBean {
    private String imageID;
    private String email;
    private String itemDesc;
    private String price;
    
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
    
  
 
    public String getImageLength() {
        return imageLength;
    }
 
    public void setImageLength(String imageLength) {
        this.imageLength = imageLength;
    }
    private String imageLength;
 
    public String getImageID() {
        return imageID;
    }
 
    public void setImageID(String imageID) {
        this.imageID = imageID;
    }
    
    
    Connection MySQLcon = null;
    Statement stmt = null;
    PreparedStatement ps;
    ResultSet rs;
 
    public List<ImageBean> getAllImage() {
        List<ImageBean> imageInfo = new ArrayList<ImageBean>();
        Connection con = DataConnect.getConnection();
        try {
            stmt = con.createStatement();
            String strSql = "select ID,EMAIL_ADDRESS, ITEM_DESC,PRICE from PICTURE_TABLE order by DATE DESC ";
          
            rs = stmt.executeQuery(strSql);
            while (rs.next()) {
                ImageBean tbl = new ImageBean();
                tbl.setImageID(rs.getString("ID"));
                tbl.setEmail(rs.getString("EMAIL_ADDRESS"));
                tbl.setItemDesc(rs.getString("ITEM_DESC"));
                tbl.setPrice(rs.getString("PRICE"));
                imageInfo.add(tbl);
            }
        } catch (SQLException e) {
            System.out.println("Exception in getAllImage::" + e.getMessage());
        }
        return imageInfo;
    }
}
    

