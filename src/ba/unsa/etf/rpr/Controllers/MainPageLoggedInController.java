package ba.unsa.etf.rpr.Controllers;


import ba.unsa.etf.rpr.Dao.InspectorDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainPageLoggedInController {
    private InspectorDao dao = InspectorDao.getInstance();

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
