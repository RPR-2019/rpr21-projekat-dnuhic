package ba.unsa.etf.rpr.Dao;

import ba.unsa.etf.rpr.IncorrectEmailOrPasswordException;
import ba.unsa.etf.rpr.Models.Person;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InspectorDao {
    private static InspectorDao instance;
    private Connection conn;
    private PreparedStatement getAllPersons, findUserByMail, getAllInspectors, getNextId, deletePerson, updatePerson, addNewPerson, idCount, emailCount;
    private Person loggedUser = null;


    public static InspectorDao getInstance() {
        if(instance == null) instance = new InspectorDao();
        return instance;
    }

    private InspectorDao() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:baza.db");
        } catch (SQLException e) {
            regenerate();
            try {
                createQueries();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        try {
            createQueries();
        } catch (SQLException e) {
            regenerate();
            try {
                createQueries();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void createQueries() throws SQLException {
        getAllPersons = conn.prepareStatement("SELECT id, name, surname, email, password, admin FROM person;");
        findUserByMail = conn.prepareStatement("SELECT id, name, surname, email, password, admin FROM person WHERE email=?;");
        getAllInspectors = conn.prepareStatement("SELECT * FROM person WHERE admin=0;");
        getNextId = conn.prepareStatement("SELECT max(id) + 1 FROM person;");
        deletePerson = conn.prepareStatement("DELETE FROM person WHERE id=?;");
        updatePerson = conn.prepareStatement("UPDATE person SET name=?, surname=?, email=?, password=?, admin=? WHERE id=?;");
        addNewPerson = conn.prepareStatement("INSERT INTO person VALUES (?,?,?,?,?,?);");
        idCount = conn.prepareStatement("SELECT count(*) FROM person WHERE id=?;");
        emailCount = conn.prepareStatement("SELECT count(*) FROM person WHERE email=?;");
    }

    private void regenerate() {
        Scanner ulaz = null;
        try {
            ulaz = new Scanner(new FileInputStream("database.db.sql"));
            String sqlUpit = "";
            while (ulaz.hasNext()) {
                sqlUpit += ulaz.nextLine();
                if ( sqlUpit.length() > 1 && sqlUpit.charAt(sqlUpit.length() - 1) == ';') {
                    try {
                        Statement stmt = conn.createStatement();
                        stmt.execute(sqlUpit);
                        sqlUpit = "";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            ulaz.close();
        } catch (FileNotFoundException e) {
            System.out.println("Ne postoji SQL datoteka... nastavljam sa praznom bazom");
        }
    }

    public int checkIfUniqueEmail(String email) {
        try {
            emailCount.setString(1, email);
            ResultSet set = emailCount.executeQuery();
            if(set.next()) {
                return set.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int checkIfUniqueId(int id) {
        try {
            idCount.setInt(1, id);
            ResultSet set = idCount.executeQuery();
            if(set.next()) {
                return set.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getNextId() {
        try {
            ResultSet set =  getNextId.executeQuery();
            if(set.next()) {
                return set.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public List<Person> getAllInspectors() {
        ArrayList<Person> lista = new ArrayList<Person>();
        try {
            ResultSet set =  getAllPersons.executeQuery();
            while(set.next()) {
                lista.add(new Person(set.getInt(1), set.getString(2), set.getString(3), set.getString(4), set.getString(5), set.getInt(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void addNewPerson(Person person) {
        try {
            addNewPerson.setInt(1, person.getId());
            addNewPerson.setString(2, person.getName());
            addNewPerson.setString(3, person.getSurname());
            addNewPerson.setString(4, person.getEmail());
            addNewPerson.setString(5, person.getPassword());
            addNewPerson.setInt(6, person.getAdminInt());
            addNewPerson.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePerson(Person person) {
        try {
            deletePerson.setInt(1, person.getId());
            deletePerson.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePerson(Person person) {
        try {
            updatePerson.setString(1, person.getName());
            updatePerson.setString(2, person.getSurname());
            updatePerson.setString(3, person.getEmail());
            updatePerson.setString(4, person.getPassword());
            updatePerson.setInt(5, person.getAdminInt());
            updatePerson.setInt(6, person.getId());
            updatePerson.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Person login(String email, String password) throws IncorrectEmailOrPasswordException {
        try {
            findUserByMail.setString(1, email);
            ResultSet set = findUserByMail.executeQuery();
            Person person = null;
            while(set.next()) {
                boolean admin = false;
                if(set.getInt(6) >= 1)
                    admin = true;
                person = new Person(set.getInt(1), set.getString(2), set.getString(3), set.getString(4), set.getString(5), admin);
            }
            if(person == null || !person.getPassword().equals(password)) {
                throw new IncorrectEmailOrPasswordException("Incorrect email or password.");
            }
            loggedUser = person;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loggedUser;
    }

    public void logout() {
        loggedUser = null;
    }
}
