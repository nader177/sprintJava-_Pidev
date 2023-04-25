/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.barcodelib.barcode.Linear;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import entities.commandes;
import entities.produits;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import javax.imageio.ImageIO;
import javax.xml.bind.Marshaller;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import services.Servicecommandes;

/**
 * FXML Controller class
 *
 * @author dot
 */
public class AfficherCommandeController implements Initializable {

    @FXML
    private TableView<commandes> tableview;
    @FXML
    private TableColumn<commandes, String> datec;
    @FXML
    private TableColumn<commandes, String> nomc;
    @FXML
    private TableColumn<commandes, String> prenomc;
    @FXML
    private TableColumn<commandes, String> adressc;
    @FXML
    private TableColumn<commandes, String> telc;
    @FXML
    private TableColumn<commandes, Integer> idprodc;
    @FXML
    private TableColumn<commandes, String> statusc;
    
    Servicecommandes ps = new Servicecommandes();
    ObservableList<commandes> obs;
    @FXML
    private Label username;
    @FXML
    private TextField tcode;
    @FXML
    private Label barcode_read;
    @FXML
    private TextField br_path;
    @FXML
    private TextField qr_text;
    @FXML
    private TextField QR_path;
    @FXML
    private Label QR_read;
    
    @FXML
    private AnchorPane PaneProduit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        List<commandes> commandes = ps.afficher();
        obs = FXCollections.observableArrayList(commandes);
        tableview.setItems(obs);
        datec.setCellValueFactory(new PropertyValueFactory<>("date"));
        nomc.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomc.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        adressc.setCellValueFactory(new PropertyValueFactory<>("adress"));
        telc.setCellValueFactory(new PropertyValueFactory<>("tel"));
        idprodc.setCellValueFactory(new PropertyValueFactory<>("id_produit_id"));
        statusc.setCellValueFactory(new PropertyValueFactory<>("status"));
    }
   

    private void SuppComm(ActionEvent event) {
        int selectedIndex = obs.indexOf( tableview.getSelectionModel().getSelectedItem());
        System.out.println(obs.get(0)); 
        ps.supprimer(obs.get(selectedIndex));
        obs.remove(selectedIndex);
    }

    @FXML
    private void Accepter(ActionEvent event) throws SQLException {
        
        int selectedIndex = obs.indexOf(tableview.getSelectionModel().getSelectedItem());
        
            // Créer une boîte de dialogue d'alerte
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Accept");
            alert.setHeaderText("Do you really want to Accept this commande ?");

            ButtonType buttonTypeYes = new ButtonType("Oui");
            ButtonType buttonTypeNo = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

            // Afficher la boîte de dialogue et attendre la réponse de l'utilisateur
            Optional<ButtonType> result = alert.showAndWait();
         if (result.get() == buttonTypeYes) {
              System.out.println("av :"+ obs.get(selectedIndex));
                ps.accept(obs.get(selectedIndex)); 
                System.out.println("ap :"+ obs.get(selectedIndex));
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle(" Accept");
            alert1.setHeaderText(null);
            alert1.setContentText("This commande is accepted");
            alert1.showAndWait();       
          
        } 
    }

    @FXML
    private void Refuser(ActionEvent event) throws SQLException {
        int selectedIndex = obs.indexOf(tableview.getSelectionModel().getSelectedItem());
        // System.out.println( obs.get(selectedIndex).getStatue().equalsIgnoreCase("in progress"));
        if (selectedIndex < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed ");
            alert.setHeaderText(null);
            alert.setContentText("no selected ");
            alert.showAndWait();
            return;
        } else if (!obs.get(selectedIndex).getStatus().equalsIgnoreCase("Refuser")) {
            // Créer une boîte de dialogue d'alerte
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Reject");
            alert.setHeaderText("Do you really want to Reject this commande ?");

            ButtonType buttonTypeYes = new ButtonType("Oui");
            ButtonType buttonTypeNo = new ButtonType("Non", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

            // Afficher la boîte de dialogue et attendre la réponse de l'utilisateur
            Optional<ButtonType> result = alert.showAndWait();
           if (result.get() == buttonTypeYes) {
               System.out.println("hhhh :"+ obs.get(selectedIndex));
                ps.reject(obs.get(selectedIndex));
                   
               System.out.println("hhhh :"+ obs.get(selectedIndex));
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed to reject");
            alert.setHeaderText(null);
            alert.setContentText("This transaction is already Rejected");
            alert.showAndWait();
            return;
        } 
    }

    private void pdf(ActionEvent event) {
            int selectedIndex = obs.indexOf(tableview.getSelectionModel().getSelectedItem());
                
            Servicecommandes pdf = new Servicecommandes();       
         //   pdf.orderPdf(obs.get(selectedIndex));
            Alert alert0 = new Alert(Alert.AlertType.INFORMATION);
            alert0.setTitle("Information dialog box");
            alert0.setHeaderText(null);
            alert0.setContentText("The pdf file has been exported successfully. ");
            alert0.show();  
    }

    @FXML
    private void btnwritecode(ActionEvent event) {
        
         try {
            
            Linear barcode = new Linear();
            barcode.setType(Linear.CODE128B);
            barcode.setData(tcode.getText());
            barcode.setI(11.0f);
          
          String fname = tcode.getText();
          
            barcode.renderBarcode("C:\\" + fname +".png" );
            
        } catch (Exception e) {
           
        }
    }

    @FXML
    private void btnreadbar(ActionEvent event) {
        
          try {
             InputStream barInputStream = new FileInputStream(br_path.getText());
             BufferedImage barBufferedImage = ImageIO.read(barInputStream);
             LuminanceSource source = new BufferedImageLuminanceSource(barBufferedImage);
             BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
             Reader reader = new MultiFormatReader();
             Result result = reader.decode(bitmap);
             barcode_read.setText(result.getText());
    
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void btnwriteqr(ActionEvent event) {
        
          try {
            
            ByteArrayOutputStream out = QRCode.from(qr_text.getText())
                    .to(ImageType.PNG).stream();
            
            String f_name = qr_text.getText();
            String Path_name = "C:\\" ;
            
            FileOutputStream fout = new FileOutputStream(new File(Path_name +(f_name + ".PNG")));
            fout.write(out.toByteArray());
            fout.flush();    
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void btnreadqr(ActionEvent event) {
        
         try {
            
            InputStream barcodeInputStream = new FileInputStream(QR_path.getText());
            BufferedImage barcBufferedImage = ImageIO.read(barcodeInputStream);
            
            LuminanceSource source = new BufferedImageLuminanceSource(barcBufferedImage);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            Reader reader = new MultiFormatReader();
            Result reslut = reader.decode(bitmap);
            
            QR_read.setText(reslut.getText());
             
        } catch (Exception e) {
        }
    }  

    @FXML
    private void imprimer(ActionEvent event) {
        PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null){
             Window primaryStage = null;
             job.showPrintDialog(primaryStage); 
            
             Node root=this.PaneProduit;   
              job.printPage(root);
              
              job.endJob(); 
        
    }
    }

    @FXML
    private void refff(ActionEvent event) {
        
         // TODO
        
        List<commandes> commandes = ps.afficher();
        obs = FXCollections.observableArrayList(commandes);
        tableview.setItems(obs);
        datec.setCellValueFactory(new PropertyValueFactory<>("date"));
        nomc.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomc.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        adressc.setCellValueFactory(new PropertyValueFactory<>("adress"));
        telc.setCellValueFactory(new PropertyValueFactory<>("tel"));
        idprodc.setCellValueFactory(new PropertyValueFactory<>("id_produit_id"));
        statusc.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        
        
    }
    
    }