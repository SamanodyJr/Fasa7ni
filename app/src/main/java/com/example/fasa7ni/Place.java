package com.example.fasa7ni;

public class Place {
    String name;
    String location;
    String opening_hours;
    int image;

    public Place(String name, String location, String opening_hours, int image)
    {
        this.name = name;
        this.location = location;
        this.opening_hours = opening_hours;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOpening_hours() {
        return opening_hours;
    }

    public void setOpening_hours(String opening_hours) {
        this.opening_hours = opening_hours;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
