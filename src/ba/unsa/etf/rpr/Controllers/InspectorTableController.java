package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Dao.InspectorDao;
import ba.unsa.etf.rpr.Models.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class InspectorTableController {
    public TableView<Person> inspectorTable;
    public ObservableList<Person> users;
    public TableColumn<Person, String> colId;
    public TableColumn<Person, String> colName;
    public TableColumn<Person, String> colSurname;
    public TableColumn<Person, String> colEmail;
    public TableColumn<Person, String> colPassword;
    public TableColumn<Person, String> colAdmin;

    private InspectorDao inspectorDao = InspectorDao.getInstance();

    public InspectorTableController(List<Person> users) {
        this.users = FXCollections.observableArrayList(users);
    }

    @FXML
    public void initialize() {
        inspectorTable.setItems(users);
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colName.setCellValueFactory(new PropertyValueFactory("name"));
        colSurname.setCellValueFactory(new PropertyValueFactory("surname"));
        colEmail.setCellValueFactory(new PropertyValueFactory("email"));
        colPassword.setCellValueFactory(new PropertyValueFactory("password"));
        colAdmin.setCellValueFactory(new PropertyValueFactory("admin"));
        inspectorTable.setRowFactory( tv -> {
            TableRow<Person> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Person person = row.getItem();
                    Stage stage = new Stage();
                    try {
                        UserProfileController userProfileController = new UserProfileController(person);
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/adminUserProfile.fxml"));
                        loader.setController(userProfileController);
                        Parent root = loader.load();
                        Scene scene = new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE);
                        stage.setScene(scene);
                        stage.show();
                        stage.setOnHiding(e -> {
                            if(userProfileController.getPerson() != null) {
                                person.setId(userProfileController.getPerson().getId());
                                person.setName(userProfileController.getPerson().getName());
                                person.setSurname(userProfileController.getPerson().getSurname());
                                person.setEmail(userProfileController.getPerson().getEmail());
                                person.setPassword(userProfileController.getPerson().getPassword());
                                person.setAdmin(userProfileController.getPerson().isAdmin());
                                inspectorDao.updatePerson(userProfileController.getPerson());
                                inspectorTable.refresh();
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }


}
