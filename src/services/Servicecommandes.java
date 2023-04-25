/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entities.commandes;
import java.io.FileOutputStream;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;
import java.time.LocalDate;

import java.time.LocalDate;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author nader
 */
public class Servicecommandes implements IService<commandes>{
      Connection cnx;

    public Servicecommandes() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(commandes p) {
        String req = "INSERT INTO commandes (date, nom, prenom,adress, tel, id_produit_id, status) VALUES(?,?,?,?,?,?,?);";
        try {
            //`donation`(`id`, `username_id`, `title_id`, `pointsdonated`, `datedonation`)
            //charity_demand`(`id`, `username_id`, `category_id`, `title`, `receiver`, `pointsdemanded`, `datedemand`, `file_upload`)
           
            //Statement st = cnx.createStatement();
            PreparedStatement pst = cnx.prepareStatement(req);
            
            pst.setDate(1,Date.valueOf(LocalDate.now()));
            pst.setString(2, p.getNom());
            pst.setString(3, p.getPrenom());
            pst.setString(4, p.getAdress());
            pst.setString(5, p.getTel());
            pst.setInt(6, p.getIdprod());
            pst.setString(7,"non");
           
            
            
            pst.executeUpdate();
            System.out.println("Commandes Ajoutée !");
        } catch (SQLException ex) {
            
            System.out.println("nonnnnnnn");
            System.out.println(ex.getMessage());
        }
          
    }

    @Override
    public void modifier(commandes p) {
        String req = "UPDATE `date_r` SET `date`= ?  where id=?";
         try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(8, p.getId());
            pst.setString(1, p.getDate());
            pst.setString(2, p.getNom());
            pst.setString(3, p.getPrenom());
            pst.setString(4, p.getAdress());
            pst.setString(5, p.getTel());
            pst.setString(6, p.getProd().getId_p());
            pst.setString(7, p.getStatus());
          
            pst.executeUpdate();
            System.out.println("Commandes  modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(commandes p) {
       String req = "DELETE FROM `date_r` WHERE id=?";
        
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, p.getId());
            pst.executeUpdate();
            System.out.println("Commandes supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

  
    public List<commandes> afficher() {
             List<commandes> list = new ArrayList<>();
        
        String req = "SELECT * FROM `commandes`";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet result = pst.executeQuery();
            while(result.next()) {
                list.add(new commandes(result.getInt("id"),result.getString("date"),result.getString("nom"),result.getString("prenom"),result.getString("adress"),result.getString("tel"),result.getString("status")));
            }
            System.out.println("commandes récupérées !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }

    @Override
    public List<commandes> recuperer() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   public void accept(commandes t) throws SQLException {
        try {
            
            String query = "UPDATE commandes SET status=? WHERE id=?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setString(1, "oui");
            stmt.setInt(2, t.getId());
            stmt.executeUpdate();

            System.out.println("commandes  Accepted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void reject(commandes t) throws SQLException {
        try {
            System.out.println(t);
            String query = "UPDATE commandes SET status=? WHERE id=?";
            PreparedStatement stmt = cnx.prepareStatement(query);
            stmt.setString(1, "non");
            stmt.setInt(2, t.getId());
            stmt.executeUpdate();

            System.out.println("commandes Rejected !");
                 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
 
    }


    
    
    
    
    
    

