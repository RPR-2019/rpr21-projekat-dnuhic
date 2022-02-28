package ba.unsa.etf.rpr.Models;

public class Inspector extends Person {
    public Inspector(int id,String name, String surname, String email, String password) {
        super(id, email, password, name, surname);
    }
}
