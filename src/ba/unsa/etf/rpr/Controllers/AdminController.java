package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Dao.InspectorDao;
import ba.unsa.etf.rpr.Models.Person;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class AdminController {
    public BorderPane borderPane;
    private InspectorDao dao = InspectorDao.getInstance();
    private InspectorTableController inspectorTableController;



    @FXML
    public void initialize() {
        inspectorTableController = new InspectorTableController(dao.getAllInspectors());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/inspectorTable.fxml"));
        loader.setController(inspectorTableController);
        try {
            borderPane.setCenter(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNewButtonClick(ActionEvent actionEvent) {
        if(inspectorTableController != null) {
            Stage stage = new Stage();
            try {
                UserProfileController userProfileController = new UserProfileController();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/adminUserProfile.fxml"));
                loader.setController(userProfileController);
                Parent root = loader.load();
                Scene scene = new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
                stage.setScene(scene);
                stage.show();
                stage.setOnHiding(e -> {
                    if(userProfileController.getPerson() != null) {
                        dao.addNewPerson(userProfileController.getPerson());
                        inspectorTableController.users.add(userProfileController.getPerson());
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteButtonClick(ActionEvent actionEvent) {
        Person selectedPerson = inspectorTableController.inspectorTable.getSelectionModel().getSelectedItem();
        if(selectedPerson != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Confirmation!");
            alert.setContentText("Are you sure you want to delete " + selectedPerson.getName() + "?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                dao.deletePerson(selectedPerson);
                inspectorTableController.users.remove(selectedPerson);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No user was selected.");
            alert.setContentText("You need to select a user!");

            alert.showAndWait();
        }
    }

    public void signOutButtonClick(ActionEvent actionEvent) {
        try {
            dao.logout();
            Stage stage = ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
            MainPageController mainPageController = new MainPageController();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/mainPage.fxml"));
            fxmlLoader.setController(mainPageController);
            Parent root = null;
            root = fxmlLoader.load();
            Scene scene = new Scene(root, stage.getScene().getWidth(), stage.getScene().getHeight());
            stage.setTitle("Log In");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
