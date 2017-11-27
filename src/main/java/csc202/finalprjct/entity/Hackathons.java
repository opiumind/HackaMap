package csc202.finalprjct.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Hackathons {
    @JsonProperty("January")
    Hackathon[] january;

    public Hackathons() {
    }

    public Hackathons(Hackathon[] january) {
        this.january = january;
    }

    public void func() {

    }

    public void setJanuary(Hackathon[] january) {
        this.january = january;
    }
}
