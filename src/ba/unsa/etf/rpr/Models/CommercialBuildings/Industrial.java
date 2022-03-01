package ba.unsa.etf.rpr.Models.CommercialBuildings;

import ba.unsa.etf.rpr.Models.CommercialBuilding;

public class Industrial extends CommercialBuilding {

    public enum Type {
        FACTORY("Factory"), BUILDING_SITE("Building Site"), OFFICE("Office");

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

    public Industrial(String name, String location, String picture, int employees, Type type) {
        super(name, location, picture, employees);
        this.type = type;
    }

    public Industrial(String name, String location, int employees, Type type) {
        super(name, location, employees);
        this.type = type;
    }

}
