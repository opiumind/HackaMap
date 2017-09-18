package csc202.finalprjct.entity;

public class Phone {
    private int countryCode;
    private int areaCode;
    private int phoneNumber;

    public Phone(int countryCode, int areaCode, int phoneNumber) {
        this.countryCode = countryCode;
        this.areaCode = areaCode;
        this.phoneNumber = phoneNumber;
    }

    public int getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(int countryCode) {
        this.countryCode = countryCode;
    }

    public int getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
