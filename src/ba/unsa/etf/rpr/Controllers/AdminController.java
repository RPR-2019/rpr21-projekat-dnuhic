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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminController {
    public BorderPane borderPane;
    private InspectorDao dao = InspectorDao.getInstance();


    @FXML
    public void initialize() {
        InspectorTableController inspectorTableController = new InspectorTableController(dao.getAllInspectors());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/inspectorTable.fxml"));
        loader.setController(inspectorTableController);
        try {
            borderPane.setCenter(loader.load());
            System.out.println("here");
        } catch (IOException e) {
            e.printStackTrace();
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
