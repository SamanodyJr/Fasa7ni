package com.example.fasa7ni;

public class Event
{
    String Fos7a_Name, Host_Username;
    String Description;
    int Capacity;
    String Fos7a_Time;
    String Fos7a_Date;
    String Image;
    int Is_Public;
    String Place_Name;

    public Event(String Fos7a_Name, String Host_Username, String Description, String Fos7a_Time, String Fos7a_Date,int Capacity,String Image, int Is_Public,String Place_Name)
    {
        this.Fos7a_Name = Fos7a_Name;
        this.Host_Username = Host_Username;
        this.Description = Description;
        this.Capacity = Capacity;
        this.Fos7a_Time = Fos7a_Time;
        this.Fos7a_Date = Fos7a_Date;
        this.Image = Image;
        this.Is_Public = Is_Public;
        this.Place_Name = Place_Name;
    }

    public String getName() {
        return Fos7a_Name;
    }

    public void setName(String name) {
        this.Fos7a_Name = name;
    }

    public String getHostName() {
        return Host_Username;
    }

    public void setHostName(String name) {
        this.Host_Username = name;
    }

    public String getLocation() {
        return Place_Name;
    }

    public void setLocation(String location) {
        this.Place_Name = location;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        this.Image = image;
    }

    public String getDate() {
        return Fos7a_Date;
    }

    public void setDate(String Date) {
        this.Fos7a_Date = Date;
    }
}