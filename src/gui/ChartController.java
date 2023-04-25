/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.Serviceproduits;

/**
 * FXML Controller class
 *
 * @author Ben Nasr
 */
public class ChartController implements Initializable {

   @FXML
    private Pane chart;
    @FXML
    private Button bye;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Serviceproduits es = new Serviceproduits();
    ObservableList<Integer> genderCounts = es.getGenderCounts();
    
    int numMale = genderCounts.get(0);
    int numFemale = genderCounts.get(1);
    
    ObservableList<PieChart.Data> genderData = FXCollections.observableArrayList(
        new PieChart.Data("adulte", numMale),
        new PieChart.Data("enfants", numFemale)
    );
    
    PieChart genderChart = new PieChart(genderData);
    chart.getChildren().add(genderChart);
}

    @FXML
    private void ExitChart(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AfficherProduit.fxml")) ;
            Scene rcScene= new Scene(root);
            Stage window= (Stage)((Node)event.getSource()) .getScene().getWindow();
            window.setScene(rcScene);
            window.show();
        
    
    }
    
}
