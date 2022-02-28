package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LogInController {
    public TextField emailField;
    public PasswordField passwordField;
    public String emailFormat = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public Label test;

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
        System.out.println("radi");
    }
}