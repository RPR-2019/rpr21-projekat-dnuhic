package ba.unsa.etf.rpr.Models;

public class Admin extends Person {
    public Admin(int id,String name, String surname, String email, String password) {
        super(id, email, password, name, surname);
    }
}
