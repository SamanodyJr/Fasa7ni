package com.example.fasa7ni;

public class Event
{
    String name, hostName;
    String location;

    String opening_hours;
    int image;

    public Event(String name, String location, String opening_hours, String hostName, int image)
    {
        this.name = name;
        this.location = location;
        this.opening_hours = opening_hours;
        this.image = image;
        this.hostName = hostName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String name) {
        this.hostName = name;
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