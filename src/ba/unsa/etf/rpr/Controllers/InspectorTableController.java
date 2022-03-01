package ba.unsa.etf.rpr.Controllers;

import ba.unsa.etf.rpr.Models.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class InspectorTableController {
    public TableView<Person> inspectorTable;
    ObservableList<Person> users;
    public TableColumn<Person, String> colId;
    public TableColumn<Person, String> colName;
    public TableColumn<Person, String> colSurname;
    public TableColumn<Person, String> colEmail;
    public TableColumn<Person, String> colPassword;
    public TableColumn<Person, String> colAdmin;

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
    }
}
