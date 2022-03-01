package ba.unsa.etf.rpr.Models;

import javafx.beans.property.SimpleStringProperty;

public class Person {
    private int id;
    private SimpleStringProperty name;
    private SimpleStringProperty surname;
    private SimpleStringProperty email;
    private SimpleStringProperty password;
    private boolean admin;

    public Person(int id,String name, String surname, String email, String password, boolean admin) {
        this.id = id;
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.admin = admin;
    }

    public Person(int id,String name, String surname, String email, String password) {
        this.id = id;
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        admin = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
