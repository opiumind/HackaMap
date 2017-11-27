package csc202.finalprjct.entity;

import java.util.Date;

public class Person {
    private String firstName;
    private String lastName;
    // profilePhoto, ssn, phone will be in Profile Edit on the site
    private int ssn;
    private Date dob;
    private Gender gender;
    private String street;
    private String city;
    private String state;
    private Integer house;
    private Integer zip;
    private Location location;

    // Empty constructor
    public Person() {
    }

    // Constructor with only required fields
    public Person(String firstName, String lastName, Date dob, Gender gender, String street, String city, String state, Integer house, Integer zip) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.street = street;
        this.city = city;
        this.state = state;
        this.house = house;
        this.zip = zip;
    }

    // Full constructor
    public Person(String firstName, String lastName, int ssn, Date dob, Gender gender, String street, String city, String state, Integer house, Integer zip) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.dob = dob;
        this.gender = gender;
        this.street = street;
        this.city = city;
        this.state = state;
        this.house = house;
        this.zip = zip;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getHouse() {
        return house;
    }

    public void setHouse(Integer house) {
        this.house = house;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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
