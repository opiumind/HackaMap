package csc202.finalprjct.entity;

public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other");

    private String text;

    Gender(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
