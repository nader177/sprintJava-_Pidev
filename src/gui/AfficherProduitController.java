/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javafx.geometry.Insets;
import entities.produits;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.xml.bind.Marshaller.Listener;
import services.Serviceproduits;

/**
 * FXML Controller class
 *
 * @author dot
 */
public class AfficherProduitController implements Initializable {

    @FXML
    private TableView<produits> tableview;
    @FXML
    private TableColumn<produits, String> idpcol;
    @FXML
    private TableColumn<produits, String> nomcol;
    @FXML
    private TableColumn<produits, String> typecol;
    @FXML
    private TableColumn<produits, String> prixcol;
    @FXML
    private TableColumn<produits, String> stockcol;

    Serviceproduits ps = new Serviceproduits();
    ObservableList<produits> obs;
    @FXML
    private Label usernameTF;
    @FXML
    private TableColumn<produits, String> imgp;
    @FXML
    private Label usernameTF1;
    @FXML
    private TextField rech;
    @FXML
    private Button btnrech;

    private Listener myListener;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            // TODO

            List<produits> personnes = ps.afficher();
            obs = FXCollections.observableArrayList(personnes);
            tableview.setItems(obs);
            idpcol.setCellValueFactory(new PropertyValueFactory<>("id_p"));
            nomcol.setCellValueFactory(new PropertyValueFactory<>("nom_p"));
            typecol.setCellValueFactory(new PropertyValueFactory<>("type_p"));
            prixcol.setCellValueFactory(new PropertyValueFactory<>("prix_p"));
            stockcol.setCellValueFactory(new PropertyValueFactory<>("stock_p"));
            imgp.setCellValueFactory(new PropertyValueFactory<>("img"));

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @FXML
    private void SuppProd(ActionEvent event) {
        int selectedIndex = obs.indexOf(tableview.getSelectionModel().getSelectedItem());
        System.out.println(obs.get(0));
        ps.supprimer(obs.get(selectedIndex));
        obs.remove(selectedIndex);
    }

    @FXML
    private void stat(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Chart.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.initOwner(((Node) event.getSource()).getScene().getWindow());
        popupStage.setScene(scene);
        popupStage.show();

    }


    @FXML
    public void recherche(ActionEvent actionEvent) {
        String searchKeyword = rech.getText();

        System.out.println("test searchbar");
        if (actionEvent.getSource() == btnrech) {
            //String searchKeyword = searchBar.getText();
            if (searchKeyword != null && !searchKeyword.isEmpty()) {

                try {
                    // TODO

                    List<produits> personnes = ps.afficher2(searchKeyword);
                    obs = FXCollections.observableArrayList(personnes);
                    tableview.setItems(obs);
                    idpcol.setCellValueFactory(new PropertyValueFactory<>("id_p"));
                    nomcol.setCellValueFactory(new PropertyValueFactory<>("nom_p"));
                    typecol.setCellValueFactory(new PropertyValueFactory<>("type_p"));
                    prixcol.setCellValueFactory(new PropertyValueFactory<>("prix_p"));
                    stockcol.setCellValueFactory(new PropertyValueFactory<>("stock_p"));
                    imgp.setCellValueFactory(new PropertyValueFactory<>("img"));

                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }
}
