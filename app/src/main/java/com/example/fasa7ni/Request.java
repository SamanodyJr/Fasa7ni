package com.example.fasa7ni;

public class Request
{
    String Requester;
    int mutual;
    int remove, add;
    int image;

    public Request(String name, int mutual, int remove, int add, int image)
    {
        this.Requester = name;
        this.image = image;
        this.mutual = mutual;
        this.remove = remove;
        this.add = add;
    }

    public String getName() {
        return Requester;
    }

    public void setName(String name) {
        this.Requester = name;
    }

    public int getMutual() {
        return mutual;
    }

    public void setMutual(int mutual) {
        this.mutual = mutual;
    }

    public int getRemove() {
        return remove;
    }

    public void setRemove(int remove) {
        this.remove = remove;
    }

    public int getAdd() {
        return add;
    }

    public void setAdd(int add) {
        this.add = add;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image)
    {
        this.image = image;
    }
}