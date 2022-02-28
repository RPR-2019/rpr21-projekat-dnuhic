package ba.unsa.etf.rpr.Models;

import javafx.beans.property.SimpleStringProperty;

public class Inspector extends Person {
    private SimpleStringProperty name;
    private SimpleStringProperty surname;

    public Inspector(int id, String email, String password, String name, String surname) {
        super(id, email, password);
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }
}
