/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.produits;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import services.Serviceproduits;
import java.awt.Image;
import java.io.File;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author dot
 */
public class AjoutProduitController implements Initializable {

    @FXML
    private TextField idP;
    @FXML
    private TextField typeP;
    @FXML
    private TextField stockP;
    @FXML
    private TextField nomP;
    @FXML
    private TextField prixP;
    @FXML
    private ListView imagep;
     @FXML
    private Button insérer;

    /**
     * Initializes the controller class.
     */ 
    
    Serviceproduits ps = new Serviceproduits();
    @FXML
    private Label usernameTF;
    
    File selectedfile;
    String path_img;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterProduit(ActionEvent event) {
            try {

            try {
                if (idP.getText().equalsIgnoreCase("") && typeP.getText().equalsIgnoreCase("") && nomP.getText().equalsIgnoreCase("")&& stockP.getText().equalsIgnoreCase("")&& prixP.getText().equalsIgnoreCase("")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Echec de l'ajout");
                    alert.setHeaderText(null);
                    alert.setContentText("Attention ! Verifier les données saisie (Pas de champs vides)");
                    alert.showAndWait();
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                e.getCause();
            }
        
        String idprod = idP.getText();
        
        String typeProd = typeP.getText();
        
        String nomProd = nomP.getText();
        
        String stockProd = stockP.getText();
        
        String prixProd = prixP.getText();
        
        if (selectedfile != null) {
                String file = selectedfile.getName();
                System.out.println(file);
                Serviceproduits ps = new Serviceproduits();
        produits p = new produits(idprod, nomProd, typeProd, prixProd, stockProd,file);
        ps.ajouter(p);}
       /* else{
            Serviceproduits ps = new Serviceproduits();
        produits p = new produits(idprod, nomProd, typeProd, prixProd, stockProd);
         ps.ajouter(p);}*/
        
         Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
            alert0.setTitle("information Dialog");
            alert0.setHeaderText(null);
            alert0.setContentText("Ajout avec succes ");
            alert0.show();
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (Exception ex) {
            ex.printStackTrace();
            ex.getCause(); }
       }

        

    @FXML
    private void AfficherProduits(ActionEvent event) {
        
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

    @FXML
    private void insererimage(ActionEvent event) throws IOException {
        

                FileChooser fc = new FileChooser();
            fc.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("image", "*.jpg", "*.png")
            );
            selectedfile = fc.showOpenDialog(null);
            if (selectedfile != null) {
               BufferedImage bufferedImage = ImageIO.read(selectedfile);
               Image image = bufferedImage.getScaledInstance(600, 400, Image.SCALE_DEFAULT);
                System.out.println(selectedfile);
               
                resizeFile(selectedfile.getAbsolutePath(),"C:\\xampp\\htdocs\\img\\"+selectedfile.getName(),500,500);


                path_img = selectedfile.getAbsolutePath();

            } else {
                System.out.println("Fichier erroné");
            }
    }
    
    public void resizeFile(String imagePathToRead,
                              String imagePathToWrite, int resizeWidth, int resizeHeight)
            throws IOException {

        File fileToRead = new File(imagePathToRead);
        BufferedImage bufferedImageInput = ImageIO.read(fileToRead);

        BufferedImage bufferedImageOutput = new BufferedImage(resizeWidth,
                resizeHeight, bufferedImageInput.getType());

        Graphics2D g2d = bufferedImageOutput.createGraphics();
        g2d.drawImage(bufferedImageInput, 0, 0, resizeWidth, resizeHeight, null);
        g2d.dispose();

        String formatName = imagePathToWrite.substring(imagePathToWrite
                .lastIndexOf(".") + 1);

        ImageIO.write(bufferedImageOutput, formatName, new File(imagePathToWrite));
        
    }

    @FXML
    private void gestionCmd(MouseEvent event) {
        
        
          try {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutProduit.fxml"));
             Parent root = loader.load();
           // Serviceproduits controller = loader.getController();
           // controller.setUsername(usernameTF.getText());ssss
            usernameTF.getScene().setRoot(root);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        
    }


    
    
}
