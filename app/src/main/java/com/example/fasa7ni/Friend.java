package com.example.fasa7ni;

public class Friend
{
    String name, mutual;
    int remove;
    int image;

    public Friend(String name, String mutual, int remove, int image)
    {
        this.name = name;
        this.image = image;
        this.mutual = mutual;
        this.remove = remove;
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}