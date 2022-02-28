package ba.unsa.etf.rpr.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class MainPageController {
    public HBox upcomingInspections;

     @FXML
    public void initialize() {
         try {
             Pane test = FXMLLoader.load(getClass().getResource("/fxml/buildingCard.fxml"));
             upcomingInspections.getChildren().add(test);
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

}
