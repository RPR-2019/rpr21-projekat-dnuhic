package ba.unsa.etf.rpr.Models.CommercialBuildings;

import ba.unsa.etf.rpr.Models.CommercialBuilding;

public class CateringFacility extends CommercialBuilding {
    public enum Type {
        HOTEL("Hotel"), RESTAURANT("Restaurant"), CAFFE("Caffe"), PUB("Pub");

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

    public CateringFacility(String name, String location, String picture, int employees, Type type) {
        super(name, location, picture, employees);
        this.type = type;
    }

    public CateringFacility(String name, String location, int employees, Type type) {
        super(name, location, employees);
        this.type = type;
    }
}
