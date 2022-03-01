package ba.unsa.etf.rpr.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class MainPageController {
    public HBox upcomingInspections;
    public Label welcomeLabel;

     @FXML
    public void initialize() {
         LocalTime time = LocalTime.now();
         if(time.getHour() >= 12 && time.getHour() < 19)
             welcomeLabel.setText("Good afternoon");
         else if(time.getHour() > 3 && time.getHour() < 12)
             welcomeLabel.setText("Good morning");
         else
             welcomeLabel.setText("Good evening");
         try {
             Pane test = FXMLLoader.load(getClass().getResource("/fxml/buildingCard.fxml"));
             Pane test1 = FXMLLoader.load(getClass().getResource("/fxml/buildingCard.fxml"));
             Pane test2 = FXMLLoader.load(getClass().getResource("/fxml/buildingCard.fxml"));
             Pane test3 = FXMLLoader.load(getClass().getResource("/fxml/buildingCard.fxml"));
             Pane test4 = FXMLLoader.load(getClass().getResource("/fxml/buildingCard.fxml"));
             upcomingInspections.getChildren().add(test);
             upcomingInspections.getChildren().add(test1);
             upcomingInspections.getChildren().add(test3);
             upcomingInspections.getChildren().add(test4);
             upcomingInspections.getChildren().add(test2);
             Pane test5 = FXMLLoader.load(getClass().getResource("/fxml/buildingCard.fxml"));
             Pane test6 = FXMLLoader.load(getClass().getResource("/fxml/buildingCard.fxml"));
             Pane test7 = FXMLLoader.load(getClass().getResource("/fxml/buildingCard.fxml"));
             Pane test8 = FXMLLoader.load(getClass().getResource("/fxml/buildingCard.fxml"));
             Pane test9 = FXMLLoader.load(getClass().getResource("/fxml/buildingCard.fxml"));
             upcomingInspections.getChildren().add(test5);
             upcomingInspections.getChildren().add(test6);
             upcomingInspections.getChildren().add(test7);
             upcomingInspections.getChildren().add(test8);
             upcomingInspections.getChildren().add(test9);
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

     public void loginButtonClick(ActionEvent actionEvent) {
         try {
             Stage stage = ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
             LogInController logInController = new LogInController();
             FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
             fxmlLoader.setController(logInController);
             Parent root = fxmlLoader.load();
             Scene scene = new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight());
             stage.setTitle("Log In");
             stage.setScene(scene);
             stage.show();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

}
