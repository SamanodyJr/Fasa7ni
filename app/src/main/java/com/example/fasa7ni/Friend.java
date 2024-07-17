package com.example.fasa7ni;

public class Friend
{
    String Requester;
    String image;
    int Mutual;
    int remove;

    public Friend(String req, String image,int mut,int remove)
    {
        this.Requester = req;
        this.image = image;
        this.Mutual=mut;
        this.remove=remove;

    }

    public String getName() {
        return Requester;
    }

    public void setName(String name) {
        this.Requester = name;
    }

    public int getMutual() {
        return Mutual;
    }

    public void setMutual(int mutual) {
        this.Mutual = mutual;
    }

    public int getRemove() {
        return remove;
    }

    public void setRemove(int remove) {
        this.remove = remove;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}