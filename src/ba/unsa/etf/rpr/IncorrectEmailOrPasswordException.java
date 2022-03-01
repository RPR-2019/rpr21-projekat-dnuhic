package ba.unsa.etf.rpr;

public class IncorrectEmailOrPasswordException extends Exception {
    public IncorrectEmailOrPasswordException(String message) {
        super(message);
    }
}
