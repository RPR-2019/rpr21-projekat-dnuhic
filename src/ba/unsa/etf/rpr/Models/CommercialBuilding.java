package ba.unsa.etf.rpr.Models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public abstract class CommercialBuilding {
    private SimpleStringProperty name;
    private SimpleStringProperty location;
    private String picture;
    private SimpleIntegerProperty employees;

    public CommercialBuilding(String name, String location, String picture, int employees) {
        this.name = new SimpleStringProperty(name);
        this.location = new SimpleStringProperty(location);
        this.picture = picture;
        this.employees = new SimpleIntegerProperty(employees);
    }

    public CommercialBuilding(String name, String location, int employees) {
        this.name = new SimpleStringProperty(name);
        this.location = new SimpleStringProperty(location);
        this.employees = new SimpleIntegerProperty(employees);
        this.picture = "/images/building-1062.png";
    }
}
