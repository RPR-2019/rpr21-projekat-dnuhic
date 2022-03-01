package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Dao.InspectorDao;
import ba.unsa.etf.rpr.Models.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class UserProfileController {
    private Person person;
    public TextField fieldId;
    public TextField fieldName;
    public TextField fieldSurname;
    public TextField fieldEmail;
    public TextField fieldPassword;
    public Label errorLabel;
    public CheckBox adminCheck;

    private String idFormat = "^[0-9]+$";
    private String nameSurnameFormat = "^[\\p{L} .'-]+$";
    private String emailFormat = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private String errorStyle = "-fx-border-color: #ff2e2e #ff2e2e #ff2e2e #ff2e2e;";
    private String correctStyle = "-fx-border-color: #00d700 #00d700 #00d700 #00d700;";

    private InspectorDao inspectorDao = InspectorDao.getInstance();

    public UserProfileController(Person person) {
        this.person = person;
    }

    public UserProfileController() {
    }

    @FXML
    public void initialize() {
        fieldId.textProperty().addListener(e -> {
            if(!fieldId.getText().matches(idFormat))
                fieldId.setStyle(errorStyle);
            else
                fieldId.setStyle(correctStyle);
            errorLabel.setVisible(false);
        });
        fieldName.textProperty().addListener(e -> {
            if(!fieldName.getText().matches(nameSurnameFormat))
                fieldName.setStyle(errorStyle);
            else
                fieldName.setStyle(correctStyle);
            errorLabel.setVisible(false);
        });
        fieldSurname.textProperty().addListener(e -> {
            if(!fieldSurname.getText().matches(nameSurnameFormat))
                fieldSurname.setStyle(errorStyle);
            else
                fieldSurname.setStyle(correctStyle);
            errorLabel.setVisible(false);
        });
        fieldEmail.textProperty().addListener(e -> {
            if(!fieldEmail.getText().matches(emailFormat))
                fieldEmail.setStyle(errorStyle);
            else
                fieldEmail.setStyle(correctStyle);
            errorLabel.setVisible(false);
        });
        fieldPassword.textProperty().addListener(e -> {
            if(fieldPassword.getText().length() < 8)
                fieldPassword.setStyle(errorStyle);
            else
                fieldPassword.setStyle(correctStyle);
            errorLabel.setVisible(false);
        });
        if(person != null) {
            fieldId.setText(person.getId() + "");
            fieldName.setText(person.getName());
            fieldSurname.setText(person.getSurname());
            fieldEmail.setText(person.getEmail());
            fieldPassword.setText(person.getPassword());
            adminCheck.setSelected(person.isAdmin());
        } else {
            fieldId.setText(String.valueOf(inspectorDao.getNextId()));
        }
    }

    public void saveButtonClick(ActionEvent actionEvent) {
        if(fieldId.getText().matches(idFormat) && fieldName.getText().matches(nameSurnameFormat)
                && fieldEmail.getText().matches(emailFormat) && fieldPassword.getText().length() >=8) {
            if(person != null) {
                if(inspectorDao.checkIfUniqueEmail(fieldEmail.getText()) > 1) {
                    errorLabel.setText("Email already exists!");
                    errorLabel.setVisible(true);
                }
                else if(inspectorDao.checkIfUniqueId(Integer.parseInt(fieldId.getText())) > 1) {
                    errorLabel.setText("ID already exists!");
                    errorLabel.setVisible(true);
                }
                person = new Person(Integer.parseInt(fieldId.getText()), fieldName.getText(), fieldSurname.getText(), fieldEmail.getText(), fieldPassword.getText());
                if (adminCheck.isSelected())
                    person.setAdmin(true);
            } else {
                if (inspectorDao.checkIfUniqueEmail(fieldEmail.getText()) > 0) {
                    errorLabel.setText("Email already exists!");
                    errorLabel.setVisible(true);
                } else if (inspectorDao.checkIfUniqueId(Integer.parseInt(fieldId.getText())) > 0) {
                    errorLabel.setText("ID already exists!");
                    errorLabel.setVisible(true);
                } else {
                    person = new Person(Integer.parseInt(fieldId.getText()), fieldName.getText(), fieldSurname.getText(), fieldEmail.getText(), fieldPassword.getText());
                    if (adminCheck.isSelected())
                        person.setAdmin(true);
                }
            }
            Window window = ((Node) actionEvent.getSource()).getScene().getWindow();
            ((Stage) window).close();
        }
    }

    public Person getPerson() {
        return person;
    }

    public void cancelButtonClick(ActionEvent actionEvent) {
        Window window = ((Node) actionEvent.getSource()).getScene().getWindow();
        ((Stage) window).close();
    }

}
