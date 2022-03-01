package ba.unsa.etf.rpr.Models.CommercialBuildings;

import ba.unsa.etf.rpr.Models.CommercialBuilding;
import javafx.beans.property.SimpleIntegerProperty;

public class Educational extends CommercialBuilding {
    public enum Type {
        PRIMARY_SCHOOL("Primary School"), HIGH_SCHOOL("High School"), UNIVERSITY("Univeristy");

        private String string;

        Type(String type) {
            string = type;
        }

        @Override
        public String toString() {
            return string;
        }
    }
    private Type type;
    private SimpleIntegerProperty studentNumber;

    public Educational(String name, String location, String picture, int employees, Type type) {
        super(name, location, picture, employees);
        this.type = type;
    }

    public Educational(String name, String location, int employees, Type type) {
        super(name, location, employees);
        this.type = type;
    }
}
