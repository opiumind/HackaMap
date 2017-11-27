package csc202.finalprjct.entity;

public class Location {
    private Double longitude;
    private Double latitude;

    public Location(Double longitude, Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public double getDistance(Location destination) {
        return 0;
    }
}
