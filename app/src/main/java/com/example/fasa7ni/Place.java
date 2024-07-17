package com.example.fasa7ni;

public class Place
{
    public String name;
    public String location;
    public String description;
    public String phone;
    public String OpeningTime;
    public String ClosingTime;
    public String WorkingDays;
    public String image;


    public Place(String name, String Location, String description, String phone, String OpeningTime, String ClosingTime, String WorkingDays, String image)
    {
        this.name = name;
        this.location = Location;
        this.description = description;
        this.phone = phone;
        this.OpeningTime = OpeningTime;
        this.ClosingTime = ClosingTime;
        this.WorkingDays = WorkingDays;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getOpeningTime() {
        return OpeningTime;
    }

    public void setOpeningTime(String OpeningTime) {
        this.OpeningTime = OpeningTime;
    }

    public String getClosingTime() {
        return ClosingTime;
    }

    public void setClosingTime(String ClosingTime) {
        this.ClosingTime = ClosingTime;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getWorkingDays() {
        return WorkingDays;
    }

    public void setWorkingDays(String workingDays) {
        WorkingDays = workingDays;
    }
}
