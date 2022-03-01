package ba.unsa.etf.rpr.Models;

public enum InspectionResult {
    PASSED("Passed"), WARNED("Warned"), FINED("Fined"), CLOSED("Closed");

    private String string;

    InspectionResult(String type) {
        string = type;
    }

    @Override
    public String toString() {
        return string;
    }
}
