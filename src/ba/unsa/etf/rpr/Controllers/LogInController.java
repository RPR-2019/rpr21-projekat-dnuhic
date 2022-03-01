package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Dao.InspectorDao;
import ba.unsa.etf.rpr.IncorrectEmailOrPasswordException;
import ba.unsa.etf.rpr.Models.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInController {
    public VBox outerVBox;
    public TextField emailField;
    public PasswordField passwordField;
    public String emailFormat = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public Label test;

    private InspectorDao dao = InspectorDao.getInstance();

    @FXML
    public void initialize() {
        emailField.textProperty().addListener(e -> {
            if(emailField.getText().matches(emailFormat)) {
                emailField.setStyle("-fx-border-color: -fx-background -fx-background #00a801 -fx-background;");
            } else {
                emailField.setStyle("-fx-border-color: -fx-background -fx-background #ff6565 -fx-background;");
            }
        });
        passwordField.textProperty().addListener(e -> {
            if(passwordField.getText().length() >= 8) {
                passwordField.setStyle("-fx-border-color: -fx-background -fx-background #00a801 -fx-background;");
            } else {
                passwordField.setStyle("-fx-border-color: -fx-background -fx-background #ff6565 -fx-background;");
            }
        });
        test.requestFocus();
    }

    public void loginButtonClick(ActionEvent actionEvent) {
        if(emailField.getText().matches(emailFormat) && passwordField.getText().length() >= 8) {
            try {
                Person person = dao.login(emailField.getText(), passwordField.getText());
                Stage stage = ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
                FXMLLoader fxmlLoader;
                Scene scene;
                if(person.isAdmin()) {
                    AdminController adminController = new AdminController();
                    fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/admin.fxml"));
                    fxmlLoader.setController(adminController);
                    stage.setTitle("Log In");
                } else {
                    MainPageLoggedInController mainPageLoggedInController = new MainPageLoggedInController();
                    fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/mainPageLoggedIn.fxml"));
                    fxmlLoader.setController(mainPageLoggedInController);
                    stage.setTitle("Log In");
                }
                Parent root = fxmlLoader.load();
                scene = new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight());
                stage.setScene(scene);
                stage.show();
            } catch (IncorrectEmailOrPasswordException e) {
                if(!((outerVBox.getChildren().get(outerVBox.getChildren().size()-2)) instanceof Label)) {
                    Label label = new Label(e.getMessage());
                    label.setStyle("-fx-text-fill: #ff3232");
                    outerVBox.getChildren().add(outerVBox.getChildren().size() - 1, label);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}