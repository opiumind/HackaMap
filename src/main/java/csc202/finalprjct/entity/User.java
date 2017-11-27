package csc202.finalprjct.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class User implements Comparable {
    private String username;
    private String email;
    private Phone phone;
    private String password;
    private String confirmPassword;
    // profilePhoto, ssn, phone will be in Profile Edit on the site
    private String profilePhotoUrl;
    private Person person;

    // Empty constructor
    public User() {
        this.person = new Person();
    }

    // Full constructor
    public User(String username, String email, Phone phone, String password, String confirmPassword, String profilePhotoUrl, Person person) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.profilePhotoUrl = profilePhotoUrl;
        this.person = person;
    }

    // Constructor with only required fields
    public User(String username, String email, String password, String confirmPassword, Person person) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.person = person;
    }

    @Override
    public int compareTo(Object o) throws ClassCastException {
        if (o instanceof User) {
            User userToCompare = (User)o;
            return this.username.compareTo(userToCompare.username);
        }
        throw new ClassCastException("Can compare to only instances of User");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        if (username == null) {
            return 0;
        }
        return username.hashCode();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getFirstName() {
        return getPerson().getFirstName();
    }

    public void setFirstName(String firstName) {
        getPerson().setFirstName(firstName);
    }

    public String getLastName() {
        return getPerson().getLastName();
    }

    public void setLastName(String lastName) {
        getPerson().setLastName(lastName);
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getDob() {
        return getPerson().getDob();
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setDob(Date dob) {
        getPerson().setDob(dob);
    }

    public Gender getGender() {
        return getPerson().getGender();
    }

    public void setGender(Gender gender) {
        getPerson().setGender(gender);
    }

    public int getSsn() {
        return person.getSsn();
    }

    public void setSsn(int ssn) {
        person.setSsn(ssn);
    }

    public String getStreet() {
        return person.getStreet();
    }

    public void setStreet(String street) {
        person.setStreet(street);
    }

    public String getCity() {
        return person.getCity();
    }

    public void setCity(String city) {
        person.setCity(city);
    }

    public String getState() {
        return person.getState();
    }

    public void setState(String state) {
        person.setState(state);
    }

    public Integer getHouse() {
        return person.getHouse();
    }

    public void setHouse(Integer house) {
        person.setHouse(house);
    }

    public Integer getZip() {
        return person.getZip();
    }

    public void setZip(Integer zip) {
        person.setZip(zip);
    }

    public Location getLocation() {
        return person.getLocation();
    }

    public void setLocation(Location location) {
        person.setLocation(location);
    }


    public String toString() {
        StringBuffer userAsString = new StringBuffer();
        if (getUsername() != null) {
            userAsString.append(getUsername());
        }
        userAsString.append(":");
        if (getEmail() != null) {
            userAsString.append(getEmail());
        }
        userAsString.append(":");
        if (getPassword() != null) {
            userAsString.append(getPassword());
        }
        userAsString.append(":");
        if (getFirstName() != null) {
            userAsString.append(getFirstName());
        }
        userAsString.append(":");
        if (getLastName() != null) {
            userAsString.append(getLastName());
        }
        userAsString.append(":");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (getDob() != null) {
            userAsString.append(sdf.format(getDob()));
//            try {
//                newUser.setDob(sdf.parse(userAsArray[5]));
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
        }
        userAsString.append(":");
        if (getGender() != null) {
            userAsString.append(getGender().toString());
        }
        userAsString.append(":");
        if (getPhone() != null) {
            String phoneAsString = getPhone().getCountryCode() + "-"
                    + getPhone().getAreaCode() + "-"
                    + getPhone().getPhoneNumber();
            userAsString.append(phoneAsString);
        }
        userAsString.append(":");
        if (getProfilePhotoUrl() != null) {
            userAsString.append(getProfilePhotoUrl());
        }
        userAsString.append(":");
        if (getSsn() != 0) {
            userAsString.append(getSsn());
        }
        userAsString.append(":");
        if (!getState().isEmpty()) {
            userAsString.append(getState());
        }
        userAsString.append(":");
        if (!getCity().isEmpty()) {
            userAsString.append(getCity());
        }
        userAsString.append(":");
        if (!getStreet().isEmpty()) {
            userAsString.append(getStreet());
        }
        userAsString.append(":");
        if (getHouse() != 0) {
            userAsString.append(getHouse());
        }
        userAsString.append(":");
        if (getZip() != 0) {
            userAsString.append(getZip());
        }
        userAsString.append(":");
        if (getLocation() != null) {
            userAsString.append(getLocation().getLatitude());
            userAsString.append(";");
            userAsString.append(getLocation().getLongitude());
        }


        return userAsString.toString();
    }

}
