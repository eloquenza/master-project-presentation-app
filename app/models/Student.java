package models;

public class Student {
    public String firstName;
    public String lastName;
    public String mail;
    public int matNr;

    public Student() {
        firstName = "";
        lastName = "";
        mail = "";
        matNr = 0;
    }

    public Student(String firstName, String lastName, String mail, int matNr) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.matNr = matNr;
    }

    public String fullName() {
        return lastName + ", " + firstName;
    }
}