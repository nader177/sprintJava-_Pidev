/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;


import entities.produits;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyDB;


/**
 *
 * @author feryel
 */
public class Serviceproduits  implements IService<produits  >{
    
      Connection cnx;

    public Serviceproduits() {
        cnx = MyDB.getInstance().getCnx();
    }

    
    @Override
    public void ajouter(produits p) {
        String req = "INSERT INTO `produits`( `id`,`id_p`,`nom_p`,`type_p`,`prix_p`,`stock_p`,`img`) VALUES( ?,?,?,?,?,?,? );";
        try {
            //`donation`(`id`, `username_id`, `title_id`, `pointsdonated`, `datedonation`)
            //charity_demand`(`id`, `username_id`, `category_id`, `title`, `receiver`, `pointsdemanded`, `datedemand`, `file_upload`)
           
            //Statement st = cnx.createStatement();
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, p.getId());
            pst.setString(2, p.getId_p());
            pst.setString(3, p.getNom_p());
            pst.setString(4, p.getType_p());
            pst.setString(5, p.getPrix_p());
            pst.setString(6, p.getStock_p());
            pst.setString(7, p.getImg());
           
           
            
            
            pst.executeUpdate();
            System.out.println("produits Ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
          
    }

    @Override
    public void modifier(produits p) {
        String req = "UPDATE `produits` SET `id_p`= ? ,`nom_p`= ? ,`type_p`= ? ,`prix_p`= ?,`stock_p`= ?,`img`= ? where id=?";
         try {
            PreparedStatement pst = cnx.prepareStatement(req);
          pst.setInt(7, p.getId());
            pst.setString(1, p.getId_p());
            pst.setString(2, p.getNom_p());
            pst.setString(3, p.getType_p());
            pst.setString(4, p.getPrix_p());
            pst.setString(5, p.getStock_p());
            pst.setString(6, p.getImg());
          
            pst.executeUpdate();
            System.out.println("produits  modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(produits p) {
       String req = "DELETE FROM `produits` WHERE id=?";
        
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, p.getId());
            pst.executeUpdate();
            System.out.println("produits supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    public List<produits> afficher() throws SQLException{
                    List<produits> list = new ArrayList<>();
        
        String req = "SELECT * FROM `produits`";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet result = pst.executeQuery();
            while(result.next()) {
                list.add(new produits(result.getInt("id"),result.getString("id_p"),result.getString("nom_p"), result.getString("type_p"), result.getString("prix_p"), result.getString("stock_p"), result.getString("img")));
            }
            System.out.println("produits récupérées !");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
    
    
     public List<produits> afficher2(String nom) throws SQLException{
                    List<produits> list = new ArrayList<>();
        
        String req = "SELECT * FROM `produits` where nom_p LIKE '%"+nom+"%';";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet result = pst.executeQuery();
            while(result.next()) {
                list.add(new produits(result.getInt("id"),result.getString("id_p"),result.getString("nom_p"), result.getString("type_p"), result.getString("prix_p"), result.getString("stock_p"), result.getString("img")));
            }
            System.out.println("produits récupérées !");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }

    @Override
    public List<produits> recuperer() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  
     public ObservableList<Integer> getGenderCounts() {
        ObservableList<Integer> counts = FXCollections.observableArrayList();
        try {
            String req = "SELECT COUNT(*) FROM produits WHERE type_p = 'adulte'";
            Statement st = cnx.createStatement(); 
            ResultSet rs = st.executeQuery(req) ;
            rs.next();
            counts.add(rs.getInt(1));
            
            req = "SELECT COUNT(*) FROM produits WHERE type_p = 'enfant'";
            rs = st.executeQuery(req) ;
            rs.next();
            counts.add(rs.getInt(1));
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return counts;
    }
 
}
