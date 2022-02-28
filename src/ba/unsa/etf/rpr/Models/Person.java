package ba.unsa.etf.rpr.Models;

import javafx.beans.property.SimpleStringProperty;

public class Person {
    private int id;
    private SimpleStringProperty name;
    private SimpleStringProperty surname;
    private SimpleStringProperty email;
    private SimpleStringProperty password;

    public Person(int id,String name, String surname, String email, String password) {
        this.id = id;
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
    }
}
