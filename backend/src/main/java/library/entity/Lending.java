package library.entity;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Lending implements Serializable{

    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private int bookID;
    private int status;

    public Lending(){};

    public Lending(String firstname, String lastname, String email, int bookID) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.bookID = bookID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
