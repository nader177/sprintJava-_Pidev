/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.commandes;
import entities.produits;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import services.Servicecommandes;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import services.Serviceproduits;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author dot
 */
public class AjouterCommandesController implements Initializable {

    @FXML
    private TextField prenomcom;
    @FXML
    private TextField telcom;
    @FXML
    private Label usernameTF;
    @FXML
    private TextField nomcom;
    @FXML
    private TextField adresscom;
    private TextField idprodcom;
    
     Servicecommandes ps = new Servicecommandes();
    private Label username;
    @FXML
    private ComboBox<produits> idprod;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      ListerProd();
        //ListerProd();
        // TODO
    }    

    @FXML
    private void AjouterCommande(ActionEvent event) {
      
        
      //  String date = datecom.getText();
        String nomco = nomcom.getText();
        String prenomco = prenomcom.getText();
        String adressco = adresscom.getText();
        String telco = telcom.getText();
        //produits prod =idprod.getSelectionModel().getSelectedItem();
        produits prod =idprod.getSelectionModel().getSelectedItem();
          // TypeCompte c = typesfx.getSelectionModel().getSelectedItem();
        System.out.println("4555 : "+prod);
        commandes c = new commandes(nomco, prenomco, adressco, telco, prod.getId());        
        System.out.println("prod : "+ c);
        
            ps.ajouter(c);
            Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
            alert0.setTitle("information Dialog");
            alert0.setHeaderText(null);
            alert0.setContentText("Ajout avec succes ");
            alert0.show();
       
    }

    @FXML
    private void AfficherCommande(ActionEvent event) {
//         try {
//             FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherCommandes.fxml"));
//             Parent root = loader.load();
//           // Serviceproduits controller = loader.getController();
//           // controller.setUsername(usernameTF.getText());
//            username.getScene().setRoot(root);
//        } catch (IOException ex) {
//            System.err.println(ex.getMessage());
//        }
 try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherCommande.fxml"));
            Parent root;
            root = loader.load();
            usernameTF.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void ListerProd() {
        
        //Serviceproduits prodc = new Serviceproduits();
        ObservableList<produits> list = FXCollections.observableArrayList();
        try {
            String req ="select id,nom_p, type_p, prix_p,stock_p from produits ";
            Connection cnx = MyDB.getInstance().getCnx();
            PreparedStatement pst = cnx.prepareStatement(req);
            
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                produits p = new produits(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
                
                System.out.println("hh / "+ p);
                list.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        idprod.setItems(null);
        idprod.setItems(list);
                   
    }

    @FXML
    private void gotoprod(MouseEvent event) {
        
          try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherProduit.fxml"));
             Parent root = loader.load();
           // Serviceproduits controller = loader.getController();
           // controller.setUsername(usernameTF.getText());
            usernameTF.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
}
