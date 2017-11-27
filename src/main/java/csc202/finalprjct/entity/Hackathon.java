package csc202.finalprjct.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import csc202.finalprjct.additionalServices.RequestService;

/*
* "December": [
    {
      "title": "YHack 2017",
      "url": "https://www.yhack.org/",
      "startDate": "December 3",
      "endDate": "December 5",
      "year": "2017",
      "city": "New Haven, Connecticut, United States",
      "host": "Yale University",
      "length": "36",
      "size": "1000",
      "travel": "yes",
      "prize": "yes",
      "highSchoolers": "yes",
      "cost": "free",
      "facebookURL": "https://www.facebook.com/whyhackatyhack/",
      "twitterURL": "https://twitter.com/yalehack",
      "googlePlusURL": "",
      "notes": "If you're not an undergrad, email team@yhack.org to see if an exception for you can be made."
    }
  ]
* */
public class Hackathon implements Comparable<Hackathon> {
    protected String title;
    protected String url;
    protected String startDate;
    protected String endDate;
    protected String year;
    protected String city;
    protected Integer distanceFromUser;
    protected String host;
    @JsonProperty("length")
    protected String duration;
    @JsonProperty("size")
    protected String capacity;
    @JsonProperty("travel")
    protected String travelReimbursement;
    protected String prize;
    protected String highSchoolers;
    protected String cost;
    @JsonProperty("facebookURL")
    protected String facebookUrl;
    @JsonProperty("twitterURL")
    protected String twitterUrl;
    @JsonProperty("googlePlusURL")
    protected String googlePlusUrl;
    protected String notes;

    public Hackathon() {
    }

    public Hackathon(String title, String url, String startDate, String endDate, String year, String city, String host, String duration, String capacity, String travelReimbursement, String prize, String highSchoolers, String cost, String facebookUrl, String twitterUrl, String googlePlusUrl, String notes) {
        this.title = title;
        this.url = url;
        this.startDate = startDate;
        this.endDate = endDate;
        this.year = year;
        this.city = city;
        this.host = host;
        this.duration = duration;
        this.capacity = capacity;
        this.travelReimbursement = travelReimbursement;
        this.prize = prize;
        this.highSchoolers = highSchoolers;
        this.cost = cost;
        this.facebookUrl = facebookUrl;
        this.twitterUrl = twitterUrl;
        this.googlePlusUrl = googlePlusUrl;
        this.notes = notes;
    }

//    public void setDistanceFromUser(String destination) {
//        RequestService requestService = new RequestService();
//        String originsForUrl = city.replaceAll("\\s", "+");
//        String destinationForUrl = destination.replaceAll("\\s", "+");
//        String distanceJson = requestService.getJsonAsString("https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=" + originsForUrl + "&destinations=" + destinationForUrl + "&key=AIzaSyAH8hz-uAi7-5dlgczj3rf-NaX6wBKVrpc");
//
//        Integer distance = parseDistanceFromJson(distanceJson);
//        distanceFromUser = distance;
//    }

    private Integer parseDistanceFromJson(String jsonString) {
        if (jsonString.indexOf("\"distance\" : {") + 14 > 0
                && jsonString.indexOf("\"duration\"") > 0) {
            String distance = jsonString.substring(jsonString.indexOf("\"distance\" : {") + 14, jsonString.indexOf("\"duration\"")).trim();

            distance = distance.split(",")[1].trim();
            distance = distance.split(":")[1].trim();
            distance = distance.split("\\s")[0];
            return Integer.parseInt(distance);
        }
        return null;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getYear() {
        return year;
    }

    public String getCity() {
        return city;
    }

    public Integer getDistanceFromUser() {
        return distanceFromUser;
    }

    public void setDistanceFromUser(Integer distanceFromUser) {
        this.distanceFromUser = distanceFromUser;
    }

    public String getHost() {
        return host;
    }

    public String getDuration() {
        return duration;
    }

    public String getCapacity() {
        return capacity;
    }

    public String getTravelReimbursement() {
        return travelReimbursement;
    }

    public String getPrize() {
        return prize;
    }

    public String getHighSchoolers() {
        return highSchoolers;
    }

    public String getCost() {
        return cost;
    }

    public String getFacebookUrl() {
        return facebookUrl;
    }

    public String getTwitterUrl() {
        return twitterUrl;
    }

    public String getGooglePlusUrl() {
        return googlePlusUrl;
    }

    public String getNotes() {
        return notes;
    }


    @Override
    public int compareTo(Hackathon o) {
        if (o == null) {
            throw new NullPointerException();
        }

        if (o.getDistanceFromUser() == null) {
            return 1;
        } else if (distanceFromUser == null) {
            return -1;
        } else if (distanceFromUser > o.getDistanceFromUser()) {
            return 1;
        } else if (distanceFromUser < o.getDistanceFromUser()) {
            return -1;
        } else {
            return 0;
        }
    }
}
