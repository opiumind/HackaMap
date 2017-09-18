package csc202.finalprjct.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

public class Person {
    private String firstName;
    private String lastName;
    // profilePhoto, ssn, phone will be in Profile Edit on the site
    private int ssn;
    private Date dob;
    private Gender gender;

    public Person() {
    }

    public Person(String firstName, String lastName, int ssn, Date dob, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.dob = dob;
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
