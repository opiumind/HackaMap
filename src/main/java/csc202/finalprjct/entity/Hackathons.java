package csc202.finalprjct.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Hackathons {
    @JsonProperty("February")
    Hackathon[] month;

    public Hackathons() {
    }

    public Hackathons(Hackathon[] month) {
        this.month = month;
    }


    public void setMonth(Hackathon[] month) {
        this.month = month;
    }
}
