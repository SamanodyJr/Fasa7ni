package com.example.fasa7ni;

public class Request
{
    String name, mutual;
    int remove, add;
    int image;

    public Request(String name, String mutual, int remove, int add, int image)
    {
        this.name = name;
        this.image = image;
        this.mutual = mutual;
        this.remove = remove;
        this.add = add;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMutual() {
        return mutual;
    }

    public void setMutual(String mutual) {
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